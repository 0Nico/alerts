package com.safetynet.alerts.dao.firestationDao;

import java.util.List;

import org.springframework.stereotype.Repository;

import com.safetynet.alerts.model.Firestation;

@Repository
public interface FirestationRepositoryInterface {

	List<Firestation> getFirestationsList();
	
	Firestation getFirestation(Firestation firestation);
	void deleteFirestation(Firestation firestation);
	
	void createFirestation(Firestation firestation);
	void updateFirestation(Firestation firestation);
}
