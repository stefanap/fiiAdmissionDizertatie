package com.fiiadmission.api.controller;

import java.util.List;

import com.fiiadmission.api.dto.AnnouncementDTO;
import com.fiiadmission.api.dto.mappers.AnnouncementMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import com.fiiadmission.domain.Announcement;
import com.fiiadmission.service.AnnouncementService;

@RestController
public class AnnouncementController {

    @Autowired
    private AnnouncementService announcementService;

    @GetMapping
    @RequestMapping("/announcements")
    public List<AnnouncementDTO> getAnnouncements() {
        return AnnouncementMapper.INSTANCE.toAnnouncementDtoList(announcementService.findAllAnnouncements());
    }

    @PostMapping
    @RequestMapping("/fii/announcements")
    @PreAuthorize("hasAuthority('ADMIN_USER')")
    @ResponseStatus(value = HttpStatus.CREATED)
    public AnnouncementDTO createAnnouncement(@RequestBody AnnouncementDTO announcementDTO) {
        Announcement announcement = AnnouncementMapper.INSTANCE.toAnnouncement(announcementDTO);
        return AnnouncementMapper.INSTANCE.toAnnouncementDto(announcementService.saveAnnouncement(announcement));
    }
}
