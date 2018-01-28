package hello;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.springframework.stereotype.Service;

@Service
public class UserService {

	private List<User> users = new ArrayList<>(Arrays.asList(new User("Ethan", "1@gmail.com"), new User("Danny", "2@yahoo.com"),
			new User("Tanner", "3@hotmail.com"), new User("Max", "4@bing.com")));

	public List<User> getUsers() {
		return users;
	}

	public User getUser(String name) {
		// return users.stream().filter(t ->
		// t.getName().equals(name)).findFirst().get();

		for (User u : users) {
			if (u.getName().equals(name))
				return u;
		}
		return null;

	}

	public void addUser(User user) {
		users.add(user);		
	}

	public void updateUser(String name, User user) {
		for(int i=0; i<users.size(); i++) {
			User u = users.get(i);
			if(u.getName().equals(name)) {
				users.set(i, user);
				return;
			}
		}
	}

	public User deleteUser(String name) {
		User ret = null;
		for (User u : users) {
			if (u.getName().equals(name)) {
				users.remove(u);
				ret = u;
				break;
			}
		}
		return ret;
	}

}
