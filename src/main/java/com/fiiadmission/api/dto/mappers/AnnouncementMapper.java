package com.fiiadmission.api.dto.mappers;

import com.fiiadmission.api.dto.AnnouncementDTO;
import com.fiiadmission.domain.Announcement;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface AnnouncementMapper {
    AnnouncementMapper INSTANCE = Mappers.getMapper(AnnouncementMapper.class);

    AnnouncementDTO toAnnouncementDto(Announcement announcement);

    List<AnnouncementDTO> toAnnouncementDtoList(List<Announcement> announcements);

    @Mapping(target="id", ignore = true)
    @Mapping(target="publish_date", ignore = true)
    Announcement toAnnouncement(AnnouncementDTO announcementDTO);
}
