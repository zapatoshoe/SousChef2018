package db.app.recipeSteps;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/steps")
public class RecipeStepsController {

    @Autowired
    RecipeStepsService recipeStepsService;

    /**
     * Returns a specific RecipeSteps object
     * @param recipeStepId the ID of the desired RecipeSteps object
     * @return the desired RecipeSteps object
     */
    @RequestMapping("/{recipeStepId}")
    public RecipeSteps getStep(@PathVariable Integer recipeStepId){
        return recipeStepsService.getStep(recipeStepId);
    }

    /**
     * Returns a list of all the RecipeSteps for a specific recipe
     * @param recipeId the ID of the recipe to get the RecipeSteps
     * @return a list of the RecipeSteps for the desired recipe
     */
    @RequestMapping("/recipe/{recipeId}")
    public List<RecipeSteps> getRecipeSteps(@PathVariable Integer recipeId){
        return recipeStepsService.getRecipeSteps(recipeId);
    }

    /**
     * Creates a new RecipeSteps object as the NEWEST step for a recipe
     * @param recipeId the ID of the recipe the step is to be created for
     * @param recipeSteps the recipe step to be created (time, description)
     */
    @RequestMapping(method = RequestMethod.POST, value = "/recipe/{recipeId}")
    public void addRecipeStep(@PathVariable Integer recipeId, @RequestBody RecipeSteps recipeSteps){
        recipeStepsService.addRecipeStep(recipeId, recipeSteps);
    }

    /**
     * Creates a new recipe step at with a specific step number
     * @param recipeId the ID of the recipe the step is to be created for
     * @param stepNumber the step number for the recipe step
     * @param step the recipe step to be created (time, description)
     */
    @RequestMapping(method = RequestMethod.POST, value = "/recipe/{recipeId}/{stepNumber}")
    public void addStepToRecipe(@PathVariable Integer recipeId, @PathVariable Integer stepNumber, @RequestBody RecipeSteps step){
        recipeStepsService.addStepToRecipe(recipeId, stepNumber, step);
    }

    /**
     * Updates a current recipe step without changing its step number
     * @param recipeStepId the ID of the recipe step to be updated
     * @param newStep the new recipe step object (time, description)
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{recipeStepId}")
    public void updateStep(@PathVariable Integer recipeStepId, @RequestBody RecipeSteps newStep){
        recipeStepsService.updateStep(recipeStepId, newStep);
    }

    /**
     * Deletes all of the recipe steps for a specific recipe
     * @param recipeId the ID of the recipe the steps are to be deleted
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/recipe/{recipeId}")
    public void deleteRecipeSteps(@PathVariable Integer recipeId){
        recipeStepsService.deleteRecipeSteps(recipeId);
    }

    /**
     * Deletes a specific recipe step
     * @param recipeStepId the ID of the recipe step that is to be deleted
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{recipeStepId}")
    public void deleteStep(@PathVariable Integer recipeStepId){
        recipeStepsService.deleteStep(recipeStepId);
    }
}
