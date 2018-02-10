package db.app.Inventory;

import db.app.Ingredient.Ingredient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InventoryService {

    @Autowired
    private InventoryRepository inventoryRepository;

    public List<Inventory> getInventory(Integer ownerId){
        return inventoryRepository.findByOwnerId(ownerId);
    }

    public void addToInventory(Ingredient ingredient, Integer ownerId) {
        //TODO Use methods from Inventory and Ingredient packages to add this Ingredient to owners Inventory
    }
}
