package db.app.recipeSteps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/steps")
public class RecipeStepsController {

    @Autowired
    RecipeStepsService recipeStepsService;

    @RequestMapping("/{recipeStepId}")
    public RecipeSteps getStep(@PathVariable Integer recipeStepId){
        return recipeStepsService.getStep(recipeStepId);
    }

    @RequestMapping("/recipe/{recipeId}")
    public List<RecipeSteps> getRecipeSteps(@PathVariable Integer recipeId){
        return recipeStepsService.getRecipeSteps(recipeId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/recipe/{recipeId}")
    public void addRecipeStep(@PathVariable Integer recipeId, @RequestBody RecipeSteps recipeSteps){
        recipeStepsService.addRecipeStep(recipeId, recipeSteps);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/recipe/{recipeId}/{stepNumber}")
    public void addStepToRecipe(@PathVariable Integer recipeId, @PathVariable Integer stepNumber, @RequestBody RecipeSteps step){
        recipeStepsService.addStepToRecipe(recipeId, stepNumber, step);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{recipeStepId}")
    public void updateStep(@PathVariable Integer recipeStepId, @RequestBody RecipeSteps newStep){
        recipeStepsService.updateStep(recipeStepId, newStep);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/recipe/{recipeId}")
    public void deleteRecipeSteps(@PathVariable Integer recipeId){
        recipeStepsService.deleteRecipeSteps(recipeId);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{recipeStepId}")
    public void deleteStep(@PathVariable Integer recipeStepId){
        recipeStepsService.deleteStep(recipeStepId);
    }
}
