package com.safetynet.alerts.model.dto;

import java.util.List;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;

@JsonInclude(Include.NON_NULL)
public class PersonByStationDto {
	
	private Integer stationNumber;
	private List<PersonRecordDto> persons;
	private Integer adultsCount;
	private Integer childrenCount;
	private String address;
	
	public Integer getStationNumber() {
		return stationNumber;
	}
	public void setStationNumber(Integer stationNumber) {
		this.stationNumber = stationNumber;
	}
	public List<PersonRecordDto> getPersons() {
		return persons;
	}
	public void setPersons(List<PersonRecordDto> persons) {
		this.persons = persons;
	}
	public Integer getAdultsCount() {
		return adultsCount;
	}
	public void setAdultsCount(Integer adultsCount) {
		this.adultsCount = adultsCount;
	}
	public Integer getChildrenCount() {
		return childrenCount;
	}
	public void setChildrenCount(Integer childrenCount) {
		this.childrenCount = childrenCount;
	}
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	
	
	
	

}
