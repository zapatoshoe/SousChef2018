package db.app.Person;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import db.app.ImageAndPicture;
import db.app.Inventory.Inventory;
import db.app.ListItem.ListItem;

import javax.persistence.*;
import java.sql.Blob;
import java.util.Date;
import java.util.List;

@Entity
@JsonSerialize(using = PersonSerializer.class)
public class Person implements ImageAndPicture{

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

	@OneToMany(mappedBy = "owner", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
	private List<ListItem> shoppingList;

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

	public List<Inventory> getInventory() {
		return inventory;
	}
	public void setInventory(List<Inventory> inventory) {
		this.inventory = inventory;
	}

	public List<ListItem> getShoppingList() {
		return shoppingList;
	}
	public void setShoppingList(List<ListItem> shoppingList) {
		this.shoppingList = shoppingList;
	}

}
