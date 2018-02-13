package db.app.Inventory;

import db.app.Ingredient.IngredientService;
import db.app.Ingredient.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(path="/inventory")
public class InventoryController {

    @Autowired
    private InventoryService inventoryService;
    @Autowired
    private IngredientService ingredientService;
/**
    @RequestMapping("/{ownerId}")
    public List<Ingredient> getAllIngredients(@PathVariable Integer ownerId) {
        return ingredientService.getAllIngredients(inventoryService.getInventory(ownerId));
    }
**/
}
