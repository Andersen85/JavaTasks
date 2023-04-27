package net.thumbtack.school.hospital.daoimpl;

import net.thumbtack.school.hospital.dao.PatientDao;
import net.thumbtack.school.hospital.data.DataBase;
import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.model.Patient;

public class PatientDaoImpl implements PatientDao {


    @Override
    public String insert(Patient patient) throws ServerException {
        return DataBase.getDataBase().insert(patient);
    }

    @Override
    public void addPatientToDoctor(Patient patient, String doctorToken) {
        DataBase.getDataBase().addPatientToDoctor(patient, doctorToken);
    }
}
