package db.app.Inventory;

import db.app.Ingredient.Ingredient;
import db.app.Ingredient.IngredientRepository;
import db.app.Person.Person;
import db.app.Person.PersonService;
import org.hibernate.Hibernate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;
    @Autowired
    private PersonService personService;

    public List<Inventory> getInventory(Integer ownerId){
        return inventoryRepository.findByOwnerId(ownerId);
    }

    public List<Ingredient> getUserIngredients(Integer ownerId) {
        Person p = personService.getPerson(ownerId);
        List<Inventory> inventory = inventoryRepository.findByOwnerId(ownerId);
        List<Ingredient> ingredients = new ArrayList<Ingredient>();
        for(Inventory i : inventory) {
            Ingredient ingredient = i.getIngredient();
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    public void addToInventory(Ingredient ingredient, Integer ownerId) {
        Person me = personService.getPerson(ownerId);
        inventoryRepository.save(new Inventory(me, ingredient));
    }
}
