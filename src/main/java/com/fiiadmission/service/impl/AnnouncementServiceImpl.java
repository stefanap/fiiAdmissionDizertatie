package com.fiiadmission.service.impl;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
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
		List<Announcement> announcements = new ArrayList<>();
        Timestamp time = new Timestamp(new Date().getTime());
		for(Announcement announcement : (List<Announcement>)announcementRepository.findAll()){
		    if(announcement.getExpiry_date().after(time))
			{
		        announcements.add(announcement);
            }
        }
        return announcements;
	}

	@Override
	public Announcement saveAnnouncement(Announcement announcement) {
		return announcementRepository.save(announcement);
	}

}
