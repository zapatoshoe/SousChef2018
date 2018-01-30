package db.app.Item;

import java.util.List;
import org.springframework.data.repository.CrudRepository;

public interface ItemRepository extends CrudRepository<Item, String>{
	
	public List<Item> findByPersonName(String personId);

}
