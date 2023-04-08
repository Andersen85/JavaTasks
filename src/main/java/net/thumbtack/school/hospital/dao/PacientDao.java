package net.thumbtack.school.hospital.dao;

import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.model.Patient;

public interface PacientDao {

     String insert(Patient patient) throws ServerException;
}
