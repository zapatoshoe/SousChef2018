package db.app.ingredient;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> l = new ArrayList<>();
        ingredientRepository.findAll().forEach(l::add);
        Collections.sort(l);
        return l;
    }

    public Ingredient getIngredient(String name){
        List<Ingredient> list = ingredientRepository.findByName(name);
        return list.isEmpty() ? null : list.get(0);     //should only be one ingredient with that name
    }

    public void addIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(String name){
        List<Ingredient> list = ingredientRepository.findByName(name);
        ingredientRepository.delete(list.get(0).getId());
    }

    public List<Ingredient> getAllByType(IngredientType type) {
        List<Ingredient> l = ingredientRepository.findByType(type);
        Collections.sort(l);
        return l;
    }
}
