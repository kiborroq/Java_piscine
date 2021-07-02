package edu.school21.repositories;

import edu.school21.models.Product;
import edu.school21.repositories.ProductsRepository;
import edu.school21.repositories.ProductsRepositoryJdbcImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseBuilder;
import org.springframework.jdbc.datasource.embedded.EmbeddedDatabaseType;

import javax.sql.DataSource;
import java.sql.SQLException;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

public class ProductsRepositoryJdbcImplTest {
	DataSource ds;

	final List <Product> EXPECTED_FIND_ALL_PRODUCTS = Arrays.asList(
		new Product(0L, "pie", 111.0f),
			new Product(1L, "meat", 333.0f),
			new Product(2L, "water", 22.0f),
			new Product(3L, "package", 6.0f),
			new Product(4L, "potato", 44.0f)
	);
	final List <Product> EXPECTED_FIND_ALL_PRODUCTS_AFTER_DELETE = Arrays.asList(
			new Product(0L, "pie", 111.0f),
			new Product(2L, "water", 22.0f),
			new Product(3L, "package", 6.0f),
			new Product(4L, "potato", 44.0f)
	);
	final Product EXPECTED_FIND_BY_ID_PRODUCT = new Product(3L, "package", 6.0f);
	final Product EXPECTED_UPDATED_PRODUCT = new Product(2L, "milk", 55.0f);
	final Product EXPECTED_SAVED_PRODUCT = new Product(5L, "ice-cream", 77.0f);

	@BeforeEach
	void init() {
		EmbeddedDatabaseBuilder builder = new EmbeddedDatabaseBuilder();
		ds = builder.setType(EmbeddedDatabaseType.HSQL).addScript("/schema.sql").addScript("/data.sql").build();
	}

	@Test
	void returned_connection_is_not_null() {
		try {
			assertNotEquals(null, ds.getConnection());
		} catch (SQLException throwables) {
			fail("Error with connection.");
		}
	}

	@Test
	void find_by_id() {
		Long valid_id = 3L;
		Long not_valid_id = 10L;

		assertAll(
				()->assertEquals(Optional.empty(), new ProductsRepositoryJdbcImpl(ds).findById(not_valid_id)),
				()->assertEquals(EXPECTED_FIND_BY_ID_PRODUCT, new ProductsRepositoryJdbcImpl(ds).findById(valid_id).get())
		);
	}

	@Test
	void find_all() throws SQLException {
		assertArrayEquals(EXPECTED_FIND_ALL_PRODUCTS.toArray(), new ProductsRepositoryJdbcImpl(ds).findAll().toArray());
	}

	@Test
	void update() throws SQLException {
		Long id = 2L;
		ProductsRepository pr = new ProductsRepositoryJdbcImpl(ds);
		Product p = pr.findById(id).get();
		p.setName("milk");
		p.setPrice(55.0f);
		pr.update(p);

		assertEquals(EXPECTED_UPDATED_PRODUCT, pr.findById(id).get());
	}

	@Test
	void save() throws SQLException {
		Long id = 5L;
		ProductsRepository pr = new ProductsRepositoryJdbcImpl(ds);
		Product p = new Product(
			id, "ice-cream", 77.0f
		);
		pr.save(p);

		assertEquals(EXPECTED_SAVED_PRODUCT, pr.findById(id).get());
	}


	@Test
	void delete() throws SQLException {
		ProductsRepository pr = new ProductsRepositoryJdbcImpl(ds);
		pr.delete(1L);
		assertArrayEquals(EXPECTED_FIND_ALL_PRODUCTS_AFTER_DELETE.toArray(), new ProductsRepositoryJdbcImpl(ds).findAll().toArray());
	}
}
