package db.app.Ingredient;

import db.app.Inventory.Inventory;
import db.app.Person.Person;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

    public List<Ingredient> getAllIngredients() {
        List<Ingredient> l = new ArrayList<>();
        ingredientRepository.findAll().forEach(l::add);
        return l;
    }

    public Ingredient getIngredient(String name){
        return ingredientRepository.findOne(name);
    }

    public void addIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }

    public void deleteIngredient(String name){
        ingredientRepository.delete(name);
    }
}
