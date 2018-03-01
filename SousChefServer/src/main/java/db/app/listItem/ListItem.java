package db.app.listItem;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import db.app.ingredient.Ingredient;
import db.app.person.Person;

import javax.persistence.*;

@Entity
@JsonSerialize(using = ListItemSerializer.class)
public class ListItem {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne
    private Person owner;

    private String entry;

    @ManyToOne
    private Ingredient ingredient;

    private Boolean checked;

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

    public String getEntry() {
        return entry;
    }
    public void setEntry(String entry) {
        this.entry = entry;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }
    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    public Boolean getChecked() {
        return checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }
}
