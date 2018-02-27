package db.app.Inventory;

import db.app.Ingredient.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

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
    public void addToInventory(@PathVariable Integer ownerId, @PathVariable String ingredientName) {
        inventoryService.addToInventory(ownerId, ingredientName);
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
