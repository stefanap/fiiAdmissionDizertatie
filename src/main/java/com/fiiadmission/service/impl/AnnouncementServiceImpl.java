package com.fiiadmission.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.fiiadmission.domain.Announcement;
import com.fiiadmission.domain.User;
import com.fiiadmission.repository.AnnouncementRepository;
import com.fiiadmission.repository.UserRepository;
import com.fiiadmission.service.AnnouncementService;

@Service
public class AnnouncementServiceImpl implements AnnouncementService{
	
	 @Autowired
	 private AnnouncementRepository announcementRepository;

	@Override
	public List<Announcement> findAllAnnouncements() {
		return (List<Announcement>)announcementRepository.findAll();
	}

}
