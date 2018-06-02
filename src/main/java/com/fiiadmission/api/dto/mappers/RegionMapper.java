package com.fiiadmission.api.dto.mappers;

import com.fiiadmission.api.dto.RegionDTO;
import com.fiiadmission.domain.Region;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface RegionMapper {
    RegionMapper INSTANCE = Mappers.getMapper(RegionMapper.class);

    RegionDTO toRegionDto(Region region);

    List<RegionDTO> toRegionDtoList(List<Region> regions);

    Region toRegion(RegionDTO regionDTO);
}
