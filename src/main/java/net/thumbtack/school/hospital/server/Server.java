package net.thumbtack.school.hospital.server;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.serviсe.DoctorService;
import net.thumbtack.school.hospital.serviсe.PacientService;
import net.thumbtack.school.hospital.serviсe.UserService;


public class Server {

    private final Gson gson = new Gson();
    private DoctorService doctorService = new DoctorService();
    private PacientService pacientService = new PacientService();
    private UserService userService = new UserService();

    public void startServer(String savedDataFileName) {
        //Производит всю необходимую инициализацию и запускает сервер.
        //Если savedDataFileName == null, восстановление состояния не производится, сервер стартует “с нуля”.

    }

    public void stopServer(String savedDataFileName) {
        //Останавливает сервер и записывает все его содержимое в файл сохранения с именем savedDataFileName.
        //Если savedDataFileName == null, запись содержимого не производится

    }

    public String registerDoctor(String requestJsonString) throws ServerException {
        //Регистрация доктора. Обязательно содержит token
        return doctorService.registerDoctor(requestJsonString);
    }

    public String registerPatient(String requestJsonString) throws ServerException {
        //Регистрация пациента.
        return pacientService.registerPatient(requestJsonString);
    }

    public String login(String requestJsonString) {
        return userService.login(requestJsonString);
    }

    public void logout(String requestJsonString) {
        userService.logout(requestJsonString);
    }

    public void leave(String requestJsonString){
        userService.leave(requestJsonString);
    }

    public String changePassword(String requestJsonString){
        return userService.changePassword(requestJsonString);
    }

    public String addDoctorAppointment(String requestJsonString){
        return doctorService.addDoctorAppointment(requestJsonString);
    }

    public String deleteDoctorAppointment(String reqestJsonString){
        return doctorService.deleteDoctorAppointment(reqestJsonString);
    }



}