package com.safetynet.alerts.dao.personDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.safetynet.alerts.dao.jsontools.JsonReaderInterface;
import com.safetynet.alerts.model.Database;
import com.safetynet.alerts.model.Person;

@Component
public class PersonRepository implements PersonRepositoryInterface{

	
	@Autowired
	private JsonReaderInterface jsonReader;
	
	@Override
	public List<Person> getPersonsList(){
		
		Database database = jsonReader.getDatabase();
		return database.getPersons();
	}
	
	@Override
	public void deletePerson(Person person) {
		
		Database database = jsonReader.getDatabase();
		
		List<Person> persons = database.getPersons();
		Person foundPerson = persons.stream().filter(pers -> pers.equals(person)).findFirst().orElse(null);
		persons.remove(foundPerson);
		database.setPersons(persons);
		
		jsonReader.writeInJsonFile(database);
		
	};
	
	@Override
	public void createPerson(Person person) {
		
		Database database = jsonReader.getDatabase();
		
		List<Person> persons = database.getPersons();
		
		persons.add(person);
		database.setPersons(persons);
		jsonReader.writeInJsonFile(database);
		
	};
	
	@Override
	public void updatePerson(Person person) {
		
		Database database = jsonReader.getDatabase();
		
		List<Person> persons = database.getPersons();
		
		Person foundPerson = persons.stream().filter(pers -> 
		pers.getFirstName().equals(person.getFirstName()) 
		&& pers.getLastName().equals(person.getLastName())).findFirst().orElse(null);
	
		persons.remove(foundPerson);
		persons.add(person);
		database.setPersons(persons);
		jsonReader.writeInJsonFile(database);
		
	};
	
}
