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

import com.safetynet.alerts.dao.firestationDao.FirestationRepositoryInterface;
import com.safetynet.alerts.model.Firestation;

public class FirestationControllerTest extends AbstractTest{

	final String uri = "/firestation";
	
	@Autowired
	private FirestationRepositoryInterface firestationRepository;
	

	
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
	   
	   Firestation updatedFirestation = firestationRepository.getFirestation(firestation);
	   assertEquals(firestation.getStation(), updatedFirestation.getStation());
	   
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
	    assertTrue(firestationRepository.getFirestation(firestation).equals(firestation));
		
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
		assertThrows(ResponseStatusException.class, () -> firestationRepository.getFirestation(firestation));
	   
	}
}
