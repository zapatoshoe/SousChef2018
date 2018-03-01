package db.app.recipeSteps;

import db.app.recipe.Recipe;
import db.app.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RecipeStepsService {

    @Autowired
    private RecipeStepsRepository recipeStepsRepository;

    @Autowired
    private RecipeService recipeService;

    public List<RecipeSteps> getRecipeSteps(Integer recipeId) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        return recipeStepsRepository.findByRecipe(recipe);
    }

    public void addRecipeStep(Integer recipeId, RecipeSteps recipeSteps) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        if(recipe == null){
            return;
        }
        List<RecipeSteps> list = getRecipeSteps(recipeId);
        recipeSteps.setStepNumber(list.size() + 1);
        recipeSteps.setRecipe(recipe);
        recipeStepsRepository.save(recipeSteps);
    }

    public RecipeSteps getStep(Integer recipeStepId) {
        return recipeStepsRepository.findOne(recipeStepId);
    }

    public void updateStep(Integer recipeStepId, RecipeSteps newStep) {
        RecipeSteps old = recipeStepsRepository.findOne(recipeStepId);
        if(old == null){
            return;
        }
        old.setDescription(newStep.getDescription());
        old.setTime(newStep.getTime());
        recipeStepsRepository.save(old);
    }

    public void deleteRecipeSteps(Integer recipeId) {
        List<RecipeSteps> list = getRecipeSteps(recipeId);
        if(list.isEmpty()){
            return;
        }
        for(RecipeSteps step: list){
            recipeStepsRepository.delete(step.getId());
        }
    }

    public void addStepToRecipe(Integer recipeId, Integer stepNumber, RecipeSteps step) {
        Recipe recipe = recipeService.getRecipe(recipeId);
        if(recipe == null){
            return;
        }
        List<RecipeSteps> list = getRecipeSteps(recipeId);
        if(stepNumber > list.size() + 1){
            return;
        }
        for(int i = stepNumber - 1; i<list.size(); i++){
            list.get(i).setStepNumber(i + 2);
            recipeStepsRepository.save(list.get(i));
        }
        step.setStepNumber(stepNumber);
        step.setRecipe(recipe);
        recipeStepsRepository.save(step);
    }

    public void deleteStep(Integer recipeStepId) {
        RecipeSteps step = recipeStepsRepository.findOne(recipeStepId);
        List<RecipeSteps> list = getRecipeSteps(step.getRecipe().getId());
        for(int i = step.getStepNumber(); i<list.size(); i++){
            list.get(i).setStepNumber(i);
            recipeStepsRepository.save(list.get(i));
        }
        recipeStepsRepository.delete(recipeStepId);
    }
}
