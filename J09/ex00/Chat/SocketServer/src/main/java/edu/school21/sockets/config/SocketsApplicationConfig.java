package edu.school21.sockets.config;

import com.zaxxer.hikari.HikariDataSource;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

import javax.sql.DataSource;
import java.io.FileReader;
import java.io.IOException;
import java.util.Properties;

@Configuration
@ComponentScan("edu.school21.sockets")
public class SocketsApplicationConfig {
	@Bean
	public DataSource dataSource() {
		HikariDataSource ds = new HikariDataSource();
		try {
			Properties properties = new Properties();
			properties.load(new FileReader("src/main/resources/db.properties"));
			ds.setDriverClassName(properties.getProperty("db.driver.name"));
			ds.setUsername(properties.getProperty("db.user"));
			ds.setPassword(properties.getProperty("db.password"));
			ds.setJdbcUrl(properties.getProperty("db.url"));
		} catch (IOException e) {
			e.printStackTrace();
		}
		return ds;
	}

	@Bean
	public PasswordEncoder passwordEncoder() {
		return new BCryptPasswordEncoder();
	}
}
