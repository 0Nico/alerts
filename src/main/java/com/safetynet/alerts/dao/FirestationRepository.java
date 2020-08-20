package com.safetynet.alerts.dao;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Firestation;

public class FirestationRepository {

	final ObjectMapper mapper = new ObjectMapper(); 
	File newState = new File("src/main/resources/data/firestations.json");
	
	public List<Firestation> getFirestationList() throws JsonParseException, JsonMappingException, IOException{
		return Arrays.asList(mapper.readValue(newState, Firestation[].class));
	}

}
