package fr.epsitek.api.demo.infrastructure.persistence.mapper;

import fr.epsitek.api.demo.domain.core.model.User;
import fr.epsitek.api.demo.infrastructure.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserPersistenceMapper {
    @Mapping(source = "id", target = "userId.id")
    User toUser(final UserEntity userEntity);

    @Mapping(source = "userId.id", target = "id")
    UserEntity toUserEntity(User user);
}
