package com.fiiadmission.api.dto.mappers;

import com.fiiadmission.api.dto.CountryDTO;
import com.fiiadmission.domain.Country;
import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface CountryMapper {
    CountryMapper INSTANCE = Mappers.getMapper(CountryMapper.class);

    CountryDTO toCountryDto(Country country);

    List<CountryDTO> toCountryDtoList(List<Country> countries);

    Country toCountry(CountryDTO countryDTO);
}
