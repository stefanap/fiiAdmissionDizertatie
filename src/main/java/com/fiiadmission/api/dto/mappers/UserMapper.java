package com.fiiadmission.api.dto.mappers;

import com.fiiadmission.api.dto.UserDTO;
import com.fiiadmission.domain.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValueCheckStrategy;
import org.mapstruct.factory.Mappers;

import java.util.List;

@Mapper(nullValueCheckStrategy = NullValueCheckStrategy.ALWAYS)
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    @Mapping(target="password", ignore = true)
    UserDTO toUserDTO(User user);

    List<UserDTO> toUserDTOs(List<User> users);

    @Mapping(target="id", ignore = true)
    @Mapping(target="password", ignore = true)
    @Mapping(target="username", ignore = true)
    @Mapping(target="registerDate", ignore = true)
    @Mapping(target="registerNumber", ignore = true)
    User toUser(UserDTO userDTO);
}
