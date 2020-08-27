package com.safetynet.alerts.web;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.dao.personDao.PersonRepositoryInterface;
import com.safetynet.alerts.model.Person;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonRepositoryInterface personRepository;
	
	
	
	@PutMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public void updatePerson(@RequestBody Person person) {
		
		personRepository.updatePerson(person);
	}
	
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public void createPerson(@RequestBody Person person) {
		
		personRepository.createPerson(person);
	}
	
	
	@DeleteMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public void deletePerson(@RequestBody Person person) {
		
		personRepository.deletePerson(person);
	}
	
	 
}
