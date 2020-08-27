package com.safetynet.alerts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.dao.medicalrecordDao.MedicalrecordRepositoryInterface;
import com.safetynet.alerts.model.Medicalrecord;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalrecordController {
		
	@Autowired
	private MedicalrecordRepositoryInterface medicalrecordRepository;
	
	
	
	@PutMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public void updateMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		
		medicalrecordRepository.updateMedicalrecord(medicalrecord);
	}
	
	
	@PostMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public void createMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		
		medicalrecordRepository.createMedicalrecord(medicalrecord);
	}
	
	
	@DeleteMapping(produces=MediaType.APPLICATION_JSON_VALUE)
	public void deleteMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		
		medicalrecordRepository.deleteMedicalrecord(medicalrecord);
	}
}
