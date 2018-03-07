package db.app.recipe;

import db.app.SousChefServer;
import db.app.ingredient.Ingredient;
import db.app.ingredient.IngredientService;
import db.app.person.Person;
import db.app.person.PersonService;
import db.app.util.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.*;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private RInventoryRepository rInventoryRepository;
    @Autowired
    private IngredientService ingredientService;

    /**
     * Returns every Recipe in the database
     *
     * @return Every Recipe in the database
     */
    public List<Recipe> getAllRecipes() {
        List<Recipe> l = new ArrayList<>();
        for (Recipe r : recipeRepository.findAll()) {
            r.setVerbose(false);
            l.add(r);
        }
        return l;
    }


    /**
     * Returns the requested recipe - if it exists - and sets it to be verbose
     * @param recipeId The id for the Recipe desired
     * @return The specified Recipe or null
     */
    public Recipe getRecipe(Integer recipeId) {
        Recipe ret = recipeRepository.findOne(recipeId);
        if (ret == null)
            return null;
        ret.setVerbose(true);
        return ret;
    }

    /**
     * Returns a List of all the person's recipes
     *
     * @param ownerId Id of the Owner of the recipes
     * @return A List of Recipes for that owner
     */
    public List<Recipe> getPersonRecipes(Integer ownerId) {
        List<Recipe> ret = recipeRepository.findByOwner(personService.getPerson(ownerId));
        for (Recipe r : ret)
            r.setVerbose(false);
        return ret;
    }

    /**
     * Adds a recipe to the repository (must insert ingredients later separately)
     *
     * @param ownerId The Owner of the recipe
     * @param recipe  The recipe to insert
     */
    public void addRecipe(Integer ownerId, Recipe recipe) {
        Person person = personService.getPerson(ownerId);
        if (person == null)
            return;
        recipe.setOwner(person);    //ensure properly setting the person field
        recipe.setCreatedDate(new Date());
        recipeRepository.save(recipe);
    }

    /**
     * Removes a recipe from the database
     *
     * @param recipeId The id of the recipe to be deleted
     */
    public void deleteRecipe(Integer recipeId) {
        recipeRepository.delete(recipeId);
    }

    /**
     * Updates the information of an existing recipe
     *
     * @param recipeId  The recipe to be updated
     * @param newRecipe The new recipe data to be changed
     */
    public void updateRecipe(Integer recipeId, Recipe newRecipe) {
        Recipe old = recipeRepository.findOne(recipeId);
        if (old == null)
            return;
        old.setCookMins(newRecipe.getCookMins());
        old.setDescription(newRecipe.getDescription());
        old.setPrepMins(newRecipe.getPrepMins());
        old.setTitle(newRecipe.getTitle());
        old.setTypes(newRecipe.getTypes());
        recipeRepository.save(old);
    }

    /**
     * Inserts an ingredient into the recipe
     *
     * @param recipeId       The recipe to add to
     * @param inventory      The skeleton of the RInventory (having only amount set)
     * @param ingredientName The name of the ingredient to insert
     */
    public void addIngredientToRecipe(Integer recipeId, RInventory inventory, String ingredientName) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        Ingredient i = ingredientService.getIngredient(ingredientName);
        if (i == null)
            return;
        inventory.setIngredient(i);       //set the ingredient properly
        inventory.setRecipe(recipe);
        rInventoryRepository.save(inventory);
    }

    /**
     * Removes an ingredient (inventory) from a recipe
     *
     * @param recipeId       The recipe to be operated on
     * @param ingredientName The ingredient to be removed
     */
    public void removeIngredientFromRecipe(Integer recipeId, String ingredientName) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        if (recipe == null)
            return;
        List<RInventory> ingredients = recipe.getInv();
        if (ingredients == null)
            return;
        Ingredient actual = ingredientService.getIngredient(ingredientName);
        if (actual == null)
            return;
        for (RInventory i : ingredients) {
            if (i.getIngredient().equals(actual)) {
                try {
                    //Due to some dumbass issue, a statement must be used in order to correctly delete the RInventory
                    PreparedStatement stmt = SousChefServer.db.prepareStatement("DELETE FROM db309yt1.recipe_inventory WHERE id=?;");
                    stmt.setInt(1, i.getId());
                    stmt.executeUpdate();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
                return;
            }
        }
    }

    /**
     * Returns a List of recipes that match the Search paramaters
     * Recipes must match all, if any, types,
     * fall under the specified number of time (minutes) and above the star rating
     * Must also have the keyword if specified
     * <p>
     * If no parameter is explicated, filter will not be applied for that parameter
     *
     * @param search The Search holding the filtering parameters - can have any, all, or none of the fields specified
     * @return The valid List of Recipes
     */
    public List<Recipe> search(final Search search) {
        List<Recipe> valid = new ArrayList<>();     //The Recipes to be returned
        for (Recipe r : recipeRepository.findAll()) {
            r.setVerbose(false);
            valid.add(r);                           //all recipes are initially valid
        }
        filterKeywordsAndTypes(search, valid);
        float minStars = search.getStarRating() == null ? 0 : search.getStarRating();   //If no rating specified, min is 0
        int maxTime = search.getTime() == null ? Integer.MAX_VALUE : search.getTime();
        Set<Recipe> toRemove = new HashSet<>();         //must use other Set to avoid removing elements during a loop (failfast)
        for (Recipe r : valid) {
            if (r.getCookMins() + r.getPrepMins() > maxTime)
                toRemove.add(r);
//            else if (r.getStarRating() < minStars)
//                toRemove.add(r);
        }
        valid.removeAll(toRemove);
        //Sort by star rating then total time
        valid.sort(Recipe.RecipeComparator);
        return valid;
    }

    /**
     * Removes Recipes from valid that do not match the keywords and types, if specified, in search
     *
     * @param search The Search containing the types and keywords
     * @param valid  The original list of valid Recipes to be modified
     */
    private void filterKeywordsAndTypes(final Search search, List<Recipe> valid) {
        List<Recipe> toKeep = new ArrayList<>();
        /*
        4 scenarios
        1.) Has keyword and types
        2.) Has keyword
        3.) Has types
        4.) None
         */
        if (search.getKeywords() != null && !search.getKeywords().isEmpty()) {       //has keyword
            if (search.getTypes() != null && !search.getTypes().isEmpty()) {       //has keyword and types
                for (String type : search.getTypes()) {         //Go through each type
                    for (Recipe r : recipeRepository.findByTypesContaining(type)) {       //For each recipe matching that type, add it if has the keywords
                        toKeep.add(r);
                        for (String s : search.getKeywords()) {
                            //if it is missing any of the keywords, remove it
                            if (!(Helpers.containsIgnoreCase(r.getTitle(), s) || Helpers.containsIgnoreCase(r.getDescription(), s))) {
                                toKeep.remove(r);
                                break;
                            }
                        }
                    }
                    valid.retainAll(toKeep);     //Get rid of anything not having this type
                }
            } else {                //doesn't have types
                for (Recipe r : valid) {
                    toKeep.add(r);
                    for (String s : search.getKeywords()) {
                        //if it is missing any of the keywords, remove it
                        if (!(Helpers.containsIgnoreCase(r.getTitle(), s) || Helpers.containsIgnoreCase(r.getDescription(), s))) {
                            toKeep.remove(r);
                            break;
                        }
                    }
                }
                valid.retainAll(toKeep);
            }
        } else {    //doesn't have keywords
            if (search.getTypes() != null && !search.getTypes().isEmpty()) {       //has types
                for (String type : search.getTypes()) {         //Go through each type
                    //For each recipe matching that type, add it if has keyword
                    toKeep.addAll(recipeRepository.findByTypesContaining(type));
                    valid.retainAll(toKeep);     //Get rid of anything not having this type
                }
            }
        }
    }
}
