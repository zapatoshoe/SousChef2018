package db.app.Recipe;

import db.app.Inventory.Inventory;
import org.hibernate.annotations.OnDelete;
import org.hibernate.annotations.OnDeleteAction;

import javax.persistence.DiscriminatorValue;
import javax.persistence.Entity;
import javax.persistence.ManyToOne;

@Entity(name = "RecipeItem")
@DiscriminatorValue("RecipeItem")
@OnDelete(action = OnDeleteAction.CASCADE)
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
