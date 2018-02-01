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


@RestController
public class PersonController {

	@Autowired
	private PersonService personService;
	
	@RequestMapping("/persons")
	public List<Person> getAllPersons() {
		return personService.getAllPersons();
	}
	
	@RequestMapping("/persons/{email}")
	public Person getPerson(@PathVariable String email) {
		System.out.println(email);
		return personService.getPerson(email);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/persons")
	public void addPerson(@RequestBody Person person) {
		personService.addPerson(person);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/persons/{email}")
	public void updatePerson(@PathVariable String email, @RequestBody Person person) {
		personService.updatePerson(person, email);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/persons/{email}")
	public void deletePerson(@PathVariable String email) {
		personService.deletePerson(email);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/persons/login")	//POST to include body (Person)
	public boolean validLogin(@RequestBody Person person) {
		return personService.validLogin(person);
	}
	
}
