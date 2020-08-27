package com.safetynet.alerts.web;

import java.net.URI;
import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.dao.firestationDao.FirestationRepositoryInterface;
import com.safetynet.alerts.model.Firestation;

@RestController
@RequestMapping("/firestation")
public class FirestationController {
		
	@Autowired
	private FirestationRepositoryInterface firestationRepository;
	
	
	
	@RequestMapping(method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public void updateFirestation(@RequestBody Firestation firestation) throws URISyntaxException {
		
		firestationRepository.updateFirestation(firestation);
		//return ResponseEntity.created(new URI("Firestation " + firestation.getAddress() + " succefully updated.")).build();
	}
	
	
	@RequestMapping(method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public void createFirestation(@RequestBody Firestation firestation) {
		
		firestationRepository.createFirestation(firestation);
	}
	
	
	@RequestMapping( method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void deleteFirestation(@RequestBody Firestation firestation) {
		
		firestationRepository.deleteFirestation(firestation);
	}
}
