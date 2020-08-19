package com.safetynet.alerts.web;

import java.io.IOException;

import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.safetynet.alerts.dao.PersonRepository;
import com.safetynet.alerts.model.Person;

@Controller
@RequestMapping("/persons/")
public class PersonController {
	
	private PersonRepository personRepository = new PersonRepository();
	
	@RequestMapping(value = "/{lastName:[a-z]+}/{firstName:[a-z]+}", method = RequestMethod.GET, produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public Person findPersonByName(@PathVariable("lastName") String lastName, @PathVariable("firstName") String firstName) throws JsonParseException, JsonMappingException, IOException{
		
		return personRepository.findByName(lastName, firstName);
	}

}
