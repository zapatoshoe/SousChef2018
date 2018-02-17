package db.app.Person;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.fasterxml.jackson.databind.ser.Serializers;
import db.app.Ingredient.Ingredient;
import db.app.Inventory.Inventory;
import org.json.JSONArray;
import org.json.JSONObject;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import javax.sql.rowset.serial.SerialException;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.List;
import java.util.Set;

@Entity
@JsonSerialize(using = PersonSerializer.class)
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
	 * The date the person was created
	 */
	private Date created;
	/**
	 * The date the user last logged on
	 */
	private Date lastLogin;
	/**
	 * The person's image as a Blob (can't be sent over JSON)
	 */
	private Blob picture;
	/**
	 * The person's image encoded in Base64 string
	 */
	@Transient
	private String image;

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<Inventory> inventory;

	public Person() {
		id = -1;
		picture = null;
		image = null;
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

	public Date getCreated() {
		return created;
	}

	public void setCreated(Date created) {
		this.created = created;
	}

	public Date getLastLogin() {
		return lastLogin;
	}

	public void setLastLogin(Date lastLogin) {
		this.lastLogin = lastLogin;
	}

	private Blob getPicture() {
		return picture;
	}
	private void setPicture(Blob picture) {
		this.picture = picture;
	}

	public String getImage() {
		return image;
	}
	private void setImage(String image) {
		this.image = image;
	}

	/**
	 * Converts the Blob from the SQL db to a Base64 encoded string for serialization
	 */
	public void prepForSerialization() {
		try {
			if(this.getPicture() == null || this.getPicture().length() < 4) {
				image = null;
				return;
			}
			int len;
			len = (int) this.getPicture().length();
			byte[] bytes = this.getPicture().getBytes(1, len);
			this.setImage(Base64.getEncoder().encodeToString(bytes));	//for sending over JSON
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/**
	 * Converts what is currently a String encoded in Base64 to a Blob for storage in SQL db
	 */
	public void convertStringToBlob() {
		if(this.getImage() == null)
			return;
		try {
			byte[] bytes = Base64.getDecoder().decode(this.getImage());
			Blob b = new SerialBlob(bytes);
			this.setPicture(b);
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	public List<Inventory> getInventory() {
		return inventory;
	}
	public void setIngredients(List<Inventory> inventory) {
		this.inventory = inventory;
	}

}
