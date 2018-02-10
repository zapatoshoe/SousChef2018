package db.app.Person;

import javax.persistence.*;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Blob;

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
	/**
	 * The person's image encoded in Base64 string
	 */
	private Blob picture;
	/**
	 * The person's image encoded in Base64 string
	 */
	@Transient
	private String image;

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

	public Blob getPicture() {
		return picture;
	}
	public void setPicture(Blob picture) {
		this.picture = picture;
	}

	public String getImage() {
		return image;
	}
	public void setImage(String image) {
		this.image = image;
	}

}
