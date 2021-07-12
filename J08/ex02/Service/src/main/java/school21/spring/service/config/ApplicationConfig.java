package school21.spring.service.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan("school21.spring.service")
@PropertySource("/db.properties")
public class ApplicationConfig {

	@Bean
	public HikariDataSource hikariDS() {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader("src/main/resources/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		HikariDataSource hikariDataSource = new HikariDataSource();
		hikariDataSource.setJdbcUrl(properties.getProperty("db.url"));
		hikariDataSource.setUsername(properties.getProperty("db.user"));
		hikariDataSource.setPassword(properties.getProperty("db.password"));
		hikariDataSource.setDriverClassName(properties.getProperty("db.driver.name"));
		return hikariDataSource;
	}

	public DriverManagerDataSource driverManagerDS() {
		Properties properties = new Properties();
		try {
			properties.load(new FileReader("src/main/resources/db.properties"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		DriverManagerDataSource driverManagerDataSource = new DriverManagerDataSource();
		driverManagerDataSource.setUrl(properties.getProperty("db.url"));
		driverManagerDataSource.setUsername(properties.getProperty("db.user"));
		driverManagerDataSource.setPassword(properties.getProperty("db.password"));
		driverManagerDataSource.setDriverClassName(properties.getProperty("db.driver.name"));
		return driverManagerDataSource;
	}
}


