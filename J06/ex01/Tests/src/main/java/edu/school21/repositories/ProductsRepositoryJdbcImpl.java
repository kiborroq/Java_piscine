package edu.school21.repositories;

import edu.school21.models.Product;

import javax.sql.DataSource;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;

public class ProductsRepositoryJdbcImpl implements ProductsRepository {
	Connection con;

	public ProductsRepositoryJdbcImpl(DataSource ds) throws SQLException {
		con = ds.getConnection();
	}

	@Override
	public List<Product> findAll() throws SQLException {
		List<Product> prods = new LinkedList<>();

		ResultSet rs = con.prepareStatement("SELECT * FROM products;").executeQuery();
		while (rs.next())
			prods.add(getProduct(rs));
		return prods;
	}

	@Override
	public Optional<Product> findById(Long id) throws SQLException {
		ResultSet rs = con.prepareStatement("SELECT * FROM products WHERE id=" + id + ";").executeQuery();
		if (rs.next())
			return Optional.of(getProduct(rs));
		return Optional.empty();
	}

	@Override
	public void update(Product product) throws SQLException {
		con.prepareStatement("UPDATE products SET" +
				" name='" + product.getName() +
				"', price=" + product.getPrice() +
				" WHERE id=" + product.getId() + ";").executeUpdate();
	}

	@Override
	public void save(Product product) throws SQLException {
		con.prepareStatement("INSERT INTO products (name, price) VALUES" +
				"('" + product.getName() + "', " +
				product.getPrice() + ");").executeUpdate();
	}

	@Override
	public void delete(Long id) throws SQLException {
		con.prepareStatement("DELETE FROM products WHERE id=" + id + ";").executeUpdate();
	}

	private Product getProduct(ResultSet rs) throws SQLException {
		return new Product(rs.getLong("id"), rs.getString("name"), rs.getFloat("price"));
	}
}
