package db.app.Ingredient;

import db.app.Inventory.Inventory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class IngredientService {

    @Autowired
    private IngredientRepository ingredientRepository;

<<<<<<< HEAD
    public List<Ingredient> getAllIngredients() {
        List<Ingredient> l = new ArrayList<>();
        ingredientRepository.findAll().forEach(l::add);
=======
    public List<Ingredient> getAllIngredients(List<Inventory> inv) {

        List<Ingredient> l = new ArrayList<>();
        l.add(new Ingredient(2));   //For testing purposes only
        l.add(new Ingredient(3));   //For testing purposes only
        for(Inventory i : inv) {
            l.add(ingredientRepository.findOne(i.getIngredientId()));
        }
>>>>>>> master_db
        return l;
    }

    public Ingredient getIngredient(String name){
        return ingredientRepository.findOne(name);
    }

    public void addIngredient(Ingredient ingredient){
        ingredientRepository.save(ingredient);
    }

}
