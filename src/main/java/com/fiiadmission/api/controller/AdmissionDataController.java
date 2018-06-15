package com.fiiadmission.api.controller;

import java.security.Principal;
import java.util.List;
import java.util.Optional;

import com.fiiadmission.api.dto.AdmissionDataDTO;
import com.fiiadmission.api.dto.UserDTO;
import com.fiiadmission.api.dto.mappers.AdmissionDataMapper;
import com.fiiadmission.api.dto.mappers.UserMapper;
import com.fiiadmission.api.exceptions.BadRequestException;
import com.fiiadmission.api.exceptions.NotFoundException;
import com.fiiadmission.domain.User;
import com.fiiadmission.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fiiadmission.domain.AdmissionData;
import com.fiiadmission.service.AdmissionDataService;

@RestController
@RequestMapping("/fii/admission")
public class AdmissionDataController {

	@Autowired
	private AdmissionDataService admissionService;

	@Autowired
	private UserService userService;

	@GetMapping
	public AdmissionDataDTO getAdmissionData(Principal principal) throws NotFoundException {
		User user = userService.findByUsername(principal.getName());
		AdmissionData admissionData = user.getAdmissionData();
		if(admissionData == null){
			throw new NotFoundException("Admission data does not exist");
		}
		return AdmissionDataMapper.INSTANCE.toAdmissionDataDtO(admissionData);
	}
	
	@GetMapping(value="/{userId}")
	public AdmissionDataDTO getAdmissionDataByUserId(@PathVariable Long userId) throws NotFoundException {
		User user = userService.findById(userId);
		AdmissionData admissionData = user.getAdmissionData();
		if(admissionData == null){
			throw new NotFoundException("Admission data does not exist");
		}
		return AdmissionDataMapper.INSTANCE.toAdmissionDataDtO(admissionData);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public AdmissionDataDTO createAdmissionData(@RequestBody AdmissionDataDTO admissionDataDTO, Principal principal) throws BadRequestException {
		AdmissionData admissionData = AdmissionDataMapper.INSTANCE.toAdmissionData(admissionDataDTO);
		User user = userService.findByUsername(principal.getName());
		if(user.getAdmissionData() != null){
			throw new BadRequestException("User already has admission data");
		}
		admissionData.setUser(user);
		admissionData = admissionService.create(admissionData);
		AdmissionDataDTO data=AdmissionDataMapper.INSTANCE.toAdmissionDataDtO(admissionData);
		return data;
	}
	
	 @GetMapping(value ="/all")
	    //@PreAuthorize("hasAuthority('ADMIN_USER')")
	    public List<AdmissionDataDTO> getAllAdmissionData(){
	        return AdmissionDataMapper.INSTANCE.toAdmissionDataDTOs(admissionService.findAll());
	    }

}
