package net.thumbtack.school.hospital.serviсe;

import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.hospital.dto.requests.LoginUserDtoRequest;
import net.thumbtack.school.hospital.dto.requests.RegisterDoctorDtoRequest;
import net.thumbtack.school.hospital.dto.requests.RegisterPacientDtoRequest;
import net.thumbtack.school.hospital.dto.requests.TokenDtoRequest;
import net.thumbtack.school.hospital.exceptions.ServerErrorCode;
import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.model.User;

public class Validator {

    public static void doctorValidate(RegisterDoctorDtoRequest doctor) throws ServerException {
        if (doctor.getFirstName().equals("") || doctor.getLastName().equals("") ||
                doctor.getLogin().equals("") || doctor.getPassword().equals("") || doctor.getSpeciality().equals("")) {
            throw new ServerException(ServerErrorCode.WROND_DATA_IN_REQUEST);
        }
    }

    public static void pacientValidate(RegisterPacientDtoRequest pacient) throws ServerException {
        if (pacient.getFirstName().equals("") || pacient.getLastName().equals("") ||
                pacient.getLogin().equals("") || pacient.getPassword().equals("") || pacient.getSickName().equals("") ||
                pacient.getDoctorAppointmentsList().isEmpty()) {
            throw new ServerException(ServerErrorCode.WROND_DATA_IN_REQUEST);
        }
    }

    public static void userLoginValidate(User user, String password) throws ServerException {
        if (user == null) throw new ServerException(ServerErrorCode.WRONG_LOGIN);
        if (!user.getPassword().equals(password)) throw new ServerException(ServerErrorCode.WRONG_PASSWORD);
    }

    public static void tokenValidate(TokenDtoRequest tokenDtoRequest) throws ServerException {
        if(tokenDtoRequest == null) throw new ServerException(ServerErrorCode.WROND_DATA_IN_REQUEST);
    }
}
