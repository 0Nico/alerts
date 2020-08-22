package com.safetynet.alerts.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.dao.personDao.PersonRepositoryInterface;
import com.safetynet.alerts.model.Person;

@RestController
@RequestMapping("/person")
public class PersonController {
	
	@Autowired
	private PersonRepositoryInterface personRepository;
	
	
	
	@RequestMapping(method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public void updatePerson(@RequestBody Person person) {
		
		personRepository.updatePerson(person);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public void createPerson(@RequestBody Person person) {
		
		personRepository.createPerson(person);
	}
	
	
	@RequestMapping( method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void deletePerson(@RequestBody Person person) {
		
		personRepository.deletePerson(person);
	}
	
	 
}
