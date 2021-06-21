package com.lamdbsys.microservices.serviceusers.mappers;

import com.lamdbsys.microservices.serviceusers.dtos.UserDto;
import com.lamdbsys.microservices.serviceusers.entities.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

@Mapper(componentModel = "spring")
public interface UserMapper {

    @Mapping(source = "user.id" , target = "id")
    @Mapping(source = "user.username" , target = "username" )
    @Mapping(source = "token" , target = "token" )
    UserDto toDto(User user, String token);

}
