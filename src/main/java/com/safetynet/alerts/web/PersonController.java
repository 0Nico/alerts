package com.safetynet.alerts.web;

import java.util.List;

import javax.validation.Valid;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
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
	
	private Logger logger = LogManager.getLogger("PersonController");
	
	
	@RequestMapping(method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public void updatePerson(@Valid @RequestBody Person person) {
		
		personRepository.updatePerson(person);
		logger.info(person.getFirstName()+ " " + person.getLastName() + " informations succefully updated.");
	}
	
	
	@RequestMapping(method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public void createPerson(@Valid @RequestBody Person person) {
		
		personRepository.createPerson(person);
		logger.info(person.getFirstName()+ " " + person.getLastName() + " person succefully created.");
	}
	
	
	@RequestMapping( method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void deletePerson(@Valid @RequestBody Person person) {
		
		personRepository.deletePerson(person);
		logger.info(person.getFirstName()+ " " + person.getLastName() + " person succefully deleted.");
	}
	
	 
}
