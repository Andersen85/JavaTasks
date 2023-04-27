package net.thumbtack.school.hospital.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class AppointmentDtoRequest {

    private String token;
    private String patientLogin;
    private String appointment;
    private String explanation;
}
