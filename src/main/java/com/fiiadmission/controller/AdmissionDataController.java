package com.fiiadmission.controller;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.fiiadmission.domain.AdmissionData;
import com.fiiadmission.service.AdmissionDataService;

@RestController
@RequestMapping("/fii/admission")
@CrossOrigin(origins = "http://localhost:3000")
public class AdmissionDataController {

	@Autowired
	private AdmissionDataService admissionService;

	@RequestMapping(value = "/search", method = RequestMethod.GET)
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public List<AdmissionData> getUsers(@RequestParam(value = "firstname") Optional<String> firstName,
			@RequestParam("lastname") Optional<String> lastName) {
		return admissionService.findByName(firstName, lastName);
	}

}
