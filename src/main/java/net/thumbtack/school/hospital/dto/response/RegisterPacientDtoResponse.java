package net.thumbtack.school.hospital.dto.response;

public class RegisterPacientDtoResponse {
    private String token;

    public RegisterPacientDtoResponse(String token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
