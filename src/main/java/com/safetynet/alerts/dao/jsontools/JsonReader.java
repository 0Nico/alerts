package com.safetynet.alerts.dao.jsontools;

import java.io.File;
import java.io.IOException;

import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Database;

@Component
public class JsonReader implements JsonReaderInterface {
	
	File newState = new File("src/main/resources/data.json");
	ObjectMapper mapper = new ObjectMapper();
	
	
	@Override
	public Database getDatabase() {
		try {
			return mapper.readValue(newState, Database.class);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public void writeInJsonFile(Database database) {
		try {
			mapper.writeValue(newState, database);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
