package net.thumbtack.school.hospital.mapping;

import net.thumbtack.school.hospital.dto.requests.UserDtoRequest;
import net.thumbtack.school.hospital.model.User;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface UserMapper {
    UserMapper INSTANCE = Mappers.getMapper(UserMapper.class);

    User fromDtoToUser(UserDtoRequest user);
}
