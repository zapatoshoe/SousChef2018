package db.app.person;

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
	 * Returns a person object according to specified email
	 * Body - None
	 * @param id Which person to get
	 * @return The person object with that email
	 */
	@RequestMapping("/{id}")
	public Person getPerson(@PathVariable Integer id) {
		return personService.getPerson(id);
	}

	@RequestMapping("/{id}/preview")
	public Person getPersonPreview(@PathVariable Integer id) {
		return personService.getPersonPreview(id);
	}
	
	/**
	 * Inserts a person into the database
	 * Body - None
	 * @param person The person to be added
	 */
	@RequestMapping(method=RequestMethod.POST, value="/add")
	public void addPerson(@RequestBody Person person) {
		personService.addPerson(person);
	}
	
	/**
	 * Updates a person's details with previous email specified
	 * Body - New person
	 * @param id person's unique id
	 * @param person person's new specification
	 */
	@RequestMapping(method=RequestMethod.PUT, value="/{id}")
	public void updatePerson(@PathVariable Integer id, @RequestBody Person person) {
		personService.updatePerson(person, id);
	}
	
	/**
	 * Removes the person from the database if they exist
	 * Body - None
	 * @param id The id corresponding to the person to remove
	 */
	@RequestMapping(method=RequestMethod.DELETE, value="/{id}")
	public void deletePerson(@PathVariable Integer id) {
		personService.deletePerson(id);
	}
	
	/**
	 * Returns the complete person object if the person in the body has the correct email and password
	 * Body - The person (email and password only) to verify
	 * @param person The person to verify
	 * @return Complete person object if true, Uninitialized person if false
	 */
	@RequestMapping(method=RequestMethod.POST, value="/login")	//POST to include body (person)
	public Person validLogin(@RequestBody Person person) {
		return personService.validLogin(person);
	}

	/**
	 * Returns the Person with that email if the email exists
	 * @param person The Person having the email to check
	 * @return An uninitialized Person if no email found, the Person having that email otherwise
	 */
	@RequestMapping(method=RequestMethod.POST, value = "/check")
	public Person checkEmail(@RequestBody Person person) {return personService.checkEmail(person);}

}
