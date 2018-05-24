package com.fiiadmission.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.validator.constraints.NotEmpty;

import javax.persistence.*;
import java.util.List;

/**
 * Created by nydiarra on 06/05/17.
 */
@Entity
@Table(name = "admission_data")
public class AdmissionData {
	@Id
	@GeneratedValue(strategy = GenerationType.SEQUENCE)
	@Column(name = "id")
	private Long id;

	@Column(name = "cnp", length=13)
	private String cnp;

	@Column(name = "address", length=200)
	private String adress;

	@Column(name = "exam_subject", length=30)
	private String examSubject;

	@Column(name = "telephone", length=13)
	private String telephone;

	@Column(name = "bac_grade", length=10)
	private Float bacGrade;

	@Column(name = "general_grade", length=10)
	private Float generalGrade;

	@OneToOne
	@JoinColumn(name = "highschool_id")
	private Highschool highschool;
	
	@Column(name = "civil_state",length=30)
	private String civil_state;
	
	@Column(name = "language",length=20)
	private String language;
	
	@Column(name = "hasDisabilities",length=2)
	private Boolean hasDisabilities;
	
	@Column(name = "additionalInformation")
	private String additionalInformation;
	
	@Column(name="admissionType",length=50)
	private String admissionType;
	
	@OneToOne
	@JoinColumn(name = "country_id")
	private Country country;
	
	@OneToOne
	@JoinColumn(name = "regiony_id")
	private Region region;
	
	@OneToOne
	@JoinColumn(name = "city_id")
	private City city;
	
	@OneToOne
	@JoinColumn(name = "document_type_id")
	private DocumentType documentType;

	@OneToOne
	@JoinColumn(name = "user_id")
	private User user;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getCnp() {
		return cnp;
	}

	public void setCnp(String cnp) {
		this.cnp = cnp;
	}

	public String getAdress() {
		return adress;
	}

	public void setAdress(String adress) {
		this.adress = adress;
	}

	public String getExamSubject() {
		return examSubject;
	}

	public void setExamSubject(String examSubject) {
		this.examSubject = examSubject;
	}

	public DocumentType getDocument_type() {
		return documentType;
	}

	public void setDocuemnt_type(DocumentType document_type) {
		this.documentType = document_type;
	}

	public String getTelephone() {
		return telephone;
	}

	public void setTelephone(String telephone) {
		this.telephone = telephone;
	}

	
	public Float getBacGrade() {
		return bacGrade;
	}

	public void setBacGrade(Float bacGrade) {
		this.bacGrade = bacGrade;
	}

	public Float getGeneralGrade() {
		return generalGrade;
	}

	public void setGeneralGrade(Float generalGrade) {
		this.generalGrade = generalGrade;
	}

	public String getLanguage() {
		return language;
	}

	public void setLanguage(String language) {
		this.language = language;
	}

	public Boolean getHasDisabilities() {
		return hasDisabilities;
	}

	public void setHasDisabilities(Boolean hasDisabilities) {
		this.hasDisabilities = hasDisabilities;
	}

	public String getAdditionalInformation() {
		return additionalInformation;
	}

	public void setAdditionalInformation(String additionalInformation) {
		this.additionalInformation = additionalInformation;
	}

	public String getAdmissionType() {
		return admissionType;
	}

	public void setAdmissionType(String admissionType) {
		this.admissionType = admissionType;
	}




	public DocumentType getDocumentType() {
		return documentType;
	}

	public void setDocumentType(DocumentType documentType) {
		this.documentType = documentType;
	}

	public Highschool getHighschool() {
		return highschool;
	}

	public void setHighschool(Highschool highschool) {
		this.highschool = highschool;
	}

	public Country getCountry() {
		return country;
	}

	public void setCountry(Country country) {
		this.country = country;
	}

	public Region getRegion() {
		return region;
	}

	public void setRegion(Region region) {
		this.region = region;
	}

	public City getCity() {
		return city;
	}

	public void setCity(City city) {
		this.city = city;
	}

	public String getCivil_state() {
		return civil_state;
	}

	public void setCivil_state(String civil_state) {
		this.civil_state = civil_state;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
