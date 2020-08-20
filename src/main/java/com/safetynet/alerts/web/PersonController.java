package com.safetynet.alerts.web;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.dao.FirestationRepository;
import com.safetynet.alerts.dao.PersonRepository;
import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.model.Person;

@Controller
@RequestMapping("/person/")
public class PersonController {
	
	private PersonRepository personRepository = new PersonRepository();
	private FirestationRepository firestationRepository = new FirestationRepository();
	
	
	
	@RequestMapping(value = "/{lastName:[a-z]+}/{firstName:[a-z]+}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Person findPersonByName(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) throws JsonParseException, JsonMappingException, IOException{
		
		return personRepository.findPerson(lastName, firstName);
	}
	
	
	@RequestMapping(value = "/update", method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void updatePerson(@RequestBody Person person) throws JsonParseException, JsonMappingException, IOException{
		
		personRepository.updatePerson(person);
	}
	
	
	@RequestMapping(value = "/create", method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void createPerson(@RequestBody Person person) throws JsonParseException, JsonMappingException, IOException{
		
		personRepository.createPerson(person);
	}
	
	
	@RequestMapping(value = "/delete/{lastName:[a-z]+}/{firstName:[a-z]+}", method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void deletePerson(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) throws JsonParseException, JsonMappingException, IOException{
		
		personRepository.deletePerson(lastName, firstName);
	}
	
	@RequestMapping(value = "/phones/{station:[0-9]+}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> getPhoneNumberByFirestation(@PathVariable("station") int station) throws JsonParseException, JsonMappingException, IOException{
		
		List<Person> persons = personRepository.getPersonList();
		List<Firestation> firestations = firestationRepository.getFirestationList();
		List <String> adress = firestations.stream().filter(firestation -> firestation.getStation() == station).map(f -> f.getAddress()).collect(Collectors.toList());
		
		List<String> numbers = persons.stream().filter( p -> adress.contains(p.getAddress())).map(p -> p.getPhone()).distinct().collect(Collectors.toList());
		return numbers;
	}
	
	
	//child adress
	
	
	//community city 
}
