package edu.school21.manager;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;
import javax.sql.DataSource;
import java.io.IOException;
import java.lang.reflect.Field;
import java.nio.file.DirectoryStream;
import java.nio.file.Files;
import java.nio.file.Path;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.*;

public class OrmManager{
    DataSource ds;
    Path path;

    public OrmManager(DataSource ds, Path path) throws Exception {
        this.ds = ds;
        this.path = path;
        initTables();
    }

    public <T> T findById(Long id, Class<T> clazz) throws Exception {
        Connection con = ds.getConnection();
        System.out.println(getFindQuery(id, clazz));
        PreparedStatement query = con.prepareStatement(getFindQuery(id, clazz));
        ResultSet rs = query.executeQuery();
        if (!rs.next())
            return null;
        Object instance = clazz.getDeclaredConstructors()[0].newInstance();
        List<Pair<String, Field>> fields = getColumns(instance.getClass().getDeclaredFields(), instance, true);
        for (Pair<String, Field> f : fields) {
            f.getSecond().setAccessible(true);
            f.getSecond().set(instance, rs.getObject(f.getFirst()));
            f.getSecond().setAccessible(false);
        }
        return (T) instance;
    }

    public void update(Object entity) throws Exception {
        Connection con = ds.getConnection();
        System.out.println(getUpdateQuery(entity));
        PreparedStatement query = con.prepareStatement(getUpdateQuery(entity));
        query.executeUpdate();
        con.close();
    }

    public void save(Object entity) throws Exception {
        Connection con = ds.getConnection();
        System.out.println(getSaveQuery(entity));
        PreparedStatement query = con.prepareStatement(getSaveQuery(entity));
        query.executeUpdate();
        con.close();
    }

    private <T> String getFindQuery(Long id, Class<T> clazz) throws Exception {
        if (clazz.getAnnotation(OrmEntity.class) == null)
            throw new Exception(String.format("Class %s doesn't support annotation @OrmEntity", clazz.getName()));
        String tableName = clazz.getAnnotation(OrmEntity.class).table();
        return String.format("SELECT * FROM %s WHERE %s=%d;", tableName, getNameColumnId(clazz), id);
    }

    private String getNameColumnId(Class clazz) throws Exception {
        Field [] fields = clazz.getDeclaredFields();
        for (Field f : fields) {
            if (f.getAnnotation(OrmColumnId.class) != null)
                return f.getAnnotation(OrmColumnId.class).name();
        }
        throw new Exception("Field with @OrmColumnId Not found!");
    }

    private String getSaveQuery(Object entity) throws Exception {
        Class clazz = entity.getClass();
        if (clazz.getAnnotation(OrmEntity.class) == null)
            throw new Exception(String.format("Class %s doesn't support annotation @OrmEntity", clazz.getName()));
        String tableName = ((OrmEntity)clazz.getAnnotation(OrmEntity.class)).table();
        List<Pair<String, Field>> columns = getColumns(clazz.getDeclaredFields(), entity, false);

        String query = String.format("INSERT INTO %s(", tableName);
        StringJoiner names = new StringJoiner(", ");
        for (Pair<String, Field> column : columns) {
            names.add(column.getFirst());
        }
        StringJoiner values = new StringJoiner(", ");
        for (Pair<String, Field> column : columns) {
            column.getSecond().setAccessible(true);
            if (column.getSecond().getType().getSimpleName().equals("String")) {
                values.add(String.format("'%s'", column.getSecond().get(entity)));
            }
            else
                values.add(column.getSecond().get(entity).toString());
            column.getSecond().setAccessible(false);
        }
        return query.concat(names.toString())
                    .concat(") VALUES (")
                    .concat(values.toString())
                    .concat(");");
    }

    private String getUpdateQuery(Object entity) throws Exception {
        Class clazz = entity.getClass();
        if (clazz.getAnnotation(OrmEntity.class) == null)
            throw new Exception(String.format("Class %s doesn't support annotation @OrmEntity", clazz.getName()));
        String tableName = ((OrmEntity)clazz.getAnnotation(OrmEntity.class)).table();
        List<Pair<String, Field>> columns = getColumns(clazz.getDeclaredFields(), entity, false);

        String query = String.format("UPDATE %s SET ", tableName);
        StringJoiner rows = new StringJoiner(", ");
        for (Pair<String, Field> column : columns) {
            column.getSecond().setAccessible(true);
            if (column.getSecond().getType().getSimpleName().equals("String"))
                rows.add(String.format("%s='%s'", column.getFirst(), column.getSecond().get(entity)));
            else
                rows.add(column.getFirst() + "=" + column.getSecond().get(entity));
            column.getSecond().setAccessible(false);
        }
        return query.concat(rows.toString())
                    .concat(";");
    }

    private List<Pair<String, Field>> getColumns(Field[] fields, Object entity, boolean withId) throws IllegalAccessException {
        List<Pair<String, Field>> columns = new LinkedList<>();
        for (Field f : fields) {
            OrmColumn annotation = f.getAnnotation(OrmColumn.class);
            if (annotation != null) {
                columns.add(new Pair(annotation.name(), f));
            } else if (withId && f.getAnnotation(OrmColumnId.class) != null) {
                columns.add(new Pair(f.getAnnotation(OrmColumnId.class).name(), f));
            }
        }
        return columns;
    }

    private void initTables() throws Exception {
        TableCreator tc = new TableCreator(ds);

        List<Class> entities = getOrmClasses();
        for (Class e : entities) {
            tc.createTableFor(e);
        }
    }

    private List<Class> getOrmClasses() throws IOException, ClassNotFoundException {
        DirectoryStream<Path> directoryStream = Files.newDirectoryStream(path);
        List<Class> classes = new LinkedList<>();
        for (Path p : directoryStream) {
            String className = p.getFileName().toString();
            Class entity = Class.forName("edu.school21.modules." + className.substring(0, className.length() - ".java".length()));
            if (entity.getAnnotation(OrmEntity.class) != null)
                classes.add(entity);
        }
        return classes;
    }
}
