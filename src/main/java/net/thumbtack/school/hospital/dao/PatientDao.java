package net.thumbtack.school.hospital.dao;

import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.model.Patient;

public interface PatientDao {

     String insert(Patient patient) throws ServerException;
     void addPatientToDoctor(Patient patient, String doctorToken);

}
