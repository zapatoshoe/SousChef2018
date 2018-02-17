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
        List<Ingredient> ingredients = new ArrayList<>();
        for(Inventory i : inventory) {
            Ingredient ingredient = i.getIngredient();
            ingredients.add(ingredient);
        }
        return ingredients;
    }

    public void addToInventory(Ingredient ingredient, Integer ownerId) {
        Person me = personService.getPerson(ownerId);
        Inventory toAdd = new Inventory(me, ingredient);
        if(!me.getInventory().contains(toAdd)) {
            inventoryRepository.save(toAdd);
        }
    }

    public void deleteFromInventory(Integer ownerId, Ingredient ingredient){
        List<Inventory> l = inventoryRepository.findByOwnerId(ownerId);
        for(Inventory i: l){
            if(i.getIngredient().equals(ingredient)){
                inventoryRepository.delete(i.getId());
                return;
            }
        }

    }

    public void deleteAllInventory(Integer ownerId){
        List<Inventory> l = inventoryRepository.findByOwnerId(ownerId);
        for(Inventory i: l){
            inventoryRepository.delete(i.getId());
        }
    }

}
