package net.thumbtack.school.hospital.serviсe;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.daoimpl.PacientDaoImpl;
import net.thumbtack.school.hospital.data.DataBase;
import net.thumbtack.school.hospital.dto.requests.RegisterPacientDtoRequest;
import net.thumbtack.school.hospital.dto.response.RegisterDoctorDtoResponse;
import net.thumbtack.school.hospital.model.Pacient;

public class PacientService {

    private Gson gson = new Gson();
    private PacientDaoImpl pacientDao;

    public PacientService(PacientDaoImpl pacientDao) {
        this.pacientDao = pacientDao;
    }

    public PacientService(){
        this(new PacientDaoImpl());
    }

    public String registerPacient(String requestJsonString){
        RegisterPacientDtoRequest registerPacientDtoRequest = gson.fromJson(requestJsonString,
                                                                                RegisterPacientDtoRequest.class);
        Pacient pacient = new Pacient(registerPacientDtoRequest);
        RegisterDoctorDtoResponse registerDoctorDtoResponse = new RegisterDoctorDtoResponse(
                gson.toJson(DataBase.getDataBase().insert(pacient)));
        return registerDoctorDtoResponse.getToken();
    }
}
