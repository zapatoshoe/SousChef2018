package db.app.Ingredient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredient {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ingredientId;

    public Ingredient(Integer id) {
        ingredientId = id;
    }

    //TODO add all fields
}
