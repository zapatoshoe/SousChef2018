package db.app.Inventory;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface InventoryRepository extends CrudRepository<Inventory, Integer> {

    List<Inventory> findByOwnerId(Integer ownerId);

}
