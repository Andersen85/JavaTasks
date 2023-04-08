package net.thumbtack.school.hospital.model;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Doctor extends User {

    private String speciality;

    public Doctor(String firstName, String lastName, String speciality, String login, String password) {
        super(firstName, lastName, login, password, 0);
        this.speciality = speciality;
    }

}
