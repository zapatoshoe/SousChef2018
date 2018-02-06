package db.app.Person;

import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


@Service
public class PersonService {
	@Autowired
	private PersonRepository personRepository;

	/**
	 * 
	 * @param email
	 * @return
	 */
	public Person getPerson(String email) {
		//get the person via email
		try {
			email = URLDecoder.decode(email, "UTF-8");
		} catch (UnsupportedEncodingException e) {}
		for(Person p : personRepository.findAll()) {
			if(p.getEmail().equals(email)) {
				return p;
			}
		}
		return null;
	}

	public List<Person> getAllPersons() {
		List<Person> l = new ArrayList<>();
		personRepository.findAll().forEach(l::add);
		return l;
	}

	public void addPerson(Person person) {
		try {
			person.setEmail(URLDecoder.decode(person.getEmail(), "UTF-8"));
		} catch (UnsupportedEncodingException e) {}
		personRepository.save(person);
	}

	public void updatePerson(Person person, String email) {
		personRepository.save(person);	//same as add but repository knows to update existing rows
	}

	public void deletePerson(String email) {
			try {
				email = URLDecoder.decode(email, "UTF-8");
			} catch (UnsupportedEncodingException e) {}
			Person toDelete = null;//get the person via email
			for(Person p : personRepository.findAll()) {
				if(p.getEmail().equals(email)) {
					toDelete = p;
					break;
				}
			}
			if(toDelete == null) return;
			personRepository.delete(toDelete.getId());
	}

	/**
	 * Returns true if the specified Person has the correct password
	 * @param person The person in question
	 * @return true if the specified Person has the correct password - false otherwise
	 * @throws UnsupportedEncodingException 
	 */
	public boolean validLogin(Person person) {
		Person actual = null;
		actual = getPerson(person.getEmail());
		if(actual == null) return false;	//no person found
		if(actual.getPassword().equals(person.getPassword()))	//if they have the same password - good login
			return true;
		return false;
	}

}
