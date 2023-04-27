package net.thumbtack.school.hospital.daoimpl;


import net.thumbtack.school.hospital.dao.DoctorDao;
import net.thumbtack.school.hospital.data.DataBase;
import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.model.Doctor;

public class DoctorDaoImpl implements DoctorDao {

    @Override
    public String insert(Doctor doctor) throws ServerException {
        return DataBase.getDataBase().insert(doctor);
    }

    @Override
    public void addAppointment(String token, String patientLogin, String appointment, String explanation) throws ServerException {
        DataBase.getDataBase().addAppointment(token, patientLogin, appointment, explanation);
    }

    @Override
    public void deleteAppointment(String token, String patientLogin, String appointment, String explanation) throws ServerException {
        DataBase.getDataBase().deleteAppointment(token, patientLogin, appointment, explanation);
    }



}
