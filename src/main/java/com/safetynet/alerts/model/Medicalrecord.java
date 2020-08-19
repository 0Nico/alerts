package com.safetynet.alerts.model;

import java.util.Date;
import java.util.List;
import java.util.Map;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Medicalrecord {
	private String firstName;
	private String lastName;
	private Date birthdate;
	private Map<String,Integer> medications;
	private List<String> allergies;

}
