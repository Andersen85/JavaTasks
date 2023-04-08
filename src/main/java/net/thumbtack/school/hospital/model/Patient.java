package net.thumbtack.school.hospital.model;


import lombok.Data;

import java.util.List;

@Data
public class Patient extends User {

    private String sickName;
    private List<DoctorAppointment> doctorAppointmentsList;

    public Patient(String firstName, String lastName, String login, String password,
                   String sickName, List<DoctorAppointment> doctorAppointment) {
        super(firstName, lastName, login, password, 0);
        setSickName(sickName);
        this.doctorAppointmentsList = doctorAppointment;
    }

}
