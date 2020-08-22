package com.safetynet.alerts.service;

import java.time.LocalDate;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.apache.catalina.startup.SetAllPropertiesRule;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import com.safetynet.alerts.dao.firestationDao.FirestationRepositoryInterface;
import com.safetynet.alerts.dao.medicalrecordDao.MedicalrecordRepositoryInterface;
import com.safetynet.alerts.dao.personDao.PersonRepositoryInterface;
import com.safetynet.alerts.model.Medicalrecord;
import com.safetynet.alerts.model.Person;
import com.safetynet.alerts.model.dto.ChildAlertListDto;
import com.safetynet.alerts.model.dto.FloodByStationsDto;
import com.safetynet.alerts.model.dto.PersonByAdressDto;
import com.safetynet.alerts.model.dto.PersonByStationDto;
import com.safetynet.alerts.model.dto.PersonRecordDto;

@Service
public class InformationService implements InformationServiceInterface {
	
	
	@Autowired
	private PersonRepositoryInterface personRepository;
	
	@Autowired
	private MedicalrecordRepositoryInterface medicalrecordRepository;
	
	@Autowired
	private FirestationRepositoryInterface firestationRepository;
	
	
	@Override
	public PersonRecordDto getPersonInfo(String firstName, String lastName) {
		
		PersonRecordDto personRecordDto = new PersonRecordDto();
		
		Person person = personRepository.getPersonsList().stream().filter(
				pers -> pers.getLastName().equals(lastName) && pers.getFirstName().equals(firstName)).findAny().orElse(null);
		
		personRecordDto.setFirstName(person.getFirstName());
		personRecordDto.setLastName(person.getLastName());
		personRecordDto.setAddress(person.getAddress());
		personRecordDto.setEmail(person.getEmail());
		setMedicByPerson(firstName, lastName, personRecordDto);
		
		return personRecordDto;
		
	}


	@Override
	public List<String> getCityEmails(String city) {
		
		List<String> emails = personRepository.getPersonsList().stream().filter(pers -> pers.getCity().equals(city))
				.map(pers -> pers.getEmail()).distinct().collect(Collectors.toList());
		return emails;
	}


	@Override
	public List<String> getNumberByStation(int firestationNumber) {
		 
		List<String> adresses = getAdressesByStationNumber(firestationNumber);
		
		List<String> numbers = personRepository.getPersonsList().stream().filter(
				pers -> adresses.contains(pers.getAddress())).map(pers -> pers.getPhone()).distinct().collect(Collectors.toList());
		return numbers;
	}


	@Override
	public ChildAlertListDto getChildrenByAdress(String adress) {
		
		ChildAlertListDto childAlertListDto = new ChildAlertListDto();
		
		List<PersonRecordDto> children = new ArrayList<PersonRecordDto>();
		List<PersonRecordDto> otherMembers = new ArrayList<PersonRecordDto>();
		personRepository.getPersonsList().stream().filter(pers -> pers.getAddress().equals(adress)).forEach(
				pers ->{
					PersonRecordDto personDto =  new PersonRecordDto();
					personDto.setFirstName(pers.getFirstName());
					personDto.setLastName(pers.getLastName());
					personDto.setAge(calculateAge(getBirthDate(pers.getFirstName(), pers.getLastName())));
					if(personDto.getAge() <= 18) {
						children.add(personDto);
					} else {
						otherMembers.add(personDto);
					}	
				}
		);
		childAlertListDto.setChildren(children);
		childAlertListDto.setOtherMembers(otherMembers);
		return childAlertListDto;
	}


	@Override
	public PersonByStationDto getPersonByFirestation(int station) {
		
		PersonByStationDto personByStationDto = new PersonByStationDto();
		personByStationDto.setStationNumber(station);
		personByStationDto.setAdultsCount(0);
		personByStationDto.setChildrenCount(0);
		List<PersonRecordDto> personList = new ArrayList<PersonRecordDto>();
		
		List<String> adresses = getAdressesByStationNumber(station);
		
		personRepository.getPersonsList().stream().filter(
				pers -> adresses.contains(pers.getAddress())).forEach(pers -> {
					PersonRecordDto personDto = new PersonRecordDto();
					personDto.setFirstName(pers.getFirstName());
					personDto.setLastName(pers.getLastName());
					personDto.setAddress(pers.getAddress());
					personDto.setPhone(pers.getPhone());
					if (calculateAge(getBirthDate(pers.getFirstName(), pers.getLastName())) > 18) {
						personByStationDto.setAdultsCount(personByStationDto.getAdultsCount() + 1);
					} else {
						personByStationDto.setChildrenCount(personByStationDto.getChildrenCount() + 1);
					}
					personList.add(personDto);
				});
		
		personByStationDto.setPersons(personList);
		return personByStationDto;
	}
	
	


	@Override
	public PersonByAdressDto getPersonByAdress(String adress) {
		
		PersonByAdressDto personByAdressDto = new PersonByAdressDto();
		List<PersonRecordDto> personList = new ArrayList<PersonRecordDto>();
		
		int station = firestationRepository.getFirestationsList().stream().filter( 
				fire -> fire.getAddress().equals(adress)).findAny().orElse(null).getStation();
		personByAdressDto.setFirestationNumber(station);
		
		personRepository.getPersonsList().stream().filter(
				pers -> adress.equals(pers.getAddress())).forEach(pers -> {
					PersonRecordDto personDto = new PersonRecordDto();
					personDto.setFirstName(pers.getFirstName());
					personDto.setLastName(pers.getLastName());
					personDto.setPhone(pers.getPhone());
					setMedicByPerson(pers.getFirstName(), pers.getLastName(), personDto);			
					personList.add(personDto);
				});
		
		personByAdressDto.setPersons(personList);
		
		return personByAdressDto;
	}


	@Override
	public List<FloodByStationsDto> getFloodByStations(List<Integer> stations) {
		
		List<FloodByStationsDto> floodList = new ArrayList<FloodByStationsDto>();
		stations.forEach(station -> {
			FloodByStationsDto floodDto = new FloodByStationsDto();
			floodDto.setStationNumber(station);
			
			List<PersonByStationDto> personList = new ArrayList<PersonByStationDto>();
			
			List<String> addresses = getAdressesByStationNumber(station);
			addresses.forEach(address -> {
				PersonByStationDto houseDto = new PersonByStationDto();
				houseDto.setAddress(address);
				List<PersonRecordDto> personDtoList = new ArrayList<PersonRecordDto>();
				personRepository.getPersonsList().stream().filter(pers -> pers.getAddress().equals(address)).forEach(pers -> {
					PersonRecordDto personDto = new PersonRecordDto();
					personDto.setFirstName(pers.getFirstName());
					personDto.setLastName(pers.getLastName());
					personDto.setPhone(pers.getPhone());
					setMedicByPerson(pers.getFirstName(), pers.getLastName(), personDto);
					personDtoList.add(personDto);
				});
				houseDto.setPersons(personDtoList);
				personList.add(houseDto);
			});
			floodDto.setHouses(personList);
			floodList.add(floodDto);
		});
		
		return floodList;
	}
	
	
	
	
	private List<String> getAdressesByStationNumber(int station){
		return firestationRepository.getFirestationsList().stream().filter(
				fire -> fire.getStation() == station).map(fire-> fire.getAddress()).collect(Collectors.toList());
	}
	
	
	private void setMedicByPerson(String firstName, String lastName, PersonRecordDto personDto) {
		
		Medicalrecord medicalRecord = medicalrecordRepository.getMedicalrecordsList().stream().filter(
				medic -> medic.getFirstName().equals(firstName) && medic.getLastName().equals(lastName)).findAny().orElse(null);
		
		personDto.setAllergies(medicalRecord.getAllergies());
		personDto.setMedications(medicalRecord.getMedications());
		personDto.setAge(calculateAge(medicalRecord.getBirthdate()));
	}
	
	
	
	private String getBirthDate(String firstName, String lastName) {
		
		return medicalrecordRepository.getMedicalrecordsList().stream().filter(
				medic -> medic.getFirstName().equals(firstName) && medic.getLastName().equals(lastName)).map(
						medic -> medic.getBirthdate()).findAny().orElse(null);
	}

	private int calculateAge(String birthDate) {
		return Period.between(LocalDate.parse(birthDate, DateTimeFormatter.ofPattern("MM/dd/yyyy")), LocalDate.now()).getYears();
	}

	

}
