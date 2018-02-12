package db.app.Ingredient;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    List<Ingredient> findByTpye(IngredientType type);
}
