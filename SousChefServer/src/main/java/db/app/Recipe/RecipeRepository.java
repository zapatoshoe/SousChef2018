package db.app.Recipe;

import db.app.Person.Person;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface RecipeRepository extends CrudRepository<Recipe, Integer>{

    public List<Recipe> findByOwner(Person person);

    public List<Recipe> findByDescriptionContaining(String word);
}
