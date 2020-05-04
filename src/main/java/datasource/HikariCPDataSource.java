package datasource;

import java.sql.Connection;
import java.sql.SQLException;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

public class HikariCPDataSource {

	private static HikariConfig config = new HikariConfig();
	private static HikariDataSource ds;

	static {
		config.setJdbcUrl(System.getenv("AAA_DATASOURCE_URL"));
		config.setUsername(System.getenv("AAA_DATASOURCE_USERNAME"));
		config.setPassword(System.getenv("AAA_DATASOURCE_PASSWORD"));
		config.setMaximumPoolSize(100);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		config.setAutoCommit(false);
		ds = new HikariDataSource(config);
	}

	public static Connection getConnection() throws SQLException {
		Connection connection = ds.getConnection();
		connection.setAutoCommit(false);
		return connection;
	}
}