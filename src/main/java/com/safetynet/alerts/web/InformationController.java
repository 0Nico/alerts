package com.safetynet.alerts.web;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.model.dto.ChildAlertListDto;
import com.safetynet.alerts.model.dto.FloodByStationsDto;
import com.safetynet.alerts.model.dto.PersonByAdressDto;
import com.safetynet.alerts.model.dto.PersonByStationDto;
import com.safetynet.alerts.model.dto.PersonRecordDto;
import com.safetynet.alerts.service.InformationServiceInterface;

@RestController
public class InformationController {

	
	@Autowired
	private InformationServiceInterface informationService;
	
	
	
	@GetMapping(path = "/personInfo", produces=MediaType.APPLICATION_JSON_VALUE)
	public PersonRecordDto getPersonInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
		
		return informationService.getPersonInfo(firstName, lastName);
	}
	
		
	@GetMapping(path = "/communityEmail", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<String> getCommunityEmails(@RequestParam("city") String city) {
		
		return informationService.getCityEmails(city);
	}
	
	
	@GetMapping(path = "/phoneAlert", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<String> getPhonesByStation(@RequestParam("firestation_number") int firestationNumber) {
		
		return informationService.getNumberByStation(firestationNumber);
	}
	
	
	@GetMapping(path = "/childAlert", produces=MediaType.APPLICATION_JSON_VALUE)
	public ChildAlertListDto getChildrenByAdress(@RequestParam("address") String address) {
		
		return informationService.getChildrenByAdress(address);
	}
	

	@GetMapping(path = "/firestation", produces=MediaType.APPLICATION_JSON_VALUE)
	public PersonByStationDto getPersonByFirestation(@RequestParam("stationNumber") int stationNumber) {
		
		return informationService.getPersonByFirestation(stationNumber);
	}

	
	@GetMapping(path = "/fire", produces=MediaType.APPLICATION_JSON_VALUE)
	public PersonByAdressDto getPersonByAddress(@RequestParam("address") String address) {
		
		return informationService.getPersonByAdress(address);
	}
	
	
	@GetMapping(path = "/flood/stations", produces=MediaType.APPLICATION_JSON_VALUE)
	public List<FloodByStationsDto> getFloodByStation(@RequestParam("stations") List<Integer> stations ) {
		
		return informationService.getFloodByStations(stations);
	}

}
