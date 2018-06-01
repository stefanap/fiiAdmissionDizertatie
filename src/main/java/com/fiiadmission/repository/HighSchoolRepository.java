package com.fiiadmission.repository;

import com.fiiadmission.domain.Highschool;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface HighSchoolRepository extends CrudRepository<Highschool, Long> {
}
