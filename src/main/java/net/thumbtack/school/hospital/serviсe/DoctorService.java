package net.thumbtack.school.hospital.serviсe;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.dao.DoctorDao;
import net.thumbtack.school.hospital.daoimpl.DoctorDaoImpl;
import net.thumbtack.school.hospital.data.DataBase;
import net.thumbtack.school.hospital.dto.requests.RegisterDoctorDtoRequest;
import net.thumbtack.school.hospital.dto.response.RegisterDoctorDtoResponse;
import net.thumbtack.school.hospital.model.Doctor;

public class DoctorService {

    private Gson gson = new Gson();
    private DoctorDaoImpl doctorDao;

    public DoctorService(DoctorDaoImpl doctorDao) {
        this.doctorDao = doctorDao;
    }

    public DoctorService() {
        this(new DoctorDaoImpl());
    }

    public String registerDoctor(String requestJsonString){
        //написать класс ServeUtils с валидатором и Gson полем(параметризовать)
        // REVU а если json с ошибкой ?
        // возникнет JsonSyntaxException
        // лучше сделать шаблонный метод getClassFromJson
        // https://docs.oracle.com/javase/tutorial/extra/generics/methods.html
        // и пусть он внутри ловит JsonSyntaxException,
        // а поймав, выбросит ServerException с ErrorCode.WRONG_JSON
        // а тут все под try
        RegisterDoctorDtoRequest registerDoctorDtoRequest = gson.fromJson(requestJsonString,
                RegisterDoctorDtoRequest.class);
        if(!registerDoctorDtoRequest.validateRegisterDoctor()) return gson.toJson("error");
        // REVU используйте mapstruct
        Doctor doctor = new Doctor(registerDoctorDtoRequest);
        RegisterDoctorDtoResponse registerDoctorDtoResponse = new RegisterDoctorDtoResponse(
                doctorDao.insert(doctor));
        return gson.toJson(registerDoctorDtoResponse);
    }
}
