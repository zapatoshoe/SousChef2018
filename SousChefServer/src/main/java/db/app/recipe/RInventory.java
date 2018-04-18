package db.app.recipe;

import db.app.ingredient.Ingredient;
import db.app.ingredient.IngredientType;

import javax.persistence.*;

@Entity(name = "RecipeInventory")
public class RInventory {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer id;

//    @ManyToOne
//    private Ingredient ingredient;
    private Integer ingredientId;

    private String name;

    @Enumerated(EnumType.STRING)
    private IngredientType type;

    private String amount;

    @ManyToOne
    private Recipe recipe;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getIngredientId() {
        return ingredientId;
    }
    public void setIngredientId(Integer ingredientId) {
        this.ingredientId = ingredientId;
    }

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    public IngredientType getType() {
        return type;
    }
    public void setType(IngredientType type) {
        this.type = type;
    }

    //    public Ingredient getIngredient() {
//        return ingredient;
//    }
//
//    public void setIngredient(Ingredient ingredient) {
//        this.ingredient = ingredient;
//    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public int hashCode() {
        return ingredientId;
    }

}
