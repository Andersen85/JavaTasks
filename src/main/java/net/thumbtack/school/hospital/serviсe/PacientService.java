package net.thumbtack.school.hospital.serviсe;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.daoimpl.PacientDaoImpl;
import net.thumbtack.school.hospital.data.DataBase;
import net.thumbtack.school.hospital.dto.requests.RegisterPacientDtoRequest;
import net.thumbtack.school.hospital.dto.response.ErrorResponse;
import net.thumbtack.school.hospital.dto.response.RegisterDoctorDtoResponse;
import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.mapping.PacientMapper;
import net.thumbtack.school.hospital.model.Patient;

public class PacientService {

    private Gson gson = new Gson();
    private PacientDaoImpl pacientDao;

    public PacientService(PacientDaoImpl pacientDao) {
        this.pacientDao = pacientDao;
    }

    public PacientService(){
        this(new PacientDaoImpl());
    }

    public String registerPacient(String requestJsonString) {
        try {
            RegisterPacientDtoRequest registerPacientDtoRequest = ServiceUtils.getClassFromJson(requestJsonString,
                    RegisterPacientDtoRequest.class);
            Validator.pacientValidate(registerPacientDtoRequest);
            Patient patient = PacientMapper.INSTANCE.fromDtoToPacient(registerPacientDtoRequest);
            RegisterDoctorDtoResponse registerDoctorDtoResponse = new RegisterDoctorDtoResponse(
                    gson.toJson(DataBase.getDataBase().insert(patient)));
            return registerDoctorDtoResponse.getToken();
        }
        catch (ServerException e){
            return gson.toJson(new ErrorResponse(e));
        }
    }
}
