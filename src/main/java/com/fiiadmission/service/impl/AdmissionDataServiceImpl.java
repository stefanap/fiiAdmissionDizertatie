package com.fiiadmission.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import com.fiiadmission.repository.AdmissionDataRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiiadmission.domain.AdmissionData;
import com.fiiadmission.domain.User;
import com.fiiadmission.service.AdmissionDataService;
import com.fiiadmission.service.UserService;
@Service
public class AdmissionDataServiceImpl implements AdmissionDataService {

	@Autowired
	AdmissionDataRepository admissionDataRepository;


	@Override
	public AdmissionData create(AdmissionData admissionData) {
		return admissionDataRepository.save(admissionData);
	}

	@Override
	public AdmissionData findById(Long id) {
		return admissionDataRepository.findOne(id);
	}

	@Override
	public List<AdmissionData> findAll() {
		return (List<AdmissionData>) admissionDataRepository.findAll();
	}
}
