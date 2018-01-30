package db.app.Item;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class ItemService {
	@Autowired
	private ItemRepository itemRepository;

	public Item getItem(String name) {
		return itemRepository.findOne(name);
	}

	public List<Item> getAllItems(String personId) {
		List<Item> l = new ArrayList<>();
		itemRepository.findByPersonName(personId).forEach(l::add);
		return l;
	}

	public void addItem(Item item) {
		itemRepository.save(item);
	}

	public void updateItem(Item item) {
		itemRepository.save(item);	//same as add but repository knows to update existing rows
	}

	public void deleteItem(String name) {
		itemRepository.delete(name);
	}

}
