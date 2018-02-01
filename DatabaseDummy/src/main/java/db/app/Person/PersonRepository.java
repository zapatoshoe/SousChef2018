package db.app.Person;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface PersonRepository extends CrudRepository<Person, Integer>{

	List<Person> findByEmail(String email);
	
}
