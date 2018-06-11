package com.fiiadmission.api.dto;

import java.sql.Timestamp;

public class SettingDTO {

	private Long id;
	  
	private Timestamp startDate;

	private Timestamp endDate;

	public Timestamp getStartDate() {
		return startDate;
	}

	public void setStartDate(Timestamp startDate) {
		this.startDate = startDate;
	}

	public Timestamp getEndDate() {
		return endDate;
	}

	public void setEndDate(Timestamp endDate) {
		this.endDate = endDate;
	}

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}
	

}
