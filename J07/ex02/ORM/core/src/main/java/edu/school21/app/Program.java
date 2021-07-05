package edu.school21.app;

import edu.school21.manager.OrmManager;
import edu.school21.modules.User;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.nio.file.Paths;

public class Program {
    public static void main(String[] args) throws Exception {
        OrmManager om = new OrmManager(getHSQLDataSource(), Paths.get("./core/src/main/java/edu/school21/modules/"));
        User u = new User();
        u.setId(0L);
        u.setAge(15);
        u.setFirstName("dmitriy");
        u.setLastName("mamedov");
        System.out.println(u);
        om.save(u);

        u.setFirstName(null);
        om.update(u);
        System.out.println(u);

        User uu = om.findById(0L, User.class);
        System.out.println(uu);
    }

    public static DataSource getHSQLDataSource() {
        EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
        return builder.setType(EmbeddedDatabaseType.HSQL).build();
    }
}
