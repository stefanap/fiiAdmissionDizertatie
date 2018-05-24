package com.fiiadmission.service;

import java.io.UnsupportedEncodingException;
import java.util.List;
import java.util.Optional;

import com.fiiadmission.domain.User;

/**
 * Created by nydiarra on 06/05/17.
 */
public interface UserService {
    User findByUsername(String username);
    
    List<User> findByName(Optional<String> firstName,Optional<String> lastName);

    List<User> findAllUsers();

    List<User> findAllUsersWithStatus(String status);

	User findOne(User user);

	User save(User searchedUser);

	User findById(Long id);

    String generateQRUrl(User user) throws UnsupportedEncodingException;
}
