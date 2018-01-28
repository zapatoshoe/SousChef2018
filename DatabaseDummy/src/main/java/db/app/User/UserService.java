package db.app.User;

import java.util.ArrayList;
import java.util.Arrays;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class UserService {
	
	@Autowired
	private UserRepository userRepository;
	
	private List<User> users = new ArrayList<>(Arrays.asList(
			new User("Ethan", "ethan@theshoemakers.com"),
			new User("Max", "mgarton1@iastate.edu")
			));
	
	public User getUser(String name) {
		for(User u : users) {
			if(u.getName().equals(name))
				return u;
		}
		return null;
	}
	
	public List<User> getAllUsers() {
//		return users;
		List<User> l = new ArrayList<>();
		userRepository.findAll().forEach(l::add);
		return l;
	}

	public void addUser(User user) {
//		users.add(user);
		userRepository.save(user);
	}

	public void updateUser(User user, String name) {
		for(int i=0; i<users.size(); i++) {
			User u = users.get(i);
			if(u.getName().equals(name)) {
				users.set(i, user);
				return;
			}
		}
	}

	public void deleteUser(String name) {
		for(int i=0; i<users.size(); i++) {
			User u = users.get(i);
			if(u.getName().equals(name)) {
				users.remove(i);
				return;
			}
		}
	}

}
