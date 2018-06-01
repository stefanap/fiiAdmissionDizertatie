package com.fiiadmission.service.impl;

import com.fiiadmission.domain.Region;
import com.fiiadmission.repository.RegionRepository;
import com.fiiadmission.service.RegionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class RegionServiceImpl implements RegionService {

    @Autowired
    private RegionRepository regionRepository;

    @Override
    public Region findById(Long id) {
        return regionRepository.findOne(id);
    }
}
