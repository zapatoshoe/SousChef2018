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

    public List<Ingredient> getAllIngredients(List<Inventory> inv) {

        List<Ingredient> l = new ArrayList<>();
        l.add(new Ingredient(2));   //For testing purposes only
        l.add(new Ingredient(3));   //For testing purposes only
        for(Inventory i : inv) {
            l.add(ingredientRepository.findOne(i.getIngredientId()));
        }
        return l;
    }

}
