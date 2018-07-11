package db.app.inventory;

import db.app.ingredient.Ingredient;
import db.app.person.Person;

import javax.persistence.*;

@Entity
public class Inventory implements Comparable<Inventory>{

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer inventoryId;

    @ManyToOne
    private Person owner;

    @ManyToOne
    private Ingredient ingredient;

    public Inventory(){}
    public Inventory(Person owner, Ingredient ingredient) {
        this.owner = owner;
        this.ingredient = ingredient;
    }

    public Integer getId() {return inventoryId; }

    public Person getOwner() {
        return owner;
    }

    public void setOwner(Person owner) {
        this.owner = owner;
    }

    public Ingredient getIngredient() {
        return ingredient;
    }

    public void setIngredient(Ingredient ingredient) {
        this.ingredient = ingredient;
    }

    @Override
    public boolean equals(Object o) {
        return o instanceof Inventory && this.ingredient.equals(((Inventory)o).ingredient);
    }

    @Override
    public int compareTo(Inventory o) {
        return ingredient.getName().compareTo(o.ingredient.getName());
    }
}
