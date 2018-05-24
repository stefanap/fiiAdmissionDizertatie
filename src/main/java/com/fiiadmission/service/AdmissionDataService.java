package com.fiiadmission.service;

import java.util.List;
import java.util.Optional;

import com.fiiadmission.domain.AdmissionData;
import com.fiiadmission.domain.User;

public interface AdmissionDataService {
	
	List<AdmissionData> findByName(Optional<String> firstName,Optional<String> lastName);

}
