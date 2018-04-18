package db.app.listItem;

import db.app.ingredient.Ingredient;
import db.app.ingredient.IngredientService;
import db.app.person.Person;
import db.app.person.PersonService;
import db.app.recipe.RInventory;
import db.app.recipe.Recipe;
import db.app.recipe.RecipeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ListItemService {

    @Autowired
    private ListItemRepository listItemRepository;
    @Autowired
    private PersonService personService;
    @Autowired
    private IngredientService ingredientService;
    @Autowired
    private RecipeService recipeService;

    /**
     * Returns all items for a specific Person
     *
     * @param ownerId The id of the Person whose list is desired
     * @return The user's list
     */
    public List<ListItem> getUserList(Integer ownerId) {
        List<ListItem> ret = listItemRepository.findByOwnerId(ownerId);
        if (ret == null)
            return new ArrayList<>();
        ret.sort(ListItem.ListItemComparator);
        return ret;
    }

    /**
     * Adds the specified ListItem to a Person's list
     *
     * @param ownerId The id of the Person whose list to be added to
     * @param item    The item to be added
     */
    public ListItem addToUserList(Integer ownerId, ListItem item) {
        Person person = personService.getPerson(ownerId);
        if (person == null)
            return null;
        Ingredient ingredient = ingredientService.getIngredient(item.getEntry());
        item.setIngredient(ingredient);     //ingredient will either be null or the corresponding ingredient
        item.setOwner(person);
        return listItemRepository.save(item);
    }

    public void addRecipeIngredientsToUserList(Integer ownerId, Integer recipeId) {
        List<ListItem> shoppingList = getUserList(ownerId); //sorted Shopping List
        Recipe recipe = recipeService.getRecipe(recipeId);
        if(recipe == null)
            return;
        List<RInventory> ingredients = recipe.getInv();
        int endOfIndex = shoppingList.isEmpty() ? 0 : shoppingList.get(shoppingList.size() - 1).getOrderNumber();    //get the largest order number to put the items at the end
        for(RInventory i : ingredients) {
            ListItem item = new ListItem();
            item.setChecked(false);
            Ingredient ingredient = ingredientService.getIngredient(i.getIngredientId());
            item.setEntry(ingredient.getName());
            if(!shoppingList.contains(item)) {
                item.setOrderNumber(++endOfIndex);
                addToUserList(ownerId, item);
            }
        }
    }

    /**
     * Changes the details of an item that already exists
     * @param itemId The id of the ListItem to modify
     * @param newItem The new details to place in the existing item
     */
    public void updateListItem(Integer itemId, ListItem newItem) {
        ListItem i = listItemRepository.findOne(itemId);
        if (i == null)
            return;
        i.setChecked(newItem.getChecked());
        i.setEntry(newItem.getEntry());
        i.setOrderNumber(newItem.getOrderNumber());
        Ingredient ingredient = ingredientService.getIngredient(newItem.getEntry());
        i.setIngredient(ingredient);
        listItemRepository.save(i);
    }

    /**
     * Deletes an item of the table
     * @param itemId The id of the ListItem to be deleted
     */
    public void deleteListItem(Integer itemId) {
        listItemRepository.delete(itemId);
    }

    /**
     * Deletes all ListItems for a specific Person
     * @param ownerId Id of the owner
     */
    public void deleteAll(Integer ownerId) {
        List<ListItem> items = listItemRepository.findByOwnerId(ownerId);
        if(items == null || items.isEmpty())
            return;
        for(ListItem i: items){
            listItemRepository.delete(i.getId());
        }
    }
}
