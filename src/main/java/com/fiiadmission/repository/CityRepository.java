package com.fiiadmission.repository;

import com.fiiadmission.domain.City;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CityRepository extends CrudRepository<City, Long> {
}
