package db.app.Ingredient;

import javax.persistence.*;

@Entity
public class Ingredient {

    /**
     * Unique Identifier
     */
    @Id
    private String name;

    /**
     * Identifies the ingredient by type
     */
    @Enumerated(EnumType.STRING)
    private IngredientType type;

    /**
     * Constructs an Ingredient object with blank fields
     */
    public Ingredient(){

    }

    /**
     * Constructs an ingredient given an id and a type
     * @param type the type desired for the ingredient
     */
    public Ingredient(String name, IngredientType type){
        this.name = name;
        this.type = type;
    }

    /**
     * Gets the ingredient's name
     * @return Ingredient's name
     */
    public String getName() {
        return name;
    }

    /**
     * Sets an Ingredient's ID
     * @param name the ID to be given to the Ingredient
     */
    public void setName(String name) {
        this.name = name;
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
