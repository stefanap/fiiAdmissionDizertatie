package com.fiiadmission.api.dto;

import com.fiiadmission.domain.UploadedDocument;

import java.util.List;

public class AdmissionDataDTO {
    private Long id;
    private String cnp;
    private String address;
    private String examSubject;
    private String telephone;
    private Float bacGrade;
    private Float generalGrade;
    private HighschoolDTO highschool;
    private String civil_state;
    private String language;
    private Boolean hasDisabilities;
    private String additionalInformation;
    private String admissionType;
    private CountryDTO country;
    private RegionDTO region;
    private CityDTO city;

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

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getExamSubject() {
        return examSubject;
    }

    public void setExamSubject(String examSubject) {
        this.examSubject = examSubject;
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

    public String getCivil_state() {
        return civil_state;
    }

    public void setCivil_state(String civil_state) {
        this.civil_state = civil_state;
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

    public HighschoolDTO getHighschool() {
        return highschool;
    }

    public void setHighschool(HighschoolDTO highschool) {
        this.highschool = highschool;
    }

    public CountryDTO getCountry() {
        return country;
    }

    public void setCountry(CountryDTO country) {
        this.country = country;
    }

    public RegionDTO getRegion() {
        return region;
    }

    public void setRegion(RegionDTO region) {
        this.region = region;
    }

    public CityDTO getCity() {
        return city;
    }

    public void setCity(CityDTO city) {
        this.city = city;
    }

}
