package net.thumbtack.school.hospital.model;


// REVU используйте lombok
import net.thumbtack.school.hospital.dto.requests.RegisterPacientDtoRequest;

import java.util.List;

// REVU Patient
public class Pacient extends User {

    private String sickName;
    private List<DoctorAppointment> doctorAppointmentsList;

    public Pacient(String firstName, String lastName, String login, String password,
                   String sickName, List<DoctorAppointment> doctorAppointment) {
        super(firstName, lastName, login, password,0);
        setSickName(sickName);
        this.doctorAppointmentsList = doctorAppointment;
    }

    // REVU классы модели не должны знать про классы DTO
    public Pacient(RegisterPacientDtoRequest registerPacientDtoRequest){
        super(registerPacientDtoRequest.getFirstName(),
                registerPacientDtoRequest.getLastName(),
                registerPacientDtoRequest.getLogin(),
                registerPacientDtoRequest.getPassword(), 0);
        sickName = registerPacientDtoRequest.getSickName();
        doctorAppointmentsList = registerPacientDtoRequest.getDoctorAppointmentsList();
    }
    /*public Pacient(String firstName, String lastName, String login, String password,
                   String sickName, List<DoctorAppointment> doctorAppointments) {
        super(firstName, lastName, login, password);
        setSickName(sickName);
        doctorAppointmentsList.addAll(doctorAppointments);
    }*/

    public String getSickName() {
        return sickName;
    }

    public void setSickName(String sickName) {
        this.sickName = sickName;
    }

    public List<DoctorAppointment> getDoctorAppointmentsList() {
        return doctorAppointmentsList;
    }

    public void setDoctorAppointmentsList(List<DoctorAppointment> doctorAppointmentsList) {
        this.doctorAppointmentsList = doctorAppointmentsList;
    }
}
