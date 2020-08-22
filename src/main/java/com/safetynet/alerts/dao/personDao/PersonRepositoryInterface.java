package com.safetynet.alerts.dao.personDao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Person;

@Repository
public interface PersonRepositoryInterface {


	List<Person> getPersonsList();
	
	void deletePerson(Person person);
	
	void createPerson(Person person);
	void updatePerson(Person person);
	
		
	
	
}
