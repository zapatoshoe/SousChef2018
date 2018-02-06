package db.app.Person;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

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
	 * @return
	 */
	@RequestMapping("/all")
	public List<Person> getAllPersons() {
		return personService.getAllPersons();
	}
	
	/**
	 * Returns a Person object according to specified email
	 * Body - None
	 * @param email Which Person to get
	 * @return The Person object with that email
	 */
	@RequestMapping("/{email}")
	public Person getPerson(@PathVariable String email) {
		//TODO Does not work as "ethshoe@iastate.edu" comes in as "ethshoe@iastate"
		System.out.println(email);
		return personService.getPerson(email);
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
	 * @param email Person's previous email
	 * @param person Person's new specification
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/{email}")
	public void updatePerson(@PathVariable String email, @RequestBody Person person) {
		personService.updatePerson(person, email);
	}
	
	/**
	 * Removes the Person from the database if they exist
	 * Body - None
	 * @param email The email corresponding to the Person to remove
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/{email}")
	public void deletePerson(@PathVariable String email) {
		personService.deletePerson(email);
	}
	
	/**
	 * Returns true if the Person in the body has the correct email and password
	 * Body - The Person to verify
	 * @param person The Person to verify
	 * @return True if valid login, false otherwise
	 */
	@RequestMapping(method=RequestMethod.POST, value="/login")	//POST to include body (Person)
	public boolean validLogin(@RequestBody Person person) {
		return personService.validLogin(person);
	}
	
}
