package db.app.Recipe;

import db.app.Person.Person;

import javax.persistence.*;
import java.util.List;

@Entity
public class Recipe {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Person owner;

    private String title;

    private String description;

    private Integer prepMins;

    private Integer cookMins;

    private String type;    //TODO

    @OneToMany(mappedBy = "recipe", cascade = CascadeType.ALL, fetch = FetchType.LAZY)
    private List<RInventory> inv;

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

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public List<RInventory> getInv() {
        return inv;
    }

    public void setInv(List<RInventory> inv) {
        this.inv = inv;
    }
}
