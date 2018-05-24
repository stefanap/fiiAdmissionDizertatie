package com.fiiadmission.service.impl;

import utils.AdmissionStatus;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiiadmission.domain.User;
import com.fiiadmission.repository.UserRepository;
import com.fiiadmission.service.UserService;

import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.List;
import java.util.Optional;


@Service
public class UserServiceImpl implements UserService {

	public static String QR_PREFIX = "https://chart.googleapis.com/chart?chs=200x200&chld=M%%7C0&cht=qr&chl=";
	public static String APP_NAME = "FII_ADMISSION";

	@Autowired
    private UserRepository userRepository;

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username);
    }

    @Override
    public List<User> findAllUsers() {
        return (List<User>)userRepository.findAll();
    }

	@Override
	public List<User> findAllUsersWithStatus(String status) {
		return (List<User>)userRepository.findByAdmissionStatus(status);
	}

	@Override
	public List<User> findByName(Optional<String> firstname, Optional<String> lastname) {
		String firstName = firstname.isPresent()? firstname.get():null;
		String lastName = lastname.isPresent()? lastname.get():null;
		if(firstName!=null&&lastName!=null)
		return userRepository.findByFirstNameAndLastName(firstName, lastName);
		else if(firstName!=null)
			return userRepository.findByFirstName(firstName);
		else if(lastName!=null)
			return userRepository.findByLastName(lastName);
		return null;
	}

	@Override
	public User findOne(User user) {
		return userRepository.findOne(user.getId());
	}

	@Override
	public User save(User searchedUser) {
		return userRepository.save(searchedUser);
	}

	@Override
	public User findById(Long id) {
		return userRepository.findById(id);
	}

	@Override
	public String generateQRUrl(User user) throws UnsupportedEncodingException {
		return QR_PREFIX + URLEncoder.encode(String.format("otpauth://totp/%s:%s?secret=%s&issuer=%s", APP_NAME, user.getEmail(), user.getSecret(), APP_NAME), "UTF-8");
	}
}
