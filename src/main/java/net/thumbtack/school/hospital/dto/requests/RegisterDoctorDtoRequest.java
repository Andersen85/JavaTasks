package net.thumbtack.school.hospital.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class RegisterDoctorDtoRequest {

    private String firstName;
    private String lastName;
    private String speciality;
    private String login;
    private String password;

    public boolean validateRegisterDoctor(){
        return !firstName.equals("") && !lastName.equals("") && !speciality.equals("") &&
                !login.equals("") && !password.equals("");
    }
}
