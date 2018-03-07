package db.app.recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;


    @RequestMapping("/all")
    public List<Recipe> getAllRecipes() {
        return recipeService.getAllRecipes();
    }

    @RequestMapping("/get/{recipeId}")
    public Recipe getRecipe(@PathVariable Integer recipeId) {
        return recipeService.getRecipe(recipeId);
    }

    /**
     * Returns all of a person's recipes
     * @param ownerId The person whose recipes you want
     * @return A List of recipes belonging to that owner
     */
    @RequestMapping("/owner/{ownerId}")
    public List<Recipe> getPersonsRecipes(@PathVariable Integer ownerId) {
        return recipeService.getPersonRecipes(ownerId);
    }

    /**
     * Adds a recipe to the database with the specified owner
     * @param recipe The recipe to be added to the database
     * recipe assumed to have these fields set already - Title, Description, CookMins, PrepMins, Type, ownerId
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}")
    public void addRecipe(@PathVariable Integer ownerId, @RequestBody Recipe recipe) {
        recipeService.addRecipe(ownerId, recipe);
    }

    /**
     * Deletes a recipe from the Repository
     * @param recipeId The id of the recipe to be deleted
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{recipeId}")
    public void deleteRecipe(@PathVariable Integer recipeId) {
        recipeService.deleteRecipe(recipeId);
    }

    /**
     * Updates the info of an existing recipe to the new info
     * @param recipeId The id of the existing recipe
     * @param newRecipe The new info for the recipe
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{recipeId}")
    public void updateRecipe(@PathVariable Integer recipeId, @RequestBody Recipe newRecipe) {
        recipeService.updateRecipe(recipeId, newRecipe);
    }

    /**
     * Inserts the specified ingredient into the specified recipe
     * @param inventory The inventory with only the amount specified
     * @param recipeId The recipe to add the ingredient to
     * @param ingredientName The name of the ingredient to add to that recipe
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{recipeId}/{ingredientName}")
    public void addToRecipe(@PathVariable Integer recipeId, @PathVariable String ingredientName, @RequestBody RInventory inventory) {
        recipeService.addIngredientToRecipe(recipeId, inventory, ingredientName);
    }

    /**
     * Removes the specified ingredient from the specified recipe
     * @param recipeId The id of the recipe to operate on
     * @param ingredientName The name of the ingredient to remove
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{recipeId}/{ingredientName}")
    public void removeFromRecipe(@PathVariable Integer recipeId, @PathVariable String ingredientName) {
        recipeService.removeIngredientFromRecipe(recipeId, ingredientName);
    }

    /**
     * Returns a List of Recipes meeting the specified search parameters
     *
     * @param arr An array of size 1 holding the parameters to compare Recipes to
     * @return A List of Recipes meeting the specified search parameters
     */
    @RequestMapping(method = RequestMethod.POST, value = "/search")
    public List<Recipe> search(@RequestBody List<Search> arr) {
        if(arr == null || arr.isEmpty() || arr.size() > 1)
            return null;
        Search search = arr.get(0);
        return recipeService.search(search);
    }

}
