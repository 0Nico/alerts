package com.safetynet.alerts.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import com.safetynet.alerts.dao.personDao.PersonRepositoryInterface;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.dto.PersonRecordDto;
import com.safetynet.alerts.service.InformationServiceInterface;

public class PersonControllerTest extends AbstractTest{

	final String uri = "/person";
	
	@Autowired
	PersonRepositoryInterface personRepository;
	
	
	
	@Test
	public void updatePerson() throws Exception {
	   Person person = new Person();
	   person.setFirstName("John");
	   person.setLastName("Boyd");
	   person.setCity("NYC");
	   person.setEmail("captainamerica@mail.com");
	   
	   String inputJson = super.mapToJson(person);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   
	   Person updatedPerson = personRepository.getPerson(person);
	   assertEquals(updatedPerson.getEmail(), person.getEmail());
	   assertEquals(updatedPerson.getCity(), person.getCity());
	   
	}
	
	@Test
	public void createPerson() throws Exception {
		Person person = new Person();
		person.setFirstName("Louis");
		person.setLastName("Bernard");
		person.setCity("Dijon");
		person.setEmail("undeux@mail.com");
		person.setAddress("8 rue du pont");
		person.setPhone("888-000-999");
		person.setZip("73846");      
		
	    String inputJson = super.mapToJson(person);
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		   
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
	    assertTrue(personRepository.getPerson(person).equals(person));
	   
	}
	
	@Test
	public void deletePerson() throws Exception {
		Person person = new Person();
		person.setFirstName("John");
		person.setLastName("Boyd");
	   
		String inputJson = super.mapToJson(person);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		assertThrows(ResponseStatusException.class, () -> personRepository.getPerson(person));
	   
	}
	
}
