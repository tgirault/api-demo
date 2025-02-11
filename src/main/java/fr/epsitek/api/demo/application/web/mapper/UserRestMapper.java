package fr.epsitek.api.demo.application.web.mapper;

import fr.epsitek.api.demo.application.web.dto.request.UserRegistrationRequest;
import fr.epsitek.api.demo.application.web.dto.response.UserResponse;
import fr.epsitek.api.demo.domain.core.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserRestMapper {
    @Mapping(source="userId.id", target="id")
    UserResponse toUserResponse(final User user);
    User toUser(final UserRegistrationRequest userRegistrationRequest);
}
