package net.thumbtack.school.hospital.dao;


import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.model.Doctor;

public interface DoctorDao {

    String insert(Doctor doctor) throws ServerException;

}
