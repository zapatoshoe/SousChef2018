package db.app.ListItem;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/list")
public class ListItemController {

    @Autowired
    private ListItemService listItemService;

    @RequestMapping("/{ownerId}")
    public List<ListItem> getUserList(@PathVariable Integer ownerId) {
        return listItemService.getUserList(ownerId);
    }

    @RequestMapping(method = RequestMethod.POST, value = "/{ownerId}")
    public void addToUserList(@PathVariable Integer ownerId, @RequestBody ListItem item) {
        listItemService.addToUserList(ownerId, item);
    }

    @RequestMapping(method = RequestMethod.PUT, value = "/{itemId}")
    public void updateListItem(@PathVariable Integer itemId, @RequestBody ListItem item) {
        listItemService.updateListItem(itemId, item);
    }

    @RequestMapping(method = RequestMethod.DELETE, value = "/{itemId}")
    public void deleteListItem(@PathVariable Integer itemId) {
        listItemService.deleteListItem(itemId);
    }
}
