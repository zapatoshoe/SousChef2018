package db.app.person;

import org.springframework.data.repository.CrudRepository;

import java.util.List;

public interface PersonRepository extends CrudRepository<Person, Integer>{

	/**
	 * Returns a list of people with the specified email 
	 * @param email The email in question
	 * @return A List of Persons with that email
	 */
	List<Person> findByEmail(String email);
	
}
