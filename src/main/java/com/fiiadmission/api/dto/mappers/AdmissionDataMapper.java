package com.fiiadmission.api.dto.mappers;

import java.util.List;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import com.fiiadmission.api.dto.AdmissionDataDTO;
import com.fiiadmission.domain.AdmissionData;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = { HighschoolMapper.class, CountryMapper.class, CityMapper.class, RegionMapper.class, UploadedDocumentMapper.class })
public interface AdmissionDataMapper {
    AdmissionDataMapper INSTANCE = Mappers.getMapper(AdmissionDataMapper.class);

    AdmissionDataDTO toAdmissionDataDtO(AdmissionData admissionData);

    AdmissionData toAdmissionData(AdmissionDataDTO admissionDataDTO);
    
    List<AdmissionDataDTO> toAdmissionDataDTOs(List<AdmissionData> list);

    
}
