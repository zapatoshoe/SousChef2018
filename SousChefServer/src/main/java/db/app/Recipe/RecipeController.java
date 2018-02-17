package db.app.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.annotation.security.PermitAll;
import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;


    /**
     * Returns all of a Person's recipes
     * @param ownerId The Person whose recipes you want
     * @return A List of recipes belonging to that owner
     */
    @RequestMapping("/{ownerId}")
    public List<Recipe> getPersonsRecipes(@PathVariable Integer ownerId) {
        return recipeService.getPersonRecipes(ownerId);
    }

    /**
     * Adds a recipe to the database with the specified owner
     * @param recipe The recipe to be added to the database
     * Recipe assumed to have these fields set already - Title, Description, CookMins, PrepMins, Type, ownerId
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}/add")
    public void addRecipe(@PathVariable Integer ownerId, @RequestBody Recipe recipe) {
        recipeService.addRecipe(ownerId, recipe);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{recipeId}")
    public void deleteRecipe(@PathVariable Integer recipeId) {
        recipeService.deleteRecipe(recipeId);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{recipeId}")
    public void updateRecipe(@PathVariable Integer recipeId, @RequestBody Recipe newRecipe) {
        recipeService.updateRecipe(recipeId, newRecipe);
    }

    /**
     * Inserts the specified ingredient into the specified recipe
     * @param inventory The Inventory with only the amount specified
     * @param recipeId The recipe to add the ingredient to
     * @param ingredientName The name of the ingredient to add to that recipe
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{recipeId}/{ingredientName}")
    public void addToRecipe(@PathVariable Integer recipeId, @PathVariable String ingredientName, @RequestBody RInventory inventory) {
        recipeService.addIngredientToRecipe(recipeId, inventory, ingredientName);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{recipeId}/{ingredientName}")
    public void removeFromRecipe(@PathVariable Integer recipeId, @PathVariable String ingredientName) {
        recipeService.removeIngredientFromRecipe(recipeId, ingredientName);
    }

}
