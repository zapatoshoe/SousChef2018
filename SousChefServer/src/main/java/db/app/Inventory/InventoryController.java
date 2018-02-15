package db.app.Inventory;

import db.app.Ingredient.IngredientService;
import db.app.Ingredient.Ingredient;
import db.app.Person.Person;
import db.app.Person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    @RequestMapping("/{ownerId}")
    public List<Ingredient> getUserIngredients(@PathVariable Integer ownerId) {
        return inventoryService.getUserIngredients(ownerId);
    }

    @RequestMapping(value = "/{ownerId}", method = RequestMethod.POST)
    public void addToInventory(@PathVariable Integer ownerId, @RequestBody Ingredient ingredient) {
        inventoryService.addToInventory(ingredient, ownerId);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{ownerId}")
    public void deleteFromInventory(@PathVariable Integer ownerId, @RequestBody Ingredient ingredient){
        inventoryService.deleteFromInventory(ownerId, ingredient);
    }
/**
    @RequestMapping(method=RequestMethod.DELETE, value="/{ownerId}")
    public void deleteAllInventory(@PathVariable Integer ownerId){
        inventoryService.deleteAllInventory(ownerId);
    }
**/
}
