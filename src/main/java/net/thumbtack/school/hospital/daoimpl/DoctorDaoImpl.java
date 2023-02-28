package net.thumbtack.school.hospital.daoimpl;


import net.thumbtack.school.hospital.dao.DoctorDao;
import net.thumbtack.school.hospital.data.DataBase;
import net.thumbtack.school.hospital.model.Doctor;

public class DoctorDaoImpl implements DoctorDao {

    @Override
    public String insert(Doctor doctor) {
        return DataBase.getDataBase().insert(doctor);
    }



}
