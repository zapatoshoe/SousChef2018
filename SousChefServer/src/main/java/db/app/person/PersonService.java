package db.app.person;

import db.app.util.Helpers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;

	/**
	 * Returns the person with corresponding id, null if not found
	 * @param id The id to search for
	 * @return The person found or null if not found
	 */
	public Person getPerson(Integer id) {
		Person me = personRepository.findOne(id);
		if(me == null)
			return new Person();
		else {
			me.setVerbose(true);
			return me;
		}
	}


	public Person getPersonPreview(Integer id) {
		Person me = personRepository.findOne(id);
		if(me == null)
			return new Person();
		else {
			me.setVerbose(false);
			return me;
		}
	}

	/**
	 * Returns a comprehensive list of all Persons in repository
	 * @return A comprehensive list of all Persons in repository
	 */
	public List<Person> getAllPersons() {
		List<Person> l = new ArrayList<>();
		for(Person p : personRepository.findAll()) {
			p.setVerbose(false);
			l.add(p);
		}
		return l;
	}

	/**
	 * Inserts the person into the repository
	 * If the email is in use, does not insert the person and returns a person with uninitialized fields
	 * @param person The person to add
	 */
	public void addPerson(Person person) {
        if (!personRepository.findByEmail(person.getEmail()).isEmpty())    //if the email is already in use, return
			return;
		Helpers.convertStringToBlob(person);
		person.setCreated(new Date());
		personRepository.save(person);
	}

	/**
	 * Updates the existing person with the new information
	 * @param person The new information
	 * @param id The id of the person
	 */
	public void updatePerson(Person person, Integer id) {
		Person actual = personRepository.findOne(id);
		if(actual == null)
			return;
		if(person.getEmail() != null)
			actual.setEmail(person.getEmail());
		if(person.getImage() != null)
			actual.setImage(person.getImage());
		if(person.getName() != null)
			actual.setName(person.getName());
		if(person.getType() != null)
			actual.setType(person.getType());
		Helpers.convertStringToBlob(actual);
		personRepository.save(actual);	//same as add but repository knows to update existing rows
	}

	/**
	 * Removes the person with specified id from the repository
	 * @param id The id of the person to remove
	 */
	public void deletePerson(Integer id) {
			personRepository.delete(id);
	}

	/**
	 * Returns true if the specified person has the correct password
	 * @param person The person in question
	 * @return The complete person if good login, an uninitialized person otherwise
	 */
	public Person validLogin(Person person) {
		Person actual;
		List<Person> people = personRepository.findByEmail(person.getEmail());
		if(people == null || people.size() != 1)
			return new Person();
		actual = people.get(0);
		if(actual.getPassword().equals(person.getPassword())) {
			actual.setLastLogin(new Date());
			personRepository.save(actual);
			actual.setVerbose(true);
			return actual;
		}
		return new Person();
	}

}
