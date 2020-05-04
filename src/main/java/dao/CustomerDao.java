package dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import entity.Customer;

public class CustomerDao {

	private Connection connection;

	public CustomerDao(Connection connection) {
		this.connection = connection;
	}

	public Long create(Customer customer) throws SQLException {
		PreparedStatement pstmt = null;
		pstmt = connection.prepareStatement(
				"INSERT INTO Customer (customerName, customerAddress, customerPhone) VALUES (?, ?, ?)",
				Statement.RETURN_GENERATED_KEYS);
		pstmt.setString(1, customer.getCustomerName());
		pstmt.setString(2, customer.getCustomerAddress());
		pstmt.setString(3, customer.getCustomerPhone());
		pstmt.executeUpdate();
		ResultSet rs = pstmt.getGeneratedKeys();
		if (rs.next()) {
			return rs.getLong(1);
		}
		return null;
	}
}
