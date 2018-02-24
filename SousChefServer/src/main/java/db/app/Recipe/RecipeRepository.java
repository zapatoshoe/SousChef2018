package db.app.Recipe;

import db.app.Person.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.Collection;
import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer>{

    List<Recipe> findByOwner(Person person);

    List<Recipe> findByDescriptionContaining(String word);

    List<Recipe> findByTypesContaining(String word);

    List<Recipe> findByTitleContaining(String keyword);
}
