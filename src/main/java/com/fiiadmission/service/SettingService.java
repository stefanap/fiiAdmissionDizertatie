package com.fiiadmission.service;

import org.springframework.stereotype.Service;
import com.fiiadmission.domain.Setting;

public interface SettingService {

	Setting findFirstBy();

	Setting save(Setting setting);
}
