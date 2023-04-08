package net.thumbtack.school.hospital.mapping;

import net.thumbtack.school.hospital.dto.requests.RegisterDoctorDtoRequest;
import net.thumbtack.school.hospital.model.Doctor;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface DoctorMapper {

    DoctorMapper INSTANCE  = Mappers.getMapper(DoctorMapper.class);

    Doctor fromDtoToDoctor(RegisterDoctorDtoRequest registerDoctorDtoRequest);

}
