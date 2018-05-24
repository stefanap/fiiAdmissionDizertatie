package com.fiiadmission.service;

import java.util.List;

import com.fiiadmission.domain.Announcement;
import com.fiiadmission.domain.User;

public interface AnnouncementService {
	
	 List<Announcement> findAllAnnouncements();

}
