package com.safetynet.alerts.model.dto;

import java.util.List;

public class PersonByAdressDto {

	private int firestationNumber;
	private List<PersonRecordDto> persons;
	
	public int getFirestationNumber() {
		return firestationNumber;
	}
	public void setFirestationNumber(int firestationNumber) {
		this.firestationNumber = firestationNumber;
	}
	public List<PersonRecordDto> getPersons() {
		return persons;
	}
	public void setPersons(List<PersonRecordDto> persons) {
		this.persons = persons;
	}
	
	
}
