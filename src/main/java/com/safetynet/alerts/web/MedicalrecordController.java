package com.safetynet.alerts.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.safetynet.alerts.dao.medicalrecordDao.MedicalrecordRepositoryInterface;
import com.safetynet.alerts.model.Medicalrecord;

@RestController
@RequestMapping("/medicalRecord")
public class MedicalrecordController {
		
	@Autowired
	private MedicalrecordRepositoryInterface medicalrecordRepository;
	
	
	
	@RequestMapping(method = RequestMethod.PUT, produces=MediaType.APPLICATION_JSON_VALUE)
	public void updateMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		
		medicalrecordRepository.updateMedicalrecord(medicalrecord);
	}
	
	
	@RequestMapping(method = RequestMethod.POST, produces=MediaType.APPLICATION_JSON_VALUE)
	public void createMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		
		medicalrecordRepository.createMedicalrecord(medicalrecord);
	}
	
	
	@RequestMapping( method = RequestMethod.DELETE, produces=MediaType.APPLICATION_JSON_VALUE)
	public void deleteMedicalrecord(@RequestBody Medicalrecord medicalrecord) {
		
		medicalrecordRepository.deleteMedicalrecord(medicalrecord);
	}
}
