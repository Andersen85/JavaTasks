package net.thumbtack.school.hospital.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class ChangePasswordDtoRequest {

    private String login;
    private String oldPassword;
    private String newPassword;
}
