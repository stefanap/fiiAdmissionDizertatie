package com.fiiadmission.api.dto.mappers;

import com.fiiadmission.api.dto.CityDTO;
import com.fiiadmission.api.dto.RegionDTO;
import com.fiiadmission.domain.City;
import com.fiiadmission.domain.Region;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CityMapper {
    CityMapper INSTANCE = Mappers.getMapper(CityMapper.class);

    CityDTO toCityDto(City city);

    List<CityDTO> toCityDtoList(List<City> cities);
}
