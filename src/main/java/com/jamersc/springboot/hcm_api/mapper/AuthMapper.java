package com.jamersc.springboot.hcm_api.mapper;

import com.jamersc.springboot.hcm_api.dto.auth.ChangePasswordDto;
import com.jamersc.springboot.hcm_api.dto.auth.LoginDto;
import com.jamersc.springboot.hcm_api.dto.auth.LoginResponseDto;
import com.jamersc.springboot.hcm_api.dto.auth.RegistrationDto;
import com.jamersc.springboot.hcm_api.entity.Permission;
import com.jamersc.springboot.hcm_api.entity.Role;
import com.jamersc.springboot.hcm_api.entity.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.Set;
import java.util.stream.Collectors;

@Mapper(componentModel = "spring",
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface AuthMapper {

    LoginDto entityToLoginDto(User user);

    @Mapping(target = "active", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "applicant", ignore = true)
    User loginDtoToEntity(LoginDto dto);

    @Mapping(target = "fullName", source = "fullName")
    @Mapping(target = "tokenType", ignore = true)
    @Mapping(target = "accessToken", ignore = true)
    @Mapping(target = "roles", expression = "java(mapRoles(user.getRoles()))")
    @Mapping(target = "permissions", expression = "java(mapPermissions(user.getRoles()))")
    LoginResponseDto entityToLoginResponseDto(User user);

    default Set<String> mapRoles(Set<Role> roles) {
        if (roles==null) {
            return null;
        }
        return roles.stream()
                .map(Role::getRoleName).collect(Collectors.toSet());
    }

    default Set<String> mapPermissions(Set<Role> roles) {
        if (roles == null) return Set.of();
        return roles.stream()
                .flatMap(role -> role.getPermissions().stream())
                .map(Permission::getName)
                .collect(Collectors.toSet());
    }

    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "applicant", ignore = true)
    User entityToLoginResponseDto(LoginResponseDto dto);

    RegistrationDto entityToRegistrationDto(User user);

    @Mapping(target = "active", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "applicant", ignore = true)
    User registrationDtoToEntity(RegistrationDto dto);

    @Mapping(target = "oldPassword", ignore = true)
    @Mapping(target = "newPassword", ignore = true)
    ChangePasswordDto entityToChangePasswordDto(User user);

    @Mapping(target = "active", ignore = true)
    @Mapping(target = "username", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "roles", ignore = true)
    @Mapping(target = "password", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "employee", ignore = true)
    @Mapping(target = "email", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "applicant", ignore = true)
    User changePasswordDtoToEntity(ChangePasswordDto dto);
}
