package db.app.Ingredient;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Ingredient {

    /**
     * Unique Identifier
     */
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer ingredientId;

    /**
     * Identifies the ingredient by type
     */
    private IngredientType type;

    /**
     * Constructs an Ingredient object with blank fields
     */
    public Ingredient(){

    }

    /**
     * Constructs an ingredient given an id and a type
     * @param id the id desired for the ingredient
     * @param type the type desired for the ingredient
     */
    public Ingredient(Integer id, IngredientType type){
        this.ingredientId = id;
        this.type = type;
    }

    /**
     * Gets the ingredient's ID
     * @return Ingredient's ID
     */
    public Integer getIngredientId() {
        return ingredientId;
    }

    /**
     * Sets an Ingredient's ID
     * @param ingredientId the ID to be given to the Ingredient
     */
    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    /**
     * Gets the ingredient's type
     * @return ingredient's type
     */
    public IngredientType getType() {
        return type;
    }

    /**
     * Sets the ingredient's type
     * @param type desired ingredient type
     */
    public void setType(IngredientType type) {
        this.type = type;
    }
}
