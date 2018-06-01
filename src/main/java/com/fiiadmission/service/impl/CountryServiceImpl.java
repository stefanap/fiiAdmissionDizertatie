package com.fiiadmission.service.impl;

import com.fiiadmission.domain.Country;
import com.fiiadmission.repository.CountryRepository;
import com.fiiadmission.service.CountryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CountryServiceImpl implements CountryService {

    @Autowired
    CountryRepository countryRepository;

    @Override
    public List<Country> findAll() {
        return (List<Country>) countryRepository.findAll();
    }
}
