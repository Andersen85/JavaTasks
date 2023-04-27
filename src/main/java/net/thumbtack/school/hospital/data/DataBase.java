package net.thumbtack.school.hospital.data;

import net.thumbtack.school.hospital.exceptions.ServerErrorCode;
import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.model.Doctor;
import net.thumbtack.school.hospital.model.DoctorAppointment;
import net.thumbtack.school.hospital.model.Patient;
import net.thumbtack.school.hospital.model.User;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.MultiValuedMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;
import org.apache.commons.collections4.multimap.HashSetValuedHashMap;

import java.util.*;

public class DataBase {

    private static DataBase singletonDataBase;
    private int counter;

    private Map<Integer, User> mapIdToUser;
    // REVU лучше BidiMap<String, User> mapTokenToUser
    private BidiMap<String, User> mapTokenToUser;
    private Map<String, User> mapLoginToUser;
    private MultiValuedMap<Integer, Integer> mapDoctorIdToPatientsId;


    public DataBase() {
        mapIdToUser = new HashMap<>();
        mapTokenToUser = new DualHashBidiMap<>();

        mapLoginToUser = new HashMap<>();
        mapDoctorIdToPatientsId = new HashSetValuedHashMap<>();
        counter = 0;

    }

    public String insert(User user) throws ServerException {
        // REVU спешите. А ну как LOGIN_IS_ALREADY_TAKEN ?
        counter++;
        user.setId(counter);
        //Добавления в базу + проверки
        String token = UUID.randomUUID().toString();
        if (mapIdToUser.put(counter, user) == null ||
                mapTokenToUser.putIfAbsent(token, user) == null ||
                mapLoginToUser.putIfAbsent(user.getLogin(), user) == null) {
            throw new ServerException(ServerErrorCode.YOU_ARE_ALREADY_REGISTERED);
        }

        return token;
    }

    public void addPatientToDoctor(Patient patient, String doctorToken){
        mapDoctorIdToPatientsId.put( mapTokenToUser.get(doctorToken).getId(), patient.getId());
    }


    public String login(String login, String password) throws ServerException {
        User user = mapLoginToUser.get(login);
        if(user == null) throw new ServerException(ServerErrorCode.YOU_ARE_NOT_REGISTERED_OR_WRONG_LOGIN);
        if(!user.getPassword().equals(password)) throw new ServerException(ServerErrorCode.WRONG_PASSWORD);
        if (mapTokenToUser.inverseBidiMap().getKey(user) != null)
            mapTokenToUser.remove(mapTokenToUser.inverseBidiMap().getKey(user));
        String token = UUID.randomUUID().toString();
        mapTokenToUser.put(token, user);
        return token;
    }

    public void logout(String token) throws ServerException {
        if (mapTokenToUser.remove(token) == null)
            throw new ServerException(ServerErrorCode.YOU_ARE_NOT_LOGGED_IN);
    }

    public void leave(String token) throws ServerException {
        User user = mapTokenToUser.get(token);
        if (user == null) throw new ServerException(ServerErrorCode.YOU_ARE_NOT_LOGGED_IN);
        mapIdToUser.remove(user.getId());
        mapLoginToUser.remove(user.getLogin());
        mapTokenToUser.remove(token);
        mapDoctorIdToPatientsId.remove(user);
    }

    public static DataBase getDataBase() {
        if (singletonDataBase == null) {
            singletonDataBase = new DataBase();
        }
        return singletonDataBase;
    }

    public String changePassword(String login, String oldPassword, String newPassword) throws ServerException {
        User user = mapLoginToUser.get(login);
        if (user == null) throw new ServerException(ServerErrorCode.WRONG_LOGIN);
        if (!user.getPassword().equals(oldPassword)) throw new ServerException(ServerErrorCode.WRONG_PASSWORD);

        mapLoginToUser.get(login).setPassword(newPassword);
        mapIdToUser.get(user.getId()).setPassword(newPassword);

        String token = mapTokenToUser.inverseBidiMap().get(user);
        mapTokenToUser.get(token).setPassword(newPassword);
        return token;
    }



    public void addAppointment(String token, String patientLogin,
                                 String appointment, String explanation) throws ServerException {
        User doctor = mapTokenToUser.get(token);
        User patient = mapLoginToUser.get(patientLogin);
        if (doctor == null) throw new ServerException(ServerErrorCode.YOU_ARE_NOT_LOGGED_IN);
        if (doctor.getClass() != Doctor.class) throw new ServerException(ServerErrorCode.YOU_ARE_NOT_A_DOCTOR);
        if (patient == null || patient.getClass() != Patient.class) throw
                new ServerException(ServerErrorCode.THIS_PATIENT_IS_NOT_IN_THE_DATABASE);
        DoctorAppointment doctorAppointment = new DoctorAppointment(appointment, explanation);
        // Создать Patient и перезаписать целиком эл-ты в map, или как-то использовать set для изменения поля?
        ((Patient) patient).getDoctorAppointmentsList().add(doctorAppointment);
        ((Patient) mapIdToUser.get(patient.getId())).getDoctorAppointmentsList().add(doctorAppointment);
        ((Patient) mapTokenToUser.get(mapTokenToUser.inverseBidiMap().get(patient))).
                getDoctorAppointmentsList().add(doctorAppointment);
    }

    public void deleteAppointment(String token, String patientLogin,
                                  String appointment, String explanation) throws ServerException {
        User doctor = mapTokenToUser.get(token);
        User patient = mapLoginToUser.get(patientLogin);
        if (doctor == null) throw new ServerException(ServerErrorCode.YOU_ARE_NOT_LOGGED_IN);
        if (doctor.getClass() != Doctor.class) throw new ServerException(ServerErrorCode.YOU_ARE_NOT_A_DOCTOR);
        if (patient == null || patient.getClass() != Patient.class) throw
                new ServerException(ServerErrorCode.THIS_PATIENT_IS_NOT_IN_THE_DATABASE);

        DoctorAppointment doctorAppointment = new DoctorAppointment(appointment, explanation);

        ((Patient) patient).getDoctorAppointmentsList().remove(doctorAppointment);
        ((Patient) mapIdToUser.get(patient.getId())).getDoctorAppointmentsList().remove(doctorAppointment);
        ((Patient) mapTokenToUser.get(mapTokenToUser.inverseBidiMap().get(patient))).
                getDoctorAppointmentsList().remove(doctorAppointment);
    }



    public Map<String, User> getMapLoginToUser() {
        return mapLoginToUser;
    }
}
