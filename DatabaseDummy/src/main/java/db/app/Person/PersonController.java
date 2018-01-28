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
	
	@RequestMapping("/persons/{name}")
	public Person getPerson(@PathVariable String name) {
		return personService.getPerson(name);
	}
	
	@RequestMapping(method=RequestMethod.POST, value="/persons")
	public void addPerson(@RequestBody Person person) {
		personService.addPerson(person);
	}
	
	@RequestMapping(method=RequestMethod.PUT, value="/persons/{name}")
	public void updatePerson(@PathVariable String name, @RequestBody Person person) {
		personService.updatePerson(person, name);
	}
	
	@RequestMapping(method=RequestMethod.DELETE, value="/persons/{name}")
	public void deletePerson(@PathVariable String name) {
		personService.deletePerson(name);
	}
	
}
