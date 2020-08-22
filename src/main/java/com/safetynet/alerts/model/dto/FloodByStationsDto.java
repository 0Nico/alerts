package com.safetynet.alerts.model.dto;

import java.util.List;

public class FloodByStationsDto {

	private int stationNumber;
	private List<PersonByStationDto> houses;
	
	public int getStationNumber() {
		return stationNumber;
	}
	public void setStationNumber(int stationNumber) {
		this.stationNumber = stationNumber;
	}
	public List<PersonByStationDto> getHouses() {
		return houses;
	}
	public void setHouses(List<PersonByStationDto> houses) {
		this.houses = houses;
	}
	
	
}
