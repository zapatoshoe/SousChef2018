package db.app.Recipe;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import db.app.Person.Person;

import javax.persistence.*;
import javax.sql.rowset.serial.SerialBlob;
import java.sql.Blob;
import java.sql.SQLException;
import java.util.Base64;
import java.util.Date;
import java.util.List;

@Entity
@JsonSerialize(using = RecipeSerializer.class)
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Person owner;

    private String title;

    @Column(columnDefinition = "TEXT")
    private String description;

    private Integer prepMins;

    private Integer cookMins;

    private String types;    //TODO

    private Date createdDate;

    private Blob picture;

    @Transient
    private String image;

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RInventory> inv;

//    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Step> steps; //TODO
//    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
//    private List<Review> reviews; //TODO

    public Integer getId() {
        return id;
    }
    public void setId(Integer id) {
        this.id = id;
    }

    public Person getOwner() {
        return owner;
    }
    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public String getTitle() {
        return title;
    }
    public void setTitle(String title) {
        this.title = title;
    }

    public String getDescription() {
        return description;
    }
    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getPrepMins() {
        return prepMins;
    }
    public void setPrepMins(Integer prepMins) {
        this.prepMins = prepMins;
    }

    public Integer getCookMins() {
        return cookMins;
    }
    public void setCookMins(Integer cookMins) {
        this.cookMins = cookMins;
    }

    public String getTypes() {
        return types;
    }
    public void setTypes(String types) {
        this.types = types;
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

    public List<RInventory> getInv() {
        return inv;
    }
    public void setInv(List<RInventory> inv) {
        this.inv = inv;
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
}
