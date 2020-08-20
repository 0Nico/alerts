package com.safetynet.alerts.dao;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.type.CollectionType;
import com.fasterxml.jackson.databind.type.TypeFactory;
import com.safetynet.alerts.model.Person;

public class PersonRepository {

	final ObjectMapper mapper = new ObjectMapper(); 
	File newState = new File("src/main/resources/data/persons.json");
	
	
	
	public List<Person> getPersonList() throws JsonParseException, JsonMappingException, IOException{
		return Arrays.asList(mapper.readValue(newState, Person[].class));
	}
	
	
	
	public Person findPerson(String lastName, String firstName) throws JsonParseException, JsonMappingException, IOException {
		
		List<Person> persons = getPersonList();
		Person foundPerson = persons.stream().findFirst().filter(pers -> 
			pers.getFirstName().equals(firstName.substring(0, 1).toUpperCase() + firstName.substring(1)) 
			&& pers.getLastName().equals(lastName.substring(0, 1).toUpperCase() + lastName.substring(1))).orElse(null);
		
		return foundPerson;
	};
	
	
	public void deletePerson(String lastName, String firstName) throws JsonParseException, JsonMappingException, IOException {
		
		List<Person> persons = getPersonList();
		
		Person foundPerson = persons.stream().findFirst().filter(pers -> 
			pers.getFirstName().equals(firstName.substring(0, 1).toUpperCase() + firstName.substring(1)) 
			&& pers.getLastName().equals(lastName.substring(0, 1).toUpperCase() + lastName.substring(1))).orElse(null);
		
		persons.remove(foundPerson);
		mapper.writeValue(newState, persons);
		
	};
	
	public void createPerson(Person person) throws JsonParseException, JsonMappingException, IOException {
		
		List<Person> persons = getPersonList();
		
		persons.add(person);
		mapper.writeValue(newState, persons);
		
	};
	
	public void updatePerson(Person person) throws JsonParseException, JsonMappingException, IOException {
		
		List<Person> persons = getPersonList();
		
		Person foundPerson = persons.stream().findFirst().filter(pers -> 
		pers.getFirstName().equals(person.getFirstName()) 
		&& pers.getLastName().equals(person.getLastName())).orElse(null);
	
		persons.remove(foundPerson);
		persons.add(person);
		mapper.writeValue(newState, persons);
		
	};
	
}
