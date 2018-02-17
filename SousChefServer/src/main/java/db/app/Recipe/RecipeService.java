package db.app.Recipe;

import db.app.Ingredient.Ingredient;
import db.app.Person.Person;
import db.app.Person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RecipeService {

    @Autowired
    private RecipeRepository recipeRepository;
    @Autowired
    private PersonService personService;


    public void addRecipe(Recipe recipe, Integer ownerId) {
        Person person = personService.getPerson(ownerId);
        recipe.setOwner(person);
        recipeRepository.save(recipe);
    }

    public void addIngredientToRecipe(Recipe recipe, Ingredient ingredient) {

    }
}
