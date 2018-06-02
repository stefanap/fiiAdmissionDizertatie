package com.fiiadmission.api.controller;

import com.fiiadmission.api.dto.CountryDTO;
import com.fiiadmission.api.dto.RegionDTO;
import com.fiiadmission.api.dto.mappers.CountryMapper;
import com.fiiadmission.api.dto.mappers.RegionMapper;
import com.fiiadmission.domain.Region;
import com.fiiadmission.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/fii/countries")
public class CountryController {

    @Autowired
    private CountryService countryService;

    @GetMapping
    public List<CountryDTO> getAllCountries(){
        return CountryMapper.INSTANCE.toCountryDtoList(countryService.findAll());
    }

    @GetMapping(value = "/{id}/regions")
    public List<RegionDTO> getRegions(@PathVariable Long id){
        List<Region> regions = countryService.findById(id).getRegions();
        return RegionMapper.INSTANCE.toRegionDtoList(regions);
    }
}
