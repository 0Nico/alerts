package com.safetynet.alerts.web;

import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.server.ResponseStatusException;

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
	
	private Logger logger = LogManager.getLogger("InformationController");
	
	
	@GetMapping(path = "/personInfo", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PersonRecordDto getPersonInfo(@RequestParam("firstName") String firstName, @RequestParam("lastName") String lastName) {
		
		return informationService.getPersonInfo(firstName, lastName);
	}
	
		
	@GetMapping(path = "/communityEmail", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> getCommunityEmails(@RequestParam("city") String city) {
		
		List<String> list = informationService.getCityEmails(city);
		if(list.isEmpty()) {
			logger.error("No city & communites in our database for : " + city);
			throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Please retry with another word or spell");
		}
		return list;
	}
	
	
	@GetMapping(path = "/phoneAlert", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<String> getPhonesByStation(@RequestParam("firestation_number") int firestationNumber) {
		
		return informationService.getNumberByStation(firestationNumber);
	}
	
	
	@GetMapping(path = "/childAlert", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public ChildAlertListDto getChildrenByAdress(@RequestParam("address") String address) {
		
		return informationService.getChildrenByAdress(address);
	}
	

	@GetMapping(path = "/firestation", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PersonByStationDto getPersonByFirestation(@RequestParam("stationNumber") int stationNumber) {
		
		return informationService.getPersonByFirestation(stationNumber);
	}

	
	@GetMapping(path = "/fire", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public PersonByAdressDto getPersonByAddress(@RequestParam("address") String address) {
		
		return informationService.getPersonByAdress(address);
	}
	
	
	@GetMapping(path = "/flood/stations", produces=MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public List<FloodByStationsDto> getFloodByStation(@RequestParam("stations") List<Integer> stations ) {
		
		return informationService.getFloodByStations(stations);
	}

	
	
	
	
}
