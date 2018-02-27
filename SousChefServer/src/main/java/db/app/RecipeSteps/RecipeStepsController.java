package db.app.RecipeSteps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/steps")
public class RecipeStepsController {

    @Autowired
    RecipeStepsService recipeStepsService;

    @RequestMapping("/{recipeId}")
    public List<RecipeSteps> getRecipeSteps(@PathVariable Integer recipeId){
        recipeStepsService.getRecipeSteps(recipeId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{recipeId}")
    public void addRecipeStep(@PathVariable Integer recipeId, @RequestBody RecipeSteps recipeSteps){
        recipeStepsService.addRecipeStep(recipeId, recipeSteps);
    }
}
