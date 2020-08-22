package com.safetynet.alerts.dao.medicalrecordDao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Medicalrecord;

@Repository
public interface MedicalrecordRepositoryInterface {

	List<Medicalrecord> getMedicalrecordsList();
	
	void deleteMedicalrecord(Medicalrecord medicalrecord);
	
	void createMedicalrecord(Medicalrecord medicalrecord);
	void updateMedicalrecord(Medicalrecord medicalrecord);
}
