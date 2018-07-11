package db.app.inventory;

import db.app.ingredient.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;

    /**
     * Returns a list of all the ingredients a user owns
     * @param ownerId the ID of the owner to get the ingredients for
     * @return a list of all ingredients for that user
     */
    @RequestMapping("/{ownerId}")
    public List<Ingredient> getUserIngredients(@PathVariable Integer ownerId) {
        return inventoryService.getUserIngredients(ownerId);
    }

    /**
     * Adds an ingredient to a user's inventory
     * @param ownerId the ID of the person to add the ingredient for
     * @param ingredientName the name of the ingredient to be added
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}/{ingredientName}")
    public void addToInventory(@PathVariable Integer ownerId, @PathVariable String ingredientName) {
        inventoryService.addToInventory(ownerId, ingredientName);
    }

    /**
     * Add a list of ingredients to a user's inventory
     * @param ownerId the ID of the person to have the ingredients added
     * @param ingredients list of ingredients to be added to inventory
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}/addall")
    public void addAllToInventory(@PathVariable Integer ownerId, @RequestBody List<Ingredient> ingredients){
        if(ingredients == null || ingredients.isEmpty()){
            return;
        }
        for(Ingredient i: ingredients){
            inventoryService.addToInventory(ownerId, i.getName());
        }
    }

    /**
     * Removes an ingredient from a peron's inventory
     * @param ownerId the ID of the person to remove the ingredient from
     * @param ingredientName the name of the ingredient to remove
     */
    @RequestMapping(method=RequestMethod.DELETE, value="/{ownerId}/{ingredientName}")
    public void deleteFromInventory(@PathVariable Integer ownerId, @PathVariable String ingredientName){
        inventoryService.deleteFromInventory(ownerId, ingredientName);
    }

    /**
     * Removes all ingredients from a person's inventory
     * @param ownerId the ID of the person to remove the ingredients from
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{ownerId}/deleteall")
    public void deletePersonInventory(@PathVariable Integer ownerId) {
        inventoryService.deleteAllInventory(ownerId);
    }
}
