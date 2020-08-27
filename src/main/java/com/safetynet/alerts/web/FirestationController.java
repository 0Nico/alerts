package com.safetynet.alerts.web;

import java.net.URISyntaxException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.dao.firestationDao.FirestationRepositoryInterface;
import com.safetynet.alerts.model.Firestation;

@RestController
@RequestMapping("/firestation")
public class FirestationController {
		
	@Autowired
	private FirestationRepositoryInterface firestationRepository;
	
	
	
	@PutMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public void updateFirestation(@RequestBody Firestation firestation) throws URISyntaxException {
		
		firestationRepository.updateFirestation(firestation);
	}
	
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public void createFirestation(@RequestBody Firestation firestation) {
		
		firestationRepository.createFirestation(firestation);
	}
	
	
	@DeleteMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public void deleteFirestation(@RequestBody Firestation firestation) {
		
		firestationRepository.deleteFirestation(firestation);
	}
}
