package com.fiiadmission.service.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiiadmission.domain.AdmissionData;
import com.fiiadmission.domain.User;
import com.fiiadmission.service.AdmissionDataService;
import com.fiiadmission.service.UserService;
@Service
public class AdmissionDataServiceImpl implements AdmissionDataService {

	@Autowired
	private UserService userService;

	@Override
	public List<AdmissionData> findByName(Optional<String> firstName, Optional<String> lastName) {
		List<AdmissionData> response=new ArrayList<AdmissionData>();
		List<User> users =userService.findByName(firstName, lastName);
		for(User user: users)
			response.add(user.getAdmissionData());
		return response;
	}
	
	
	
}
