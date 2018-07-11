package db.app.recipeSteps;

import db.app.recipe.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeStepsRepository extends CrudRepository<RecipeSteps, Integer> {
    List<RecipeSteps> findByRecipe(Recipe recipe);

}
