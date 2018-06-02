package com.fiiadmission.api.dto.mappers;

import com.fiiadmission.api.dto.AdmissionDataDTO;
import com.fiiadmission.domain.AdmissionData;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS,
        uses = { HighschoolMapper.class, CountryMapper.class, CityMapper.class, RegionMapper.class, UploadedDocumentMapper.class })
public interface AdmissionDataMapper {
    AdmissionDataMapper INSTANCE = Mappers.getMapper(AdmissionDataMapper.class);

    AdmissionDataDTO toAdmissionDataDto(AdmissionData admissionData);

    AdmissionData toAdmissionData(AdmissionDataDTO admissionDataDTO);
}
