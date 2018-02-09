package db.app.Person;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.Base64;
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
	public Person addPerson(@RequestBody Person person) {
		return personService.addPerson(person);
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
	 * Returns true if the Person in the body has the correct email and password
	 * Body - The Person to verify
	 * @param person The Person to verify
	 * @return True if valid login, false otherwise
	 */
	@RequestMapping(method=RequestMethod.POST, value="/login")	//POST to include body (Person)
	public boolean validLogin(@RequestBody Person person) {
		return personService.validLogin(person);
	}

	//For converting image to Base64 for transmittal
	@RequestMapping("/test")
	public void setPicture() {
		File file = new File("image.jpg");
		String encodedfile = null;
		try {
			FileInputStream read = new FileInputStream(file);
			byte[] bytes = new byte[(int)file.length()];
			read.read(bytes);
			encodedfile = Base64.getEncoder().encodeToString(bytes);
		} catch (FileNotFoundException e) {
			e.printStackTrace();
		} catch (IOException e) {
			e.printStackTrace();
		}
		System.out.println(encodedfile);
	}
}
