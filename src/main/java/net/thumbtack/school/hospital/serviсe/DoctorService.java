package net.thumbtack.school.hospital.serviсe;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.hospital.dao.DoctorDao;
import net.thumbtack.school.hospital.daoimpl.DoctorDaoImpl;
import net.thumbtack.school.hospital.data.DataBase;
import net.thumbtack.school.hospital.dto.requests.RegisterDoctorDtoRequest;
import net.thumbtack.school.hospital.dto.requests.RegisterPacientDtoRequest;
import net.thumbtack.school.hospital.dto.response.ErrorResponse;
import net.thumbtack.school.hospital.dto.response.RegisterDoctorDtoResponse;
import net.thumbtack.school.hospital.exceptions.ServerErrorCode;
import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.mapping.DoctorMapper;
import net.thumbtack.school.hospital.model.Doctor;
import org.mapstruct.Mapper;

public class DoctorService {

    private Gson gson = new Gson();
    private DoctorDaoImpl doctorDao;

    public DoctorService(DoctorDaoImpl doctorDao) {
        this.doctorDao = doctorDao;
    }

    public DoctorService() {
        this(new DoctorDaoImpl());
    }

    public String registerDoctor(String requestJsonString) {
        try {
            RegisterDoctorDtoRequest registerDoctorDtoRequest = ServiceUtils.getClassFromJson(requestJsonString,
                    RegisterDoctorDtoRequest.class);
            Validator.doctorValidate(registerDoctorDtoRequest);
            Doctor doctor = DoctorMapper.INSTANCE.fromDtoToDoctor(registerDoctorDtoRequest);
            RegisterDoctorDtoResponse registerDoctorDtoResponse = new RegisterDoctorDtoResponse(
                    doctorDao.insert(doctor));
            return gson.toJson(registerDoctorDtoResponse);
        } catch (ServerException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

}
