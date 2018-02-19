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

    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}/{ingredientName}")
    public void addToInventory(@PathVariable Integer ownerId, @PathVariable String ingredientName, @RequestBody Inventory inventory) {
        inventoryService.addToInventory(inventory, ingredientName, ownerId);
    }

    @RequestMapping(method=RequestMethod.DELETE, value="/{ownerId}/{ingredientName}")
    public void deleteFromInventory(@PathVariable Integer ownerId, @PathVariable String ingredientName){
        inventoryService.deleteFromInventory(ownerId, ingredientName);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{ownerId}/deleteall")
    public void deletePersonInventory(@PathVariable Integer ownerId) {
        inventoryService.deleteAllInventory(ownerId);
    }
}
