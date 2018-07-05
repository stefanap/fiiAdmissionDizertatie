package com.fiiadmission.api.controller;

import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.security.Principal;
import java.sql.Timestamp;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import javax.servlet.http.HttpServletResponse;

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

	private static final String CSV_SEPARATOR = ",";

	@Autowired
	private AdmissionDataService admissionService;

	@Autowired
	private UserService userService;

	@GetMapping
	public AdmissionDataDTO getAdmissionData(Principal principal) throws NotFoundException {
		User user = userService.findByUsername(principal.getName());
		AdmissionData admissionData = user.getAdmissionData();
		if (admissionData == null) {
			throw new NotFoundException("Admission data does not exist");
		}
		return AdmissionDataMapper.INSTANCE.toAdmissionDataDtO(admissionData);
	}

	@GetMapping(value = "/{userId}")
	public AdmissionDataDTO getAdmissionDataByUserId(@PathVariable Long userId) throws NotFoundException {
		User user = userService.findById(userId);
		AdmissionData admissionData = user.getAdmissionData();
		if (admissionData == null) {
			throw new NotFoundException("Admission data does not exist");
		}
		return AdmissionDataMapper.INSTANCE.toAdmissionDataDtO(admissionData);
	}

	@PostMapping
	@ResponseStatus(value = HttpStatus.CREATED)
	public AdmissionDataDTO createAdmissionData(@RequestBody AdmissionDataDTO admissionDataDTO, Principal principal)
			throws BadRequestException {
		AdmissionData admissionData = AdmissionDataMapper.INSTANCE.toAdmissionData(admissionDataDTO);
		admissionData.setCreateDate(new Timestamp(new Date().getTime()));
		User user = userService.findByUsername(principal.getName());
		if (user.getAdmissionData() != null) {
			throw new BadRequestException("User already has admission data");
		}
		admissionData.setUser(user);
		admissionData = admissionService.create(admissionData);
		AdmissionDataDTO data = AdmissionDataMapper.INSTANCE.toAdmissionDataDtO(admissionData);
		return data;
	}

	@GetMapping(value = "/all")
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public List<AdmissionDataDTO> getAllAdmissionData() {
		return AdmissionDataMapper.INSTANCE.toAdmissionDataDTOs(admissionService.findAll());
	}

	@GetMapping(value = "/export")
	@PreAuthorize("hasAuthority('ADMIN_USER')")
	public void getDataForExport(HttpServletResponse response) {
		 //response.addHeader("Content-Type", "application/csv");
		 response.setContentType("text/plain; charset=utf-8");
		//response.addHeader("Content-Disposition", "attachment; filename=user_details.csv");
		//response.setCharacterEncoding("UTF-8");
		PrintWriter out;
		List<AdmissionData> admissionDatas=admissionService.findAll();
		//List<User> users = userService.findAllUsers();
		try {
			out = response.getWriter();
			out.write("FirstName,LastName,Email,Cnp,Address,Telephone,ExamSubject,AdmissionType,Section,HasDisabilities,AdditionalInformation,"
					+ "civilState,bacalaureatGrade,generalGrade,country,region,city,highschool");
			out.write("\n");
			//BufferedWriter bw = new BufferedWriter(
					//new OutputStreamWriter(new FileOutputStream("admissionData.csv"), "UTF-8"));
			for (AdmissionData admissionData : admissionDatas) {
				User user=admissionData.getUser();
				if(user!=null)
				{
				StringBuilder line = new StringBuilder();
				line.append(user.getFirstName());
				line.append(CSV_SEPARATOR);
				line.append(user.getLastName());
				line.append(CSV_SEPARATOR);
				line.append(user.getEmail());
				line.append(CSV_SEPARATOR);
				line.append(admissionData.getCnp());
				line.append(CSV_SEPARATOR);
				line.append(admissionData.getAddress());
				line.append(CSV_SEPARATOR);
				line.append(admissionData.getTelephone());
				line.append(CSV_SEPARATOR);
				line.append(admissionData.getExamSubject());
				line.append(CSV_SEPARATOR);
				line.append(admissionData.getAdmissionType());
				line.append(CSV_SEPARATOR);
				line.append(admissionData.getLanguage());
				line.append(CSV_SEPARATOR);
				line.append(admissionData.getHasDisabilities());
				line.append(CSV_SEPARATOR);
				line.append(admissionData.getAdditionalInformation());
				line.append(CSV_SEPARATOR);
				line.append(admissionData.getCivil_state());
				line.append(CSV_SEPARATOR);
				line.append(admissionData.getBacGrade());
				line.append(CSV_SEPARATOR);
				line.append(admissionData.getGeneralGrade());
				line.append(CSV_SEPARATOR);
				if(admissionData.getCountry()!=null)
				line.append(admissionData.getCountry().getCountry());
				else line.append("-");
				line.append(CSV_SEPARATOR);
				if(admissionData.getRegion()!=null)
				line.append(admissionData.getRegion().getRegion());
				else line.append("-");
				line.append(CSV_SEPARATOR);
				if(admissionData.getCity()!=null)
				line.append(admissionData.getCity().getCity());
				else line.append("-");
				line.append(CSV_SEPARATOR);
				if(admissionData.getHighschool()!=null)
				line.append(admissionData.getHighschool().getHighSchoolName());
				else line.append("-");
				out.write(line.toString());//print
				//bw.newLine();
				out.write("\n");
			}}
			//bw.flush();
			//bw.close();
			out.flush();
			out.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

}
