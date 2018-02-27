package db.app.Inventory;

import db.app.Ingredient.Ingredient;
import db.app.Ingredient.IngredientService;
import db.app.Person.Person;
import db.app.Person.PersonService;
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
    @Autowired
    private IngredientService ingredientService;

    public List<Inventory> getInventory(Integer ownerId){
        return inventoryRepository.findByOwnerId(ownerId);
    }

    public List<Ingredient> getUserIngredients(Integer ownerId) {
        Person p = personService.getPerson(ownerId);
        List<Inventory> inventory = inventoryRepository.findByOwnerId(ownerId);
        List<Ingredient> ingredients = new ArrayList<>();
        for(Inventory i : inventory) {
            Ingredient ingredient = i.getIngredient();
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    public void addToInventory(Integer ownerId, String ingredientName) {
        Inventory inventory = new Inventory();
        Person me = personService.getPerson(ownerId);
        inventory.setOwner(me);
        inventory.setIngredient(ingredientService.getIngredient(ingredientName));
        if(inventory.getIngredient() == null)
            return;
        if(me.getInventory() == null || !me.getInventory().contains(inventory)) {
            inventoryRepository.save(inventory);
        }
    }

    public void deleteFromInventory(Integer ownerId, String ingredientName){
        List<Inventory> l = inventoryRepository.findByOwnerId(ownerId);
        if(l == null)
            return;
        for(Inventory i: l){
            if(i.getIngredient().getName().equals(ingredientName)){
                inventoryRepository.delete(i.getId());
                return;
            }
        }

    }

    public void deleteAllInventory(Integer ownerId){
        List<Inventory> l = inventoryRepository.findByOwnerId(ownerId);
        if(l == null)
            return;
        for(Inventory i: l){
            inventoryRepository.delete(i.getId());
        }
    }
}
