package com.fiiadmission.api.controller;

import com.fiiadmission.api.dto.CityDTO;
import com.fiiadmission.api.dto.CountryDTO;
import com.fiiadmission.api.dto.HighschoolDTO;
import com.fiiadmission.api.dto.RegionDTO;
import com.fiiadmission.api.dto.mappers.CityMapper;
import com.fiiadmission.api.dto.mappers.CountryMapper;
import com.fiiadmission.api.dto.mappers.HighschoolMapper;
import com.fiiadmission.api.dto.mappers.RegionMapper;
import com.fiiadmission.domain.City;
import com.fiiadmission.domain.Highschool;
import com.fiiadmission.domain.Region;
import com.fiiadmission.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/regions")
public class RegionController {

    @Autowired
    private RegionService regionService;

    @GetMapping(value = "/{id}/cities")
    public List<CityDTO> getCities(@PathVariable Long id){
        List<City> cities = regionService.findById(id).getCities();
        return CityMapper.INSTANCE.toCityDtoList(cities);
    }

    @GetMapping(value = "/{id}/highschools")
    public List<HighschoolDTO> getHighschools(@PathVariable Long id){
        List<Highschool> highschools = regionService.findById(id).getHighschools();
        return HighschoolMapper.INSTANCE.toHighschoolDtoList(highschools);
    }
}
