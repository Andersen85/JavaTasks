package net.thumbtack.school.hospital.dto.requests;

import lombok.AllArgsConstructor;
import lombok.Data;

@AllArgsConstructor
@Data

public class UserDtoRequest {
    private String login;
    private String password;
}
