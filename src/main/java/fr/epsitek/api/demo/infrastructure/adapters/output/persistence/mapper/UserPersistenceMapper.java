package fr.epsitek.api.demo.infrastructure.adapters.output.persistence.mapper;

import fr.epsitek.api.demo.domain.core.model.User;
import fr.epsitek.api.demo.infrastructure.adapters.output.persistence.entity.UserEntity;
import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

@Mapper(
        componentModel = MappingConstants.ComponentModel.SPRING,
        unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface UserPersistenceMapper {
    User toUser(final UserEntity userEntity);
}
