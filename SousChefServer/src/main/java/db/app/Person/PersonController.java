package db.app.Person;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * Handles any request at the url host/souschef/persons
 * @author ethan
 *
 */
@RestController
@RequestMapping(path="/persons")
public class PersonController {

	@Autowired
	private PersonService personService;
	
	/**
	 * Returns all users in the database
	 * Body - None
	 * @return A List of users in the database
	 */
	@RequestMapping("/all")
	public List<Person> getAllPersons() {
		return personService.getAllPersons();
	}
	
	/**
	 * Returns a Person object according to specified email
	 * Body - None
	 * @param id Which Person to get
	 * @return The Person object with that email
	 */
	@RequestMapping("/{id}")
	public Person getPerson(@PathVariable Integer id) {
		return personService.getPerson(id);
	}
	
	/**
	 * Inserts a Person into the database
	 * Body - None
	 * @param person The Person to be added
	 */
	@RequestMapping(method=RequestMethod.POST, value="/add")
	public void addPerson(@RequestBody Person person) {
		personService.addPerson(person);
	}
	
	/**
	 * Updates a Person's details with previous email specified
	 * Body - New Person
	 * @param id Person's unique id
	 * @param person Person's new specification
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public void updatePerson(@PathVariable Integer id, @RequestBody Person person) {
		personService.updatePerson(person, id);
	}
	
	/**
	 * Removes the Person from the database if they exist
	 * Body - None
	 * @param id The id corresponding to the Person to remove
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public void deletePerson(@PathVariable Integer id) {
		personService.deletePerson(id);
	}
	
	/**
	 * Returns the complete Person object if the Person in the body has the correct email and password
	 * Body - The Person (email and password only) to verify
	 * @param person The Person to verify
	 * @return Complete Person object if true, Uninitialized person if false
	 */
	@RequestMapping(method=RequestMethod.POST, value="/login")	//POST to include body (Person)
	public Person validLogin(@RequestBody Person person) {
		return personService.validLogin(person);
	}

}
