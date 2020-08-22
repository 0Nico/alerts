package com.safetynet.alerts.dao.jsontools;

import java.io.File;
import java.io.IOException;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.stereotype.Component;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.safetynet.alerts.model.Database;

@Component
public class JsonReader implements JsonReaderInterface {
	
	private Logger logger = LogManager.getLogger("JsonReader");
	
	File newState = new File("src/main/resources/data.json");
	ObjectMapper mapper = new ObjectMapper();
	
	
	@Override
	public Database getDatabase() {
		try {
			return mapper.readValue(newState, Database.class);
		} catch (IOException e) {
			logger.error("Error while reading database file. Please check existence, extension of "+ newState.getPath());
			e.printStackTrace();
		}
		return null;
	}
	
	
	@Override
	public void writeInJsonFile(Database database) {
		try {
			mapper.writeValue(newState, database);
		} catch (IOException e) {
			logger.error("Error while writing in database file. Please check existence and read-only parameter of "+ newState.getPath());
			e.printStackTrace();
		}
	}

}
