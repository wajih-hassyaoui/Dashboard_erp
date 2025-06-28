package com.dashboard_erp.backend.Mapper;

import com.dashboard_erp.backend.DTO.UserDto;
import com.dashboard_erp.backend.Entity.User;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface UserMapper {
    UserDto toUserDto(User user) ;
}
