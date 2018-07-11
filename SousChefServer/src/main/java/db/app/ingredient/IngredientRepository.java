package db.app.ingredient;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, Integer> {
    List<Ingredient> findByName(String name);
    List<Ingredient> findByType(IngredientType type);
}
