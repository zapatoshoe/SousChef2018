package db.app.recipeFavorite;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface FRecipeRepository extends CrudRepository<FRecipe, Integer> {
    List<FRecipe> findByOwnerId(Integer ownerId);
    List<FRecipe> findByRecipeId(Integer recipeId);
}
