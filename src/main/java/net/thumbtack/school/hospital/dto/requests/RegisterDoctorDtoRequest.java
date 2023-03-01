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

    // REVU перенесите этот метод в сервис, сделав там его static
    // пусть сервис сам вадидирует
    // ему лучше знать, как валидировать
    // и не надо ему быть boolean
    // при ошибке пусть выбрасывает ServerException с соответствующим ServerErrorCode
    public boolean validateRegisterDoctor(){
        // REVU надо на null проверять сначала, иначе при null получите NullPointerException
        return !firstName.equals("") && !lastName.equals("") && !speciality.equals("") &&
                !login.equals("") && !password.equals("");
    }
}
