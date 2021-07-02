package edu.school21.repositories;

import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabase;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.SQLException;

import static org.junit.jupiter.api.Assertions.*;

public class EmbeddedDataSourceTest {
	DataSource ds = null;

	@BeforeEach
	void init() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		ds = builder.setType(EmbeddedDatabaseType.HSQL).addScript("/schema.sql").addScript("/data.sql").build();
	}

	@Test
	void CheckConnection() {
		try {
			assertNotEquals(null, ds.getConnection());
		} catch (SQLException throwables) {
			fail("Error with connection.");
		}
	}
}
