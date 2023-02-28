package net.thumbtack.school.hospital.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor

public abstract class User {
    private String firstName;
    private String lastName;
    private String login;
    private String password;
    private int id;
}
