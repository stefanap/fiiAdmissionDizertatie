package com.fiiadmission.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiiadmission.domain.AdmissionData;
import com.fiiadmission.domain.Setting;
import com.fiiadmission.repository.SettingRepository;
import com.fiiadmission.service.SettingService;

@Service
public class SettingServiceImpl implements SettingService{


	@Autowired
	private SettingRepository settingRepository;
	
	
	@Override
	public Setting findFirstBy() {
		return settingRepository.findFirstBy();
	}
	
	@Override
	public Setting save(Setting setting) {
		return settingRepository.save(setting);
	}

}
