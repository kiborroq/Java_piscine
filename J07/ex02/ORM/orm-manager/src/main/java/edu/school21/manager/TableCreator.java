package edu.school21.manager;

import edu.school21.annotations.OrmColumn;
import edu.school21.annotations.OrmColumnId;
import edu.school21.annotations.OrmEntity;

import javax.sql.DataSource;
import java.lang.reflect.Field;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.StringJoiner;

public class TableCreator {
    private DataSource ds;

    public TableCreator(DataSource ds) throws SQLException {
        this.ds = ds;
    }

    public void createTableFor(Class entity) throws Exception {
        Connection con = ds.getConnection();
        String query = getDropTableQuery(entity);
        con.prepareStatement(query).executeUpdate();
        query = getCreatTableQuery(entity);
        con.prepareStatement(query).executeUpdate();
        con.close();
    }

    private String getDropTableQuery(Class entity) {
        return String.format("DROP TABLE IF EXISTS %s;", ((OrmEntity) entity.getAnnotation(OrmEntity.class)).table());
    }

    private String getCreatTableQuery(Class entity) throws Exception {
        String queryContent = getCommandHeader((OrmEntity) entity.getAnnotation(OrmEntity.class));
        StringJoiner types = new StringJoiner(",");
        for (Field filed : entity.getDeclaredFields()) {
            if (filed.getAnnotation(OrmColumnId.class) != null) {
                if (filed.getType().getSimpleName().compareToIgnoreCase("long") == 0)
                    types.add(getIdColumn(filed.getAnnotation(OrmColumnId.class).name()));
                else
                    throw new Exception("Invalid id type " + filed.getType().getSimpleName());
            } else if (filed.getAnnotation(OrmColumn.class) != null)
                types.add(getOtherColumn(filed.getAnnotation(OrmColumn.class), filed.getType().getSimpleName()));
        }
        queryContent = queryContent.concat(String.valueOf(types)).concat(");");
        return queryContent;
    }

    private String getOtherColumn(OrmColumn annotation, String type) throws Exception {
        return String.format("%s %s", annotation.name(), getSqlType(annotation.length(), type));
    }

    private String getSqlType(int length, String type) throws Exception {
        if (type.compareTo("String") == 0)
            return String.format("varchar(%d)", length);
        if (type.compareTo("Integer") == 0 || type.compareTo("int") == 0)
            return "integer";
        if (type.compareToIgnoreCase("long") == 0)
            return "bigint";
        if (type.compareToIgnoreCase("boolean") == 0)
            return "boolean";
        if (type.compareToIgnoreCase("double") == 0)
            return "double";
        throw new Exception("Invalid type: " + type);
    }

    private String getIdColumn(String name) {
        return String.format("%s bigint IDENTITY PRIMARY KEY", name);
    }

    private String getCommandHeader(OrmEntity annotation) {
        return String.format("CREATE TABLE %s(", annotation.table());
    }
}
