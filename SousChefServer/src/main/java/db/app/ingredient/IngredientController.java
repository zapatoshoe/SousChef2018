package db.app.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/ingredients")
public class IngredientController {

    @Autowired
    private IngredientService ingredientService;

    /**
     * Returns a list of all the  ingredients in the database
     * @return a list of all the ingredients in the database
     */
    @RequestMapping("/all")
    public List<Ingredient> getAllIngredients(){
        return ingredientService.getAllIngredients();
    }

    /**
     * Returns a list of all the ingredients of a specific type
     * @param type the type of the ingredients to be returned
     * @return a list of all ingredients of the specified type
     */
    @RequestMapping("/type/{type}")
    public List<Ingredient> getAllByType(@PathVariable IngredientType type) {
        return ingredientService.getAllByType(type);
    }

    /**
     * Returns a specific ingredient
     * @param name the name of the ingredient to be returned
     * @return the ingredient with the specified name
     */
    @RequestMapping("/{name}")
    public Ingredient getIngredient(@PathVariable String name){
        return ingredientService.getIngredient(name);
    }

    /**
     * Adds an ingredient to the database
     * @param ingredient the ingredient to be added to the database (name, type)
     */
    @RequestMapping(method= RequestMethod.POST, value="/add")
    public void addIngredient(@RequestBody Ingredient ingredient){
        ingredientService.addIngredient(ingredient);
    }

    /**
     * Updates a current ingredient object
     * @param ingredientId the ID of the ingredient to be updated
     * @param ingredient the new ingredient to replace the old
     */
    @RequestMapping(method = RequestMethod.PUT, value="/{ingredientId}")
    public void updateIngredient(@PathVariable Integer ingredientId, @RequestBody Ingredient ingredient){
        ingredientService.updateIngredient(ingredientId, ingredient);
    }

    /**
     * Removes a specific ingredient from the database
     * @param name the name of the ingredient to be removed
     */
    @RequestMapping(method=RequestMethod.DELETE, value="/{name}")
    public void deleteIngredient(@PathVariable String name){
        ingredientService.deleteIngredient(name);
    }
}
