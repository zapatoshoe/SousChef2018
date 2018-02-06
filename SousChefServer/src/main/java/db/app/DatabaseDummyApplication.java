package db.app;

import java.net.InetAddress;
import java.net.UnknownHostException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.web.context.WebApplicationContext;

@SpringBootApplication
public class DatabaseDummyApplication {

	public static void main(String[] args) throws UnknownHostException {
		SpringApplication.run(DatabaseDummyApplication.class, args);
		System.out.println(InetAddress.getLocalHost());
	}
	
}
