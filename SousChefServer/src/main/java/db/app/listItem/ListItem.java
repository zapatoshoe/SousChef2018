package db.app.listItem;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import db.app.ingredient.Ingredient;
import db.app.person.Person;

import javax.persistence.*;
import java.util.Comparator;
import java.util.List;

@Entity
@JsonSerialize(using = ListItemSerializer.class)
public class ListItem implements Comparable<ListItem> {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

    @ManyToOne(cascade = CascadeType.PERSIST)
    private Person owner;

    private String entry;

    @ManyToOne
    private Ingredient ingredient;

    private Integer orderNumber;

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

    public Integer getOrderNumber() {
        return orderNumber;
    }
    public void setOrderNumber(Integer orderNumber) {
        this.orderNumber = orderNumber;
    }

    public Boolean getChecked() {
        return checked;
    }
    public void setChecked(Boolean checked) {
        this.checked = checked;
    }

    public boolean equals(Object o) {
        return (o instanceof ListItem) && this.getEntry().equals(((ListItem) o).getEntry());   //compare the entries to each other
    }

    @Override
    public int compareTo(ListItem o) {
        return orderNumber < o.orderNumber ? -1 : 1;
    }

    public static Comparator<ListItem> ListItemComparator = ListItem::compareTo;
}
