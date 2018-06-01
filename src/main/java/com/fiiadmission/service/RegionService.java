package com.fiiadmission.service;

import com.fiiadmission.domain.Region;
import org.springframework.stereotype.Service;

public interface RegionService {

    Region findById(Long id);
}
