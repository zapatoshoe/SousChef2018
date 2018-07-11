package db.app.listItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
public class ListItemController {

    @Autowired
    private ListItemService listItemService;

    /**
     * Returns a Person's shopping list
     *
     * @param ownerId The id of the Person whose shopping list should be returned
     * @return A Person's shopping list
     */
    @RequestMapping("/{ownerId}")
    public List<ListItem> getUserList(@PathVariable Integer ownerId) {
        return listItemService.getUserList(ownerId);
    }

    /**
     * Adds item to Person's shopping list
     * Automatically checks if entry in item is an ingredient name
     * @param ownerId The id of the Person whose shopping list should be added to
     * @param item The Item to be added
     * @return The full ListItem that was added - including id field
     */
    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}")
    public ListItem addToUserList(@PathVariable Integer ownerId, @RequestBody ListItem item) {
        return listItemService.addToUserList(ownerId, item);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}/{recipeId}")
    public void addRecipeIngredientsToUserList(@PathVariable Integer ownerId, @PathVariable Integer recipeId) {
        listItemService.addRecipeIngredientsToUserList(ownerId, recipeId);
    }

    /**
     * Updates the fields of the ListItem in the database with itemId
     * to the specified fields
     * @param itemId The id of the ListItem to change
     * @param item The new credentials for that item
     */
    @RequestMapping(method = RequestMethod.PUT, value = "/{itemId}")
    public void updateListItem(@PathVariable Integer itemId, @RequestBody ListItem item) {
        listItemService.updateListItem(itemId, item);
    }

    /**
     * Removes the item from the database
     * @param itemId The id of the item to be removed
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{itemId}")
    public void deleteListItem(@PathVariable Integer itemId) {
        listItemService.deleteListItem(itemId);
    }

    /**
     * Removes all items in a Person's shopping list
     * @param ownerId The id of the Person' whose shopping list will be deleted
     */
    @RequestMapping(method = RequestMethod.DELETE, value = "/{ownerId}/deleteall")
    public void deleteAll(@PathVariable Integer ownerId) {
        listItemService.deleteAll(ownerId);
    }
}
