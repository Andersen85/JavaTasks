package net.thumbtack.school.hospital.dto.response;

import com.google.gson.Gson;

public class RegisterDoctorDtoResponse {
    private String  token;

    public RegisterDoctorDtoResponse(String  token) {
        this.token = token;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String  token) {
        this.token = token;
    }
}
