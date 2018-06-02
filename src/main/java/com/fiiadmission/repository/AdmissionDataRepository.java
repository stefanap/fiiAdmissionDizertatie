package com.fiiadmission.repository;

import org.springframework.data.repository.CrudRepository;

import com.fiiadmission.domain.AdmissionData;
import org.springframework.stereotype.Repository;

@Repository
public interface AdmissionDataRepository extends CrudRepository<AdmissionData, Long>{

}
