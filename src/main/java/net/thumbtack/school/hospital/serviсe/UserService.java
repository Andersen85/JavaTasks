package net.thumbtack.school.hospital.serviсe;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.daoimpl.UserDaoIml;
import net.thumbtack.school.hospital.data.DataBase;
import net.thumbtack.school.hospital.dto.requests.LoginUserDtoRequest;
import net.thumbtack.school.hospital.dto.requests.TokenDtoRequest;
import net.thumbtack.school.hospital.dto.response.EmptyResponse;
import net.thumbtack.school.hospital.dto.response.ErrorResponse;
import net.thumbtack.school.hospital.dto.response.LoginUserDtoResponse;
import net.thumbtack.school.hospital.exceptions.ServerException;


public class UserService {
    private Gson gson = new Gson();
    private UserDaoIml userDao = new UserDaoIml();


    public String login(String requestJsonString) {
        try {
            LoginUserDtoRequest loginUserDtoRequest = gson.fromJson(requestJsonString, LoginUserDtoRequest.class);
            LoginUserDtoResponse loginUserDtoResponse = new LoginUserDtoResponse(
                    userDao.login(loginUserDtoRequest.getLogin(), loginUserDtoRequest.getPassword()));
            return gson.toJson(loginUserDtoResponse);
        } catch (ServerException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    public String logout(String requestJsonString) {
        try {
            TokenDtoRequest tokenDtoRequest = gson.fromJson(requestJsonString, TokenDtoRequest.class);
            userDao.logout(tokenDtoRequest.getToken());
            return gson.toJson(new EmptyResponse());
        } catch (ServerException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    public String leave(String requestJsonString) {
        try{
            TokenDtoRequest tokenDtoRequest = gson.fromJson(requestJsonString, TokenDtoRequest.class);
            userDao.leave(tokenDtoRequest.getToken());
            return gson.toJson(new EmptyResponse());
        } catch (ServerException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }
}
