package net.thumbtack.school.hospital.mapping;

import net.thumbtack.school.hospital.dto.requests.RegisterPatientDtoRequest;
import net.thumbtack.school.hospital.model.Patient;
import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

@Mapper
public interface PacientMapper {

    PacientMapper INSTANCE = Mappers.getMapper(PacientMapper.class);

    Patient fromDtoToPacient(RegisterPatientDtoRequest registerPatientDtoRequest);
}
