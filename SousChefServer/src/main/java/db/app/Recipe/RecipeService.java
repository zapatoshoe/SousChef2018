package db.app.Recipe;

import db.app.DatabaseDummyApplication;
import db.app.Ingredient.Ingredient;
import db.app.Ingredient.IngredientService;
import db.app.Inventory.InventoryRepository;
import db.app.Person.Person;
import db.app.Person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private IngredientService ingredientService;


    /**
     * Returns a List of all the Person's recipes
     * @param ownerId Id of the Owner of the recipes
     * @return A List of Recipes for that owner
     */
    public List<Recipe> getPersonRecipes(Integer ownerId) {
        return recipeRepository.findByOwner(personService.getPerson(ownerId));
    }

    /**
     * Adds a recipe to the repository (must insert ingredients later separately)
     * @param ownerId The Owner of the recipe
     * @param recipe The recipe to insert
     */
    public void addRecipe(Integer ownerId, Recipe recipe) {
        Person person = personService.getPerson(ownerId);
        if(person == null)
            return;
        recipe.setOwner(person);    //ensure properly setting the Person field
        recipeRepository.save(recipe);
    }

    /**
     * Removes a recipe from the database
     * @param recipeId The id of the Recipe to be deleted
     */
    public void deleteRecipe(Integer recipeId) {
        recipeRepository.delete(recipeId);
    }

    /**
     * Updates the information of an existing Recipe
     * @param recipeId The Recipe to be updated
     * @param newRecipe The new Recipe data to be changed
     */
    public void updateRecipe(Integer recipeId, Recipe newRecipe) {
        Recipe old = recipeRepository.findOne(recipeId);
        if(old == null)
            return;
        old.setCookMins(newRecipe.getCookMins());
        old.setDescription(newRecipe.getDescription());
        old.setPrepMins(newRecipe.getPrepMins());
        old.setTitle(newRecipe.getTitle());
        old.setType(newRecipe.getType());
        recipeRepository.save(old);
    }

    /**
     * Inserts an Ingredient into the Recipe
     * @param recipeId The Recipe to add to
     * @param inventory The skeleton of the RInventory (having only amount set)
     * @param ingredientName The name of the ingredient to insert
     */
    public void addIngredientToRecipe(Integer recipeId, RInventory inventory, String ingredientName) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        Ingredient i = ingredientService.getIngredient(ingredientName);
        if(i == null)
            return;
        inventory.setIngredient(i);       //set the ingredient properly
        inventory.setRecipe(recipe);
        inventory.setOwner(recipe.getOwner());
        inventoryRepository.save(inventory);
    }

    /**
     * Removes an Ingredient (Inventory) from a Recipe
     * @param recipeId The Recipe to be operated on
     * @param ingredientName The Ingredient to be removed
     */
    public void removeIngredientFromRecipe(Integer recipeId, String ingredientName) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        if(recipe == null)
            return;
        List<RInventory> ingredients = recipe.getInv();
        if(ingredients == null)
            return;
        Ingredient actual = ingredientService.getIngredient(ingredientName);
        if(actual == null)
            return;
        for(RInventory i : ingredients) {
            if(i.getIngredient().equals(actual)) {
                try {
                    //Due to Inheritance issues, a statement must be used in order to correctly delete the RInventory
                    PreparedStatement stmt = DatabaseDummyApplication.db.prepareStatement("DELETE FROM db309yt1.inventory WHERE inventory_id=?;");
                    stmt.setInt(1, i.getId());
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }

}
