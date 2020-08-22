package com.safetynet.alerts.service;

import java.util.List;

import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.model.dto.ChildAlertListDto;
import com.safetynet.alerts.model.dto.FloodByStationsDto;
import com.safetynet.alerts.model.dto.PersonByAdressDto;
import com.safetynet.alerts.model.dto.PersonByStationDto;
import com.safetynet.alerts.model.dto.PersonRecordDto;

@Component
public interface InformationServiceInterface {

	
	PersonRecordDto getPersonInfo(String firstName, String lastName);
	
	List<String> getCityEmails(String city);
	
	List<String> getNumberByStation(int firestationNumber);
	
	ChildAlertListDto getChildrenByAdress(String adress);
	
	PersonByStationDto getPersonByFirestation(int station);
	
	PersonByAdressDto getPersonByAdress(String adress);
	
	List<FloodByStationsDto> getFloodByStations(List<Integer> stations);
}
