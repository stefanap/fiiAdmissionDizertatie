package com.fiiadmission.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.fiiadmission.domain.Announcement;
import com.fiiadmission.domain.User;
import com.fiiadmission.service.AnnouncementService;
import com.fiiadmission.service.UserService;

@RestController
@RequestMapping("/announcements")
@CrossOrigin(origins = "http://localhost:3000")
public class AnnouncementController {

	@Autowired
    private AnnouncementService announcementService;
	
	 @RequestMapping(method = RequestMethod.GET)
	    public List<Announcement> getAnnouncements(){
	        return announcementService.findAllAnnouncements();
	    }
}
