package com.safetynet.alerts.web;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertThrows;
import static org.junit.Assert.assertTrue;

import java.util.ArrayList;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.web.server.ResponseStatusException;

import com.safetynet.alerts.dao.medicalrecordDao.MedicalrecordRepositoryInterface;
import com.safetynet.alerts.model.Medicalrecord;

public class MedicalrecordControllerTest extends AbstractTest{

	final String uri = "/medicalRecord";
	
	@Autowired
	MedicalrecordRepositoryInterface medicalrecordrepository;
	
	
		
	
	@Test
	public void updateMedicalrecord() throws Exception {
	   Medicalrecord medicalrecord = new Medicalrecord();
	   medicalrecord.setFirstName("John");
	   medicalrecord.setLastName("Boyd");
	   medicalrecord.setBirthdate("12/04/1965");
	   medicalrecord.setAllergies(new ArrayList<String>());
	   medicalrecord.getAllergies().add("dust");
	   medicalrecord.setMedications(new ArrayList<String>());
	   	   
	   String inputJson = super.mapToJson(medicalrecord);
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.put(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   
	   Medicalrecord updatedMedicalrecord = medicalrecordrepository.getMedicalrecord(medicalrecord);
	   assertEquals(updatedMedicalrecord.getBirthdate(), medicalrecord.getBirthdate());
	   
	}
	
	@Test
	public void createMedicalrecord() throws Exception {
		Medicalrecord medicalrecord = new Medicalrecord();
	    medicalrecord.setFirstName("Alfred");
	    medicalrecord.setLastName("Dupont");
	    medicalrecord.setBirthdate("01/01/1901");
	    medicalrecord.setAllergies(new ArrayList<String>());
	    medicalrecord.getAllergies().add("chocolate");
	    medicalrecord.setMedications(new ArrayList<String>());
	    medicalrecord.getMedications().add("doliprane:500mg");
	   	   
	    String inputJson = super.mapToJson(medicalrecord);
	    MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.post(uri)
	      .contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		   
	    int status = mvcResult.getResponse().getStatus();
	    assertEquals(200, status);
	    assertTrue(medicalrecordrepository.getMedicalrecord(medicalrecord).equals(medicalrecord));
	   
	}
	
	@Test
	public void deleteMedicalrecord() throws Exception {
		Medicalrecord medicalrecord = new Medicalrecord();
		medicalrecord.setFirstName("John");
		medicalrecord.setLastName("Boyd");
	   
		String inputJson = super.mapToJson(medicalrecord);
		MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.delete(uri)
				.contentType(MediaType.APPLICATION_JSON_VALUE).content(inputJson)).andReturn();
		
		int status = mvcResult.getResponse().getStatus();
		assertEquals(200, status);
		assertThrows(ResponseStatusException.class, () -> medicalrecordrepository.getMedicalrecord(medicalrecord));
	   
	}
}
