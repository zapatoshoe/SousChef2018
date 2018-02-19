package db.app.Inventory;

import db.app.Ingredient.Ingredient;
import db.app.Person.Person;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.*;

@Entity
@Inheritance(strategy = InheritanceType.SINGLE_TABLE)
@DiscriminatorColumn(name = "Inv_Type")
@DiscriminatorValue(value = "Inventory")
public class Inventory {

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
}
