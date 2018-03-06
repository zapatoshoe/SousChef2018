package db.app.recipeFavorite;

import db.app.person.Person;
import db.app.person.PersonService;
import db.app.recipe.Recipe;
import db.app.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class FRecipeService {

    @Autowired
    private FRecipeRepository fRecipeRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private RecipeService recipeService;

    public List<Recipe> getFavorites(Integer ownerId) {
        List<Recipe> ret = new ArrayList<>();
        for (FRecipe f : fRecipeRepository.findByOwnerId(ownerId))
            ret.add(f.getRecipe());
        return ret;
    }

    public void addToFavorites(Integer ownerId, Integer recipeId) {
        FRecipe toAdd = new FRecipe();
        Person p = personService.getPerson(ownerId);
        Recipe r = recipeService.getRecipe(recipeId);
        if (p == null || r == null)
            return;
        toAdd.setOwner(p);
        toAdd.setRecipe(r);
        fRecipeRepository.save(toAdd);
    }

    public void removeFromFavorites(Integer ownerId, Integer recipeId) {
        for (FRecipe r : fRecipeRepository.findByOwnerId(ownerId)) {
            if (r.getRecipe().getId() == recipeId) {
                fRecipeRepository.delete(r);
                return;
            }
        }
    }
}
