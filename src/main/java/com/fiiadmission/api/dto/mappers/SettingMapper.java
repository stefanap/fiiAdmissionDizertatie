package com.fiiadmission.api.dto.mappers;

import org.mapstruct.Mapper;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import com.fiiadmission.api.dto.SettingDTO;
import com.fiiadmission.domain.Setting;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface SettingMapper {
	
		SettingMapper INSTANCE = Mappers.getMapper(SettingMapper.class);

		SettingDTO toSettingDto(Setting setting);

		Setting toSetting(SettingDTO settingDTO);

}
