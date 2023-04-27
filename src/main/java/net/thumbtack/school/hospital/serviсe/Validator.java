package net.thumbtack.school.hospital.serviсe;

import net.thumbtack.school.hospital.dto.requests.*;
import net.thumbtack.school.hospital.exceptions.ServerErrorCode;
import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.model.User;

public class Validator {

    public static void doctorValidate(RegisterDoctorDtoRequest doctor) throws ServerException {
        if (doctor.getFirstName().trim().equals("") || doctor.getFirstName() == null ||
                doctor.getLastName().trim().equals("") || doctor.getLastName() == null ||
                doctor.getLogin().trim().equals("") || doctor.getLogin() == null ||
                doctor.getPassword().trim().equals("") || doctor.getPassword() == null ||
                doctor.getSpeciality().trim().equals("") || doctor.getSpeciality() == null) {
            throw new ServerException(ServerErrorCode.WROND_DATA_IN_REQUEST);
        }
    }

    public static void pacientValidate(RegisterPatientDtoRequest pacient) throws ServerException {
        if (pacient.getFirstName().trim().equals("") || pacient.getFirstName() == null ||
                pacient.getLastName().trim().equals("") || pacient.getLastName() == null ||
                pacient.getLogin().trim().equals("") || pacient.getLogin() == null ||
                pacient.getPassword().trim().equals("") || pacient.getPassword() == null ||
                pacient.getSickName().trim().equals("") || pacient.getSickName() == null ||
                pacient.getDoctorAppointmentsList().isEmpty() || pacient.getDoctorAppointmentsList() == null) {
            throw new ServerException(ServerErrorCode.WROND_DATA_IN_REQUEST);
        }
    }

    public static void userLoginValidate(UserDtoRequest loginUserDtoRequest) throws ServerException {
        if (loginUserDtoRequest.getLogin() == null || loginUserDtoRequest.getLogin().trim().equals("") ||
                loginUserDtoRequest.getPassword() == null || loginUserDtoRequest.getPassword().trim().equals(""))
            throw new ServerException(ServerErrorCode.WROND_DATA_IN_REQUEST);
    }

    public static void tokenValidate(TokenDtoRequest tokenDtoRequest) throws ServerException {
        if (tokenDtoRequest == null || tokenDtoRequest.getToken().equals(""))
            throw new ServerException(ServerErrorCode.WROND_DATA_IN_REQUEST);
    }

    public static void changePasswordValidate(ChangePasswordDtoRequest changePasswordDtoRequest) throws ServerException {
        if (changePasswordDtoRequest.getLogin() == null || changePasswordDtoRequest.getLogin().trim().equals("") ||
                changePasswordDtoRequest.getOldPassword() == null || changePasswordDtoRequest.getOldPassword().trim().equals("") ||
                changePasswordDtoRequest.getNewPassword() == null || changePasswordDtoRequest.getNewPassword().trim().equals(""))
            throw new ServerException(ServerErrorCode.WRONG_LOGIN);
    }

    public static void appointmetValidate(AppointmentDtoRequest appointmentDtoRequest) throws ServerException {
        if (appointmentDtoRequest == null ||
                appointmentDtoRequest.getAppointment() == null ||
                appointmentDtoRequest.getAppointment().trim().equals("") ||
                appointmentDtoRequest.getPatientLogin() == null ||
                appointmentDtoRequest.getPatientLogin().trim().equals("") ||
                appointmentDtoRequest.getToken() == null ||
                appointmentDtoRequest.getToken().trim().equals("") ||
                appointmentDtoRequest.getExplanation() == null ||
                appointmentDtoRequest.getExplanation().trim().equals(""))
        throw new ServerException(ServerErrorCode.WROND_DATA_IN_REQUEST);
    }
}
