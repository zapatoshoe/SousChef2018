package db.app.RecipeSteps;

import db.app.Recipe.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeStepsRepository extends CrudRepository<RecipeSteps, Integer> {
    List<RecipeSteps> findByRecipe(Recipe recipe);

}
