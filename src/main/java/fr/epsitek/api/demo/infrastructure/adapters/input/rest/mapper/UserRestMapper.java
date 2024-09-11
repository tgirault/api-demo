package fr.epsitek.api.demo.infrastructure.adapters.input.rest.mapper;

import fr.epsitek.api.demo.domain.core.model.User;
import fr.epsitek.api.demo.infrastructure.adapters.input.rest.data.response.UserResponse;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserRestMapper {
    UserResponse toUserResponse(final User user);
}
