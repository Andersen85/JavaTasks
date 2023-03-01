package net.thumbtack.school.hospital.model;


import net.thumbtack.school.hospital.dto.requests.RegisterDoctorDtoRequest;

// REVU используйте lombok
public class Doctor extends User {

    private String speciality;

    public Doctor(String firstName, String lastName, String speciality, String login, String password) {
        super(firstName, lastName, login, password, 0);
        this.speciality = speciality;
    }

    // REVU классы модели не должны знать про классы DTO
    public Doctor(RegisterDoctorDtoRequest registerDoctorDtoRequest){
        super(registerDoctorDtoRequest.getFirstName(),
                registerDoctorDtoRequest.getLastName(),
                registerDoctorDtoRequest.getLogin(),
                registerDoctorDtoRequest.getPassword(),0);
        speciality = registerDoctorDtoRequest.getSpeciality();
    }

    public String getSpeciality() {
        return speciality;
    }

    public void setSpeciality(String speciality) {
        this.speciality = speciality;
    }
}
