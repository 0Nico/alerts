package com.safetynet.alerts.web;

import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;

import com.safetynet.alerts.model.dto.ChildAlertListDto;
import com.safetynet.alerts.model.dto.FloodByStationsDto;
import com.safetynet.alerts.model.dto.PersonByAdressDto;
import com.safetynet.alerts.model.dto.PersonByStationDto;
import com.safetynet.alerts.model.dto.PersonRecordDto;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;


public class InformationControllerTest extends AbstractTest{

	

	@Test
	public void getEmailsList() throws Exception {
	   String uri = "/communityEmail?city=Culver";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   
	   List<String> emailList = super.mapFromJson(content, List.class);
	   assertTrue(emailList.size()> 0);
	}

	@Test
	public void getPersonInfo() throws Exception {
	   String uri = "/personInfo?firstName=John&lastName=Boyd";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   
	   PersonRecordDto personDto = super.mapFromJson(content, PersonRecordDto.class);
	   assertTrue(personDto.getFirstName().equals("John") && personDto.getLastName().equals("Boyd"));
	}
	
	@Test
	public void getPhoneByStation() throws Exception {
	   String uri = "/phoneAlert?firestation_number=1";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   
	   List<String> phoneList = super.mapFromJson(content, List.class);
	   assertTrue(phoneList.size()> 0);
	}
	
	@Test
	public void getChildrenByAddress() throws Exception {
	   String uri = "/childAlert";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).param("address", "892 Downing Ct")
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   
	   ChildAlertListDto childList = super.mapFromJson(content, ChildAlertListDto.class);
	   assertTrue(childList.getChildren().size() > 0);
	   assertTrue(childList.getOtherMembers().size() > 0);
	}

	@Test
	public void getPersonByFirestation() throws Exception {
	   String uri = "/firestation?stationNumber=1";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   
	   PersonByStationDto personList = super.mapFromJson(content, PersonByStationDto.class);
	   assertTrue(personList.getAdultsCount()> 0);
	   assertTrue(personList.getPersons().size() > 0);
	}

	@Test
	public void getPersonByAddres() throws Exception {
	   String uri = "/fire";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri).param("address", "892 Downing Ct")
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   
	   PersonByAdressDto personList = super.mapFromJson(content, PersonByAdressDto.class);
	   assertTrue(personList.getPersons().size() > 0);
	}

	@Test
	public void getFloodByStation() throws Exception {
	   String uri = "/flood/stations?stations=2&stations=4";
	   MvcResult mvcResult = mvc.perform(MockMvcRequestBuilders.get(uri)
	      .accept(MediaType.APPLICATION_JSON_VALUE)).andReturn();
	   
	   int status = mvcResult.getResponse().getStatus();
	   assertEquals(200, status);
	   String content = mvcResult.getResponse().getContentAsString();
	   
	   List<FloodByStationsDto> floodList = super.mapFromJson(content, List.class);
	   assertTrue(floodList.size() > 0);
	   
	}

}