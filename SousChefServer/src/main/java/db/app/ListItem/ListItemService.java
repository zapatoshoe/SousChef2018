package db.app.ListItem;

import db.app.Ingredient.Ingredient;
import db.app.Ingredient.IngredientService;
import db.app.Person.Person;
import db.app.Person.PersonService;
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
        return listItemRepository.findByOwnerId(ownerId);
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

    public void updateListItem(Integer itemId, ListItem item) {
        ListItem i = listItemRepository.findOne(itemId);
        i.setChecked(item.getChecked());
        i.setEntry(item.getEntry());
        Ingredient ingredient = ingredientService.getIngredient(item.getEntry());
        i.setIngredient(ingredient);
        listItemRepository.save(i);
    }

    public void deleteListItem(Integer itemId) {
        listItemRepository.delete(itemId);
    }
}
