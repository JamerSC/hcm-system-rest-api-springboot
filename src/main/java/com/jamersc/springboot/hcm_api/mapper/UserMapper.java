package com.jamersc.springboot.hcm_api.mapper;

import com.jamersc.springboot.hcm_api.dto.user.UserCreateDTO;
import com.jamersc.springboot.hcm_api.dto.user.UserDTO;
import com.jamersc.springboot.hcm_api.dto.user.UserResponseDTO;
import com.jamersc.springboot.hcm_api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UserMapper {

    UserDTO entityToUserDTO(User user);

    @Mapping(target = "updatedBy", ignore = true) // source = ""
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "applicant", ignore = true)
    User userDtoToEntity(UserDTO dto);

    UserCreateDTO entityToUserCreateDTO(User user);

    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "updatedBy", ignore = true) //source = ""
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "applicant", ignore = true)
    User userCreateDtoToEntity(UserCreateDTO dto);

    @Mapping(target = "createdBy", source = "createdBy.employee.job.title")
    @Mapping(target = "updatedBy", source = "updatedBy.employee.job.title")
    UserResponseDTO entityToUserResponseDTO(User user);

    @Mapping(target = "password", ignore = true)
    @Mapping(target = "updatedBy", ignore = true) //source = ""
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "applicant", ignore = true)
    User userResponseDtoToEntity(UserResponseDTO dto);

    List<UserDTO> entitiesToDtos(List<User> users);

    List<User> dtosToEntities(List<UserDTO> dtos);
}
