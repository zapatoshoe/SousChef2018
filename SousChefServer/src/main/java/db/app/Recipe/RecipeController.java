package db.app.Recipe;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/recipes")
public class RecipeController {

    @Autowired
    private RecipeService recipeService;

    @RequestMapping("/{ownerId}/add")
    public void addRecipe(@RequestBody Recipe recipe, @PathVariable Integer ownerId) {
        recipeService.addRecipe(recipe, ownerId);
    }



}
