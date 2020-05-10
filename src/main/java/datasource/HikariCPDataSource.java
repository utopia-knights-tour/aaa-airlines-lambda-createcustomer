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
		config.setMaximumPoolSize(1);
		config.setConnectionTimeout(30000);
		config.setIdleTimeout(30000);
		config.setAutoCommit(false);
		config.addDataSourceProperty("cachePrepStmts", "true");
		config.addDataSourceProperty("prepStmtCacheSize", "250");
		config.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");
		ds = new HikariDataSource(config);
	}

	public static Connection getConnection() throws SQLException {
		return ds.getConnection();
	}
}