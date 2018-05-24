package com.fiiadmission.repository;

import org.springframework.data.repository.CrudRepository;

import com.fiiadmission.domain.Announcement;
import com.fiiadmission.domain.User;

public interface AnnouncementRepository  extends CrudRepository<Announcement, Long>{

}
