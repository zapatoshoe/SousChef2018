package db.app;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class DatabaseDummyApplication {

	public static Connection db;

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(DatabaseDummyApplication.class, args);
		System.out.println(InetAddress.getLocalHost());
		try {
			db = DriverManager.getConnection("jdbc:mysql://mysql.cs.iastate.edu/db309yt1?autoReconnect=true&useSSL=false", "dbu309yt1", "vtdSg4aB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
