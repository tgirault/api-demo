package fr.epsitek.api.demo.infrastructure.converters;

import fr.epsitek.api.demo.application.dto.UserDto;
import fr.epsitek.api.demo.domain.User;
import fr.epsitek.api.demo.infrastructure.beans.UserBean;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserConverter {
    User toUser(final UserBean userBean);
    UserDto toUserDto(final User user);
}
