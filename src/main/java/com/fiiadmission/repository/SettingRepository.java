package com.fiiadmission.repository;

import org.springframework.data.repository.CrudRepository;
import com.fiiadmission.domain.Setting;

import org.springframework.stereotype.Repository;

@Repository
public interface SettingRepository extends CrudRepository<Setting, Long>{
	
	Setting findFirstBy();

}
