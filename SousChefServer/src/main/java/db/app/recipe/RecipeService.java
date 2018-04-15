package db.app.recipe;

import db.app.SousChefServer;
import db.app.ingredient.Ingredient;
import db.app.ingredient.IngredientService;
import db.app.inventory.Inventory;
import db.app.person.Person;
import db.app.person.PersonService;
import db.app.recipeFavorite.FRecipeService;
import db.app.recipeSteps.RecipeStepsService;
import db.app.review.Review;
import db.app.review.ReviewService;
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
    @Autowired
    private RecipeStepsService recipeStepsService;
    @Autowired
    private ReviewService reviewService;
    @Autowired
    private FRecipeService fRecipeService;

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
    public Recipe addRecipe(Integer ownerId, Recipe recipe) {
        Person person = personService.getPerson(ownerId);
        if (person == null)
            return new Recipe();
        recipe.setOwner(person);    //ensure properly setting the person field
        recipe.setCreatedDate(new Date());
        recipe.setNumReviews(0);
        recipe.setAverageRating((float)0);
        recipe.setTypes(recipe.getTypes().replace(" ", ""));
        Helpers.convertStringToBlob(recipe);
        Recipe ret = recipeRepository.save(recipe);
        ret.setVerbose(false);
        person.setNumRecipes(person.getNumRecipes() + 1);
        personService.updatePerson(person, person.getId());
        return ret;
    }

    /**
     * Removes a recipe from the database
     *
     * @param recipeId The id of the recipe to be deleted
     */
    public void deleteRecipe(Integer recipeId) {
        Recipe r = recipeRepository.findOne(recipeId);
        if(r == null)       //if no recipe found
            return;
        Person person = r.getOwner();
        person.setNumRecipes(person.getNumRecipes() - 1);
        for(RInventory ri : r.getInv())
            rInventoryRepository.delete(ri.getId());
        recipeStepsService.deleteRecipeSteps(recipeId);
        for(Review review : r.getReviews())
            reviewService.deleteReview(review.getId());
        fRecipeService.removeRecipeFromAllFavorites(recipeId);
        recipeRepository.delete(recipeId);
        personService.updatePerson(person, person.getId());
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
        old.setTime(newRecipe.getTime());
        old.setDescription(newRecipe.getDescription());
        old.setTitle(newRecipe.getTitle());
        old.setTypes(newRecipe.getTypes());
        old.setImage(newRecipe.getImage());
        old.setCreatedDate(new Date());
        Helpers.convertStringToBlob(old);
        recipeRepository.save(old);
    }

    /**
     * Inserts an ingredient into the recipe
     *
     * @param recipeId       The recipe to add to
     * @param inventory      The skeleton of the RInventory (having only amount set)
     * @param ingredientName The name of the ingredient to insert
     */
    @Deprecated
    public void addIngredientToRecipe(Integer recipeId, RInventory inventory, String ingredientName) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        Ingredient i = ingredientService.getIngredient(ingredientName);
        if (i == null)
            return;
        inventory.setIngredient(i);       //set the ingredient properly
        inventory.setRecipe(recipe);
        recipe.setCreatedDate(new Date());
        rInventoryRepository.save(inventory);
    }

    /**
     * Inserts an ingredient into the recipe
     *
     * @param recipeId       The recipe to add to
     * @param inventory      The skeleton of the RInventory (having only amount and ingredient set)
     */
    public void addIngredientToRecipe(Integer recipeId, RInventory inventory) {
        Recipe recipe = recipeRepository.findOne(recipeId);
        if (inventory.getIngredient() == null)
            return;
        inventory.setRecipe(recipe);
        recipe.setCreatedDate(new Date());
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
                recipe.setCreatedDate(new Date());
                return;
            }
        }
    }

    public List<Recipe> recommendRecipes(Integer ownerId) {
        Search search = new Search();
        search.setStarRating(4.5f);
        List<Recipe> favorites = fRecipeService.getFavorites(ownerId);
        if(favorites.size() < 1)
            return new ArrayList<>();
        ArrayList<String> types = new ArrayList<>();
        ArrayList<Integer> counts = new ArrayList<>();
        for(Recipe r : favorites) {                             //loop to count number of occurrences of type in favorites
            for(String s : r.getTypes().split(",")) {       //get each type
                int i = types.indexOf(s);
                if(i >= 0) {
                    counts.set(i, counts.get(i) + 1);   //increment count
                } else {
                    types.add(s);                       //add it to the list
                    counts.add(1);                      //set it to one count
                }
            }
        }
        int maxIndex = 0;
        int maxCount = counts.get(0);
        for(int i=1; i<types.size(); i++) {
            if(counts.get(i) > maxCount) {
                maxIndex = i;
                maxCount = counts.get(i);
            }
        }
        List<String> l = new ArrayList<>();
        l.add(types.get(maxIndex));
        search.setTypes(l);
        List<Recipe> recommended;
        do {
            recommended = search(search);
            search.setStarRating((float) (search.getStarRating() - .5));       //decrease max rating by .5 and try again
            recommended.removeAll(favorites);
            recommended.removeAll(getPersonRecipes(ownerId));
        }while(recommended.size() < 4);
        return recommended;
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
            if (r.getTime() > maxTime)
                toRemove.add(r);
            else if (r.getAverageRating() < minStars)
                toRemove.add(r);
        }
        valid.removeAll(toRemove);
        if(search.getCheckInventory() != null && search.getCheckInventory())
            filterByInventory(search.getOwnerId(), valid);
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
        Map<Integer, Recipe> toKeep = new HashMap<>();
        /*
        4 scenarios
        1.) Has keywords and types
        2.) Has keywords
        3.) Has types
        4.) None
         */
        if (search.getKeywords() != null && !search.getKeywords().isEmpty()) {       //has keyword
            if (search.getTypes() != null && !search.getTypes().isEmpty()) {       //has keyword and types
                for (String type : search.getTypes()) {         //Go through each type
                    for (Recipe r : recipeRepository.findByTypesContaining(type)) {       //For each recipe matching that type, add it if has the keywords
                        toKeep.put(r.hashCode(), r);
                        for (String s : search.getKeywords()) {
                            //if it is missing any of the keywords, remove it
                            if (!(Helpers.containsIgnoreCase(r.getTitle(), s) || Helpers.containsIgnoreCase(r.getDescription(), s))) {
                                toKeep.remove(r.hashCode(), r);
                                break;
                            }
                        }
                    }
                    valid.retainAll(toKeep.values());     //Get rid of anything not having this type
                    toKeep.clear();             //Clear toKeep to contain only those with the next type
                }
            } else {                //doesn't have types
                for (Recipe r : valid) {
                    toKeep.put(r.hashCode(), r);
                    for (String s : search.getKeywords()) {
                        //if it is missing any of the keywords, remove it
                        if (!(Helpers.containsIgnoreCase(r.getTitle(), s) || Helpers.containsIgnoreCase(r.getDescription(), s))) {
                            toKeep.remove(r.hashCode(), r);
                            break;
                        }
                    }
                }
                valid.retainAll(toKeep.values());
            }
        } else {    //doesn't have keywords
            if (search.getTypes() != null && !search.getTypes().isEmpty()) {       //has types
                for (String type : search.getTypes()) {         //Go through each type
                    //For each recipe matching that type, add it if has keyword
                    for(Recipe r : recipeRepository.findByTypesContaining(type)) {
                        toKeep.put(r.hashCode(), r);
                    }
                    valid.retainAll(toKeep.values());     //Get rid of anything not having this type
                    toKeep.clear();
                }
            }
        }
    }

    /**
     * Removes any Recipe r from valid if Person owner with id ownerId doesn't have
     * all of the Ingredients for the Recipe r
     * @param ownerId The id of the owner whose inventory we want to check
     * @param valid The List of currently valid Recipes
     */
    private void filterByInventory(Integer ownerId, List<Recipe> valid) {
        if(ownerId == null)
            return;
        Person p = personService.getPerson(ownerId);
        Map<Integer, Ingredient> inventory = new HashMap<>();
        for(Inventory i : p.getInventory()) {
            inventory.put(i.getIngredient().hashCode(), i.getIngredient());
        }
        Map<Integer, Recipe> toRemove = new HashMap<>();
        for(Recipe r : valid) {
            for(RInventory i : r.getInv()) {
                if(!inventory.containsKey(i.getIngredient().hashCode())) {
                    toRemove.put(r.hashCode(), r);
                    break;
                }
            }
        }
        valid.removeAll(toRemove.values());
    }



}
