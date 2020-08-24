package com.safetynet.alerts.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;

import java.io.IOException;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.dto.PersonRecordDto;
import com.safetynet.alerts.service.InformationServiceInterface;

public class PersonControllerTest extends AbstractTest{

	final String uri = "/person";
	
	@Autowired
	InformationServiceInterface informationService;
	
	@Override
    @BeforeEach
    public void setUp() {
       super.setUp();
       
       try {
    	   super.clearJsonDatabase();
       } catch (IOException e) {
    	   e.printStackTrace();
       }
    }
	
	
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
	   
	   PersonRecordDto personDto = informationService.getPersonInfo(person.getFirstName(), person.getLastName());
	   assertEquals(personDto.getEmail(), person.getEmail());
	   
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
		assertThrows(ResponseStatusException.class, () -> informationService.getPersonInfo(person.getFirstName(), person.getLastName()));
	   
	}
	
}
