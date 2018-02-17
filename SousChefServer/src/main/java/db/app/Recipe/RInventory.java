package db.app.Recipe;

import db.app.Inventory.Inventory;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "RecipeItem")
@DiscriminatorValue("RecipeItem")
public class RInventory extends Inventory {

    @ManyToOne
    private Recipe recipe;

    private String amount;

    public Recipe getRecipe() {
        return recipe;
    }

    public void setRecipe(Recipe recipe) {
        this.recipe = recipe;
    }

    public String getAmount() {
        return amount;
    }

    public void setAmount(String amount) {
        this.amount = amount;
    }
}
