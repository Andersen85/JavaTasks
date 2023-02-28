package net.thumbtack.school.hospital.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import net.thumbtack.school.hospital.model.DoctorAppointment;

import java.util.List;
@Data
@AllArgsConstructor
@NoArgsConstructor
public class RegisterPacientDtoRequest {

    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private String sickName;
    private List<DoctorAppointment> doctorAppointmentsList;


    public boolean validateRegisterDoctor(){
        //Проверки
        return !firstName.equals("") && !lastName.equals("") &&
                !login.equals("") && !password.equals("") &&
                !sickName.equals("") && !doctorAppointmentsList.isEmpty();
    }
}
