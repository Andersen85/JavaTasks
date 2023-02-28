package net.thumbtack.school.hospital.daoimpl;

import net.thumbtack.school.hospital.dao.PacientDao;
import net.thumbtack.school.hospital.data.DataBase;
import net.thumbtack.school.hospital.model.Pacient;

public class PacientDaoImpl implements PacientDao {


    @Override
    public String insert(Pacient pacient) {
        return DataBase.getDataBase().insert(pacient);
    }
}
