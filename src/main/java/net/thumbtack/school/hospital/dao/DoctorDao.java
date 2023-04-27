package net.thumbtack.school.hospital.dao;


import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.model.Doctor;

public interface DoctorDao {

    String insert(Doctor doctor) throws ServerException;
    void addAppointment(String token, String patientLogin, String appointment, String explanation) throws ServerException;
    void deleteAppointment(String token, String patientLogin, String appointment, String explanation) throws ServerException;
}
