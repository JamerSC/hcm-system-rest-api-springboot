package com.jamersc.springboot.hcm_api.mapper;

import com.jamersc.springboot.hcm_api.dto.applicant.ApplicantDTO;
import com.jamersc.springboot.hcm_api.dto.applicant.ApplicantProfileDTO;
import com.jamersc.springboot.hcm_api.dto.applicant.ApplicantResponseDTO;
import com.jamersc.springboot.hcm_api.entity.Applicant;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import java.util.List;

@Mapper(
        componentModel = "spring",
        // This is useful if you want to explicitly define how nulls are handled.
        // For DTO to Entity, you usually want to ignore nulls on updates to prevent overwriting existing data.
        nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE
)
public interface ApplicantMapper {

    /**
     * Maps an Applicant entity to an ApplicantProfileDTO.
     * MapStruct will automatically map fields with the same name.
     * Fields in the entity not present in the DTO (like id, user, cvFilePath, currentStatus)
     * will simply be ignored, which is the desired behavior for a profile DTO.
     */
    ApplicantDTO entityToApplicantDto(Applicant applicant);

    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    Applicant applicantDtoToEntity(ApplicantDTO dto);

    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "applicantFullName", source = "applicantFullName")
    ApplicantResponseDTO entityToResponseDto(Applicant applicant);

    @Mapping(target = "user", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "phoneNumber", ignore = true)
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "educationLevel", ignore = true)
    @Mapping(target = "cvFilePath", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "address", ignore = true)
    Applicant responseDtoToEntity(ApplicantResponseDTO dto);

    @Mapping(target = "email", source = "user.email")
    @Mapping(target = "username", source = "user.username")
    ApplicantProfileDTO entityToProfileDto(Applicant applicant);

    // Maps an ApplicantDTO to an Applicant entity .
    @Mapping(target = "lastName", ignore = true)
    @Mapping(target = "firstName", ignore = true)
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true) //  source = ""
    @Mapping(target = "id", ignore = true)
    Applicant profileDtoToEntity(ApplicantDTO dto);


    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true) // 'id' should not be updated from the DTO
    @Mapping(target = "id", ignore = true) // 'user' should not be updated from the DTO
    @Mapping(target = "cvFilePath", ignore = true)  // 'cvFilePath' typically updated separately
    void updateEntityFromProfileDto(ApplicantProfileDTO dto, @MappingTarget Applicant applicant);

    /**
     * Creates a new Applicant entity from an ApplicantProfileDTO.
     * This method is used when creating a *new* applicant, not updating.
     * You'll need to handle 'user' association separately in your service layer
     * since the DTO doesn't contain user information directly.
     *
     * @param dto The DTO containing initial profile information.
     * @return A new Applicant entity.
     */
    @Mapping(target = "updatedBy", ignore = true)
    @Mapping(target = "updatedAt", ignore = true)
    @Mapping(target = "createdBy", ignore = true)
    @Mapping(target = "createdAt", ignore = true)
    @Mapping(target = "user", ignore = true)
    @Mapping(target = "id", ignore = true)
    @Mapping(target = "cvFilePath", ignore = true)
    Applicant profileDtoToNewEntity(ApplicantProfileDTO dto);

    // If you ever need to map the User entity itself to a DTO (e.g., UserIdDTO),
    // you would add a method here, but it's not directly related to ApplicantProfileDTO's purpose.
    // Example: UserIdDTO userToUserIdDto(User user);
    List<ApplicantDTO> entityToApplicantDtoList(List<Applicant> applicants);

    List<ApplicantResponseDTO> entitiesToResponseDtos(List<Applicant> applicant);

    List<Applicant> responseDtosToEntities(List<ApplicantResponseDTO> dto);
}
