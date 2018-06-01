package com.fiiadmission.service;

import com.fiiadmission.domain.Country;

import java.util.List;

public interface CountryService {

    List<Country> findAll();

    Country findById(Long id);
}
