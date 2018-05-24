package com.fiiadmission.repository;

import utils.AdmissionStatus;

import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.PagingAndSortingRepository;

import com.fiiadmission.domain.User;


public interface UserRepository extends CrudRepository<User, Long> {
	User findByUsername(String username);

	List<User> findByFirstNameAndLastName(String firstname, String lastname);

	List<User> findByFirstName(String firstname);

	List<User> findByLastName(String lastname);

	List<User> findByAdmissionStatus(String admissionStatus);

	User findById(Long id);
}
