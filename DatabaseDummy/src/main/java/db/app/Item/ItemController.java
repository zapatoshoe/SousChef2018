package db.app.Item;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import db.app.Person.Person;


@RestController
public class ItemController {

	@Autowired
	private ItemService itemService;
	
	@RequestMapping("/persons/{name}/items")
	public List<Item> getAllItems(@PathVariable String name) {
		return itemService.getAllItems(name);
	}
	
	@RequestMapping("/persons/{name}/items/{itemId}")
	public Item getItem(@PathVariable String itemId) {
		return itemService.getItem(itemId);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/persons/{name}/items")
	public void addItem(@PathVariable String name, @RequestBody Item item) {
		item.setPerson(new Person(name, ""));	//make a new person with correct name
		itemService.addItem(item);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/persons/{name}/items/{itemId}")
	public void updateItem(@PathVariable String name, @PathVariable String itemId, @RequestBody Item item) {
		item.setPerson(new Person(name, ""));	//make a new person with correct name
		itemService.updateItem(item);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/persons/{name}/items/{itemId}")
	public void deleteItem(@PathVariable String itemId) {
		itemService.deleteItem(itemId);
	}
	
}
