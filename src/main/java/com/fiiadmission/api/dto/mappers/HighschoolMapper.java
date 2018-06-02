package com.fiiadmission.api.dto.mappers;

import com.fiiadmission.api.dto.CityDTO;
import com.fiiadmission.api.dto.HighschoolDTO;
import com.fiiadmission.domain.City;
import com.fiiadmission.domain.Highschool;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface HighschoolMapper {
    HighschoolMapper INSTANCE = Mappers.getMapper(HighschoolMapper.class);

    HighschoolDTO toHighschoolDto(Highschool highschool);

    List<HighschoolDTO> toHighschoolDtoList(List<Highschool> highschools);

    Highschool toHighschool(HighschoolDTO highschoolDTO);
}
