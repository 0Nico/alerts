package com.safetynet.alerts.dao.medicalrecordDao;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import com.safetynet.alerts.dao.jsontools.JsonReaderInterface;
import com.safetynet.alerts.model.Database;
import com.safetynet.alerts.model.Medicalrecord;

@Component
public class MedicalrecordRepository implements MedicalrecordRepositoryInterface{

	
	@Autowired
	private JsonReaderInterface jsonReader;
	
	
	@Override
	public List<Medicalrecord> getMedicalrecordsList() {
		
		Database database = jsonReader.getDatabase();
		return database.getMedicalrecords();
		
	}
	
	@Override
	public Medicalrecord getMedicalrecord(Medicalrecord medicalrecord) {
		
		Database database = jsonReader.getDatabase();
		return database.getMedicalrecords().stream().filter(medic -> medic.equals(medicalrecord)).findFirst().orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MedicalRecord Not Found"));
	}

	@Override
	public void deleteMedicalrecord(Medicalrecord medicalrecord) {
		
		Database database = jsonReader.getDatabase();
		
		List<Medicalrecord> medicalrecords = database.getMedicalrecords();
		Medicalrecord foundMedicalrecord = medicalrecords.stream().filter(medic -> medic.equals(medicalrecord)).findFirst().orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MedicalRecord Not Found"));
		medicalrecords.remove(foundMedicalrecord);
		database.setMedicalrecords(medicalrecords);
		
		jsonReader.writeInJsonFile(database);
		
	}

	@Override
	public void createMedicalrecord(Medicalrecord medicalrecord) {
		
		Database database = jsonReader.getDatabase();
		
		List<Medicalrecord> medicalrecords = database.getMedicalrecords();
		
		medicalrecords.add(medicalrecord);
		database.setMedicalrecords(medicalrecords);
		jsonReader.writeInJsonFile(database);
		
	}

	@Override
	public void updateMedicalrecord(Medicalrecord medicalrecord) {

		Database database = jsonReader.getDatabase();
		
		List<Medicalrecord> medicalrecords = database.getMedicalrecords();
		
		Medicalrecord foundMedicalrecord = medicalrecords.stream().filter(medic -> medic.equals(medicalrecord)).findFirst().orElseThrow(
				() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "MedicalRecord Not Found"));
	
		medicalrecords.remove(foundMedicalrecord);
		medicalrecords.add(medicalrecord);
		database.setMedicalrecords(medicalrecords);
		
		jsonReader.writeInJsonFile(database);
		
	}

	


}
