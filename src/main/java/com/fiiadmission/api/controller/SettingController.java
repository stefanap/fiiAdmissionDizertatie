package com.fiiadmission.api.controller;

import java.io.UnsupportedEncodingException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import com.fiiadmission.api.dto.SettingDTO;
import com.fiiadmission.api.dto.mappers.SettingMapper;
import com.fiiadmission.api.exceptions.BadRequestException;
import com.fiiadmission.api.exceptions.NotFoundException;
import com.fiiadmission.domain.Setting;
import com.fiiadmission.service.SettingService;
@RestController
@RequestMapping("/fii/settings")
@PreAuthorize("hasAuthority('ADMIN_USER')")
public class SettingController {

	@Autowired
	private SettingService settingService;

	@GetMapping
	public SettingDTO getSetting() throws NotFoundException {
		Setting setting = settingService.findFirstBy();
		if (setting == null) {
			throw new NotFoundException("Setting was not found");
		}

		return SettingMapper.INSTANCE.toSettingDto(setting);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public SettingDTO createSetting(@RequestBody SettingDTO settingDTO)
			throws UnsupportedEncodingException, BadRequestException {
		Setting newSetting = SettingMapper.INSTANCE.toSetting(settingDTO);
		Setting setting = settingService.findFirstBy();
		if (setting == null) {
			setting = new Setting();
		}
		{
			setting.setEndDate(newSetting.getEndDate());
			setting.setStartDate(newSetting.getStartDate());
			settingService.save(setting);
		}
		return SettingMapper.INSTANCE.toSettingDto(setting);
	}

}
