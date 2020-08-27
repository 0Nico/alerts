package com.safetynet.alerts.dao.firestationDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.safetynet.alerts.dao.jsontools.JsonReaderInterface;
import com.safetynet.alerts.model.Database;
import com.safetynet.alerts.model.Firestation;

@Component
public class FirestationRepository implements FirestationRepositoryInterface {


	@Autowired
	private JsonReaderInterface jsonReader;
	
	
	@Override
	public List<Firestation> getFirestationsList() {
		Database database = jsonReader.getDatabase();
		return database.getFirestations();
	}

	
	@Override
	public Firestation getFirestation(Firestation firestation) {
		Database database = jsonReader.getDatabase();
		return database.getFirestations().stream().filter( fire -> fire.equals(firestation)).findFirst().orElseThrow(
						() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Firestation Not Found"));
		
	}
	
	@Override
	public void deleteFirestation(Firestation firestation) {
		
		Database database = jsonReader.getDatabase();
		
		List<Firestation> firestations = database.getFirestations();
		Firestation foundFirestation = firestations.stream().filter(fire -> fire.equals(firestation)).findFirst().orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Firestation Not Found"));
		firestations.remove(foundFirestation);
		database.setFirestations(firestations);
		
		jsonReader.writeInJsonFile(database);
		
	}

	@Override
	public void createFirestation(Firestation firestation) {
		
		Database database = jsonReader.getDatabase();
		
		List<Firestation> firestations = database.getFirestations();
		
		firestations.add(firestation);
		database.setFirestations(firestations);
		jsonReader.writeInJsonFile(database);
		
	}

	@Override
	public void updateFirestation(Firestation firestation) {
		
		Database database = jsonReader.getDatabase();
		
		List<Firestation> firestations = database.getFirestations();
		
		Firestation foundFirestation = firestations.stream().filter(fire -> fire.equals(firestation)).findFirst().orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Firestation Not Found"));
		
		firestations.remove(foundFirestation);
		firestations.add(firestation);
		database.setFirestations(firestations);
		jsonReader.writeInJsonFile(database);
		
	}

	

	

}
