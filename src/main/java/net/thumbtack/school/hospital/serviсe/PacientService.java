package net.thumbtack.school.hospital.serviсe;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.daoimpl.PatientDaoImpl;
import net.thumbtack.school.hospital.data.DataBase;
import net.thumbtack.school.hospital.dto.requests.AppointmentDtoRequest;
import net.thumbtack.school.hospital.dto.requests.RegisterPatientDtoRequest;
import net.thumbtack.school.hospital.dto.response.ErrorResponse;
import net.thumbtack.school.hospital.dto.response.RegisterDoctorDtoResponse;
import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.mapping.PacientMapper;
import net.thumbtack.school.hospital.model.Patient;

public class PacientService {

    private Gson gson = new Gson();
    private PatientDaoImpl patientDao;

    public PacientService(PatientDaoImpl patientDao) {
        this.patientDao = patientDao;
    }

    public PacientService() {
        this(new PatientDaoImpl());
    }

    public String registerPatient(String requestJsonString) {
        try {
            RegisterPatientDtoRequest registerPatientDtoRequest = ServiceUtils.getClassFromJson(requestJsonString,
                    RegisterPatientDtoRequest.class);
            Validator.pacientValidate(registerPatientDtoRequest);
            Patient patient = PacientMapper.INSTANCE.fromDtoToPacient(registerPatientDtoRequest);
            patientDao.addPatientToDoctor(patient, registerPatientDtoRequest.getDoctorToken());
            RegisterDoctorDtoResponse registerDoctorDtoResponse = new RegisterDoctorDtoResponse(
                    gson.toJson(DataBase.getDataBase().insert(patient)));
            return registerDoctorDtoResponse.getToken();
        } catch (ServerException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }


}
