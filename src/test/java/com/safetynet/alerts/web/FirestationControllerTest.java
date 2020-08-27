package com.safetynet.alerts.web;

import static org.junit.Assert.assertEquals;

import java.io.IOException;

import org.junit.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.safetynet.alerts.model.Firestation;
import com.safetynet.alerts.service.InformationServiceInterface;

public class FirestationControllerTest extends AbstractTest{

	final String uri = "/firestation";
	
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
	public void updateFirestation() throws Exception {
	   Firestation firestation = new Firestation();
	   firestation.setAddress("1509 Culver St");
	   firestation.setStation(4);
	   
	   String inputJson = super.mapToJson(firestation);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   
	}
	
	@Test
	public void createFirestation() throws Exception {
		Firestation firestation = new Firestation();
		firestation.setAddress("2302 Whasington parc");
	    firestation.setStation(1);
		   
	    String inputJson = super.mapToJson(firestation);
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		   
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
	   
	}
	
	@Test
	public void deleteFirestation() throws Exception {
		Firestation firestation = new Firestation();
		firestation.setAddress("1509 Culver St");
		firestation.setStation(3);
	   
		String inputJson = super.mapToJson(firestation);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
	   
	}
}
