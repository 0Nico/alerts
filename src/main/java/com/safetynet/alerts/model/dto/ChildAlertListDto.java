package com.safetynet.alerts.model.dto;

import java.util.List;

public class ChildAlertListDto {
	
	private List<PersonRecordDto> children;
	private List<PersonRecordDto> otherMembers;
	
	public List<PersonRecordDto> getChildren() {
		return children;
	}
	public void setChildren(List<PersonRecordDto> children) {
		this.children = children;
	}
	public List<PersonRecordDto> getOtherMembers() {
		return otherMembers;
	}
	public void setOtherMembers(List<PersonRecordDto> otherMembers) {
		this.otherMembers = otherMembers;
	}
	
	
	
	
	
	

}
