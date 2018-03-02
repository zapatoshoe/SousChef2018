package db.app.listItem;

import db.app.ingredient.Ingredient;
import db.app.ingredient.IngredientService;
import db.app.person.Person;
import db.app.person.PersonService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ListItemService {

    @Autowired
    private ListItemRepository listItemRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private IngredientService ingredientService;

    public List<ListItem> getUserList(Integer ownerId) {
        List<ListItem> ret = listItemRepository.findByOwnerId(ownerId);
        ret.sort(ListItem.ListItemComparator);
        return ret;
    }

    public void addToUserList(Integer ownerId, ListItem item) {
        Person person = personService.getPerson(ownerId);
        if(person == null)
            return;
        Ingredient ingredient = ingredientService.getIngredient(item.getEntry());
        item.setIngredient(ingredient);     //ingredient will either be null or the corresponding ingredient
        item.setOwner(person);
        listItemRepository.save(item);
    }

    public void updateListItem(Integer itemId, ListItem newItem) {
        ListItem i = listItemRepository.findOne(itemId);
        i.setChecked(newItem.getChecked());
        i.setEntry(newItem.getEntry());
        i.setOrderNumber(newItem.getOrderNumber());
        Ingredient ingredient = ingredientService.getIngredient(newItem.getEntry());
        i.setIngredient(ingredient);
        listItemRepository.save(i);
    }

    public void deleteListItem(Integer itemId) {
        listItemRepository.delete(itemId);
    }

    public void deleteAll(Integer ownerId) {
        List<ListItem> items = listItemRepository.findByOwnerId(ownerId);
        if(items == null || items.isEmpty())
            return;
        for(ListItem i: items){
            listItemRepository.delete(i.getId());
        }
    }
}
