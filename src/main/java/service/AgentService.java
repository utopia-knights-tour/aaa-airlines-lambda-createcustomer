package service;

import java.sql.Connection;
import java.sql.SQLException;

import dao.CustomerDao;
import datasource.HikariCPDataSource;
import entity.Customer;

public class AgentService {

	public Long createCustomer(Customer customer) throws ClassNotFoundException, SQLException {
		Connection connection = null;
		try {
			connection = HikariCPDataSource.getConnection();
			CustomerDao customerDao = new CustomerDao(connection);
			Long customerId = customerDao.create(customer);
			if (customerId == null) {
				connection.rollback();
				return null;
			}
			connection.commit();
			return customerId;
		} catch (SQLException e) {
			connection.rollback();
			throw e;
		} finally {
			connection.close();
		}
	}
}
