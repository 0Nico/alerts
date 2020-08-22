package com.safetynet.alerts.dao.jsontools;

import org.springframework.stereotype.Component;

import com.safetynet.alerts.model.Database;

@Component
public interface JsonReaderInterface {
	
	Database getDatabase();
	
	void writeInJsonFile(Database database);

}
