package db.app.review;

import db.app.person.Person;
import db.app.recipe.Recipe;
import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ReviewRepository extends CrudRepository<Review, Integer> {
    List<Review> findByRecipe(Recipe recipe);
    List<Review> findByOwner(Person person);
}
