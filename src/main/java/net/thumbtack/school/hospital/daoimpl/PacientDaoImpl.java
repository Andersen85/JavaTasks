package net.thumbtack.school.hospital.daoimpl;

import net.thumbtack.school.hospital.dao.PacientDao;
import net.thumbtack.school.hospital.data.DataBase;
import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.model.Patient;

public class PacientDaoImpl implements PacientDao {


    @Override
    public String insert(Patient patient) throws ServerException {
        return DataBase.getDataBase().insert(patient);
    }
}
