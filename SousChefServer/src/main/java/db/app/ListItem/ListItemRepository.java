package db.app.ListItem;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface ListItemRepository extends CrudRepository<ListItem, Integer> {
    List<ListItem> findByOwnerId(Integer ownerId);
}
