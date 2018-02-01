package db.app;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

@SpringBootApplication
public class DatabaseDummyApplication {

	public static void main(String[] args) {
		SpringApplication.run(DatabaseDummyApplication.class, args);
	}
	
}
