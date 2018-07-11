package db.app.recipeFavorite;

import db.app.recipe.Recipe;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * Controller for client to interface with the database to add, remove, and retrieve
 * favorited Recipes
 */
@RestController
@RequestMapping("/favorite")
public class FRecipeController {

    @Autowired
    private FRecipeService fRecipeService;

    /**
     * Returns all of a Person's favorite Recipes
     *
     * @param ownerId The id of the Person whose favorites should be gotten
     * @return The List of favorite Recipes for that Person
     */
    @RequestMapping("/{ownerId}")
    public List<Recipe> getFavorites(@PathVariable Integer ownerId) {
        return fRecipeService.getFavorites(ownerId);
    }

    /**
     * Adds the Recipe with recipeId to the Person with ownerId's favorites
     *
     * @param ownerId  The id of the Person to add a favorite to
     * @param recipeId The id of the Recipe to be favorited
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}/{recipeId}")
    public void addToFavorites(@PathVariable Integer ownerId, @PathVariable Integer recipeId) {
        fRecipeService.addToFavorites(ownerId, recipeId);
    }

    /**
     * Removes the Recipe with recipeId from the Person with ownerId's favorites
     *
     * @param ownerId  The id of the Person to remove a favorite from
     * @param recipeId The id of the Recipe to removed from favorites
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{ownerId}/{recipeId}")
    public void removeFromFavorites(@PathVariable Integer ownerId, @PathVariable Integer recipeId) {
        fRecipeService.removeFromFavorites(ownerId, recipeId);
    }
}
