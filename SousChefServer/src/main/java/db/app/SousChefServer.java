package db.app;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.net.InetAddress;
import java.net.UnknownHostException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

@SpringBootApplication
public class SousChefServer {

	public static Connection db;

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(SousChefServer.class, args);
		System.out.println(InetAddress.getLocalHost());
		try {
			db = DriverManager.getConnection("jdbc:mysql://mysql.cs.iastate.edu/db309yt1?autoReconnect=true&useSSL=false", "dbu309yt1", "vtdSg4aB");
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
	
}
