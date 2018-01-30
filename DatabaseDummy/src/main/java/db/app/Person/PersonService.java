package db.app.Person;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;

	public Person getPerson(String name) {
		return personRepository.findOne(name);
	}

	public List<Person> getAllPersons() {
		List<Person> l = new ArrayList<>();
		personRepository.findAll().forEach(l::add);
		return l;
	}

	public void addPerson(Person person) {
		personRepository.save(person);
	}

	public void updatePerson(Person person, String name) {
		personRepository.save(person);	//same as add but repository knows to update existing rows
	}

	public void deletePerson(String name) {
		personRepository.delete(name);
	}

}
