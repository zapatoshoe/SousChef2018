package db.app.Person;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;

@Entity
public class Person {

	/**
	 * Unique identifier
	 */
	@Id
	@GeneratedValue(strategy=GenerationType.AUTO)
	private Integer id;
	/**
	 * Their Full name
	 */
	private String name;
	/**
	 * Their email address
	 */
	private String email;
	/**
	 * Their password
	 */
	private String password;
	/**
	 * Their type - Chef, Author, or Admin
	 */
	private String type;

	public Person() {
		id = -1;
	}
	
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}

	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
			this.email = email;
	}
	
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	
	public String getType() {
		return type;
	}
	public void setType(String type) {
		this.type = type;
	}

	
	
	
}
