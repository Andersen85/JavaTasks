package net.thumbtack.school.hospital.serviсe;

import com.google.gson.Gson;
import net.thumbtack.school.hospital.daoimpl.UserDaoIml;
import net.thumbtack.school.hospital.data.DataBase;
import net.thumbtack.school.hospital.dto.requests.ChangePasswordDtoRequest;
import net.thumbtack.school.hospital.dto.requests.UserDtoRequest;
import net.thumbtack.school.hospital.dto.requests.TokenDtoRequest;
import net.thumbtack.school.hospital.dto.response.EmptyResponse;
import net.thumbtack.school.hospital.dto.response.ErrorResponse;
import net.thumbtack.school.hospital.dto.response.UserDtoResponse;
import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.model.User;


public class UserService {
    private Gson gson = new Gson();
    private UserDaoIml userDao = new UserDaoIml();


    public String login(String requestJsonString) {
        try {
            UserDtoRequest loginUserDtoRequest = ServiceUtils.getClassFromJson(requestJsonString,
                    UserDtoRequest.class);
            Validator.userLoginValidate(loginUserDtoRequest);
            UserDtoResponse loginUserDtoResponse = new UserDtoResponse(
                    userDao.login(loginUserDtoRequest.getLogin(), loginUserDtoRequest.getPassword()));
            return gson.toJson(loginUserDtoResponse);
        } catch (ServerException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    public String logout(String requestJsonString) {
        try {
            TokenDtoRequest tokenDtoRequest = ServiceUtils.getClassFromJson(requestJsonString, TokenDtoRequest.class);
            Validator.tokenValidate(tokenDtoRequest);
            userDao.logout(tokenDtoRequest.getToken());
            return gson.toJson(new EmptyResponse());
        } catch (ServerException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    public String leave(String requestJsonString) {
        try {
            TokenDtoRequest tokenDtoRequest = ServiceUtils.getClassFromJson(requestJsonString, TokenDtoRequest.class);
            Validator.tokenValidate(tokenDtoRequest);
            userDao.leave(tokenDtoRequest.getToken());
            return gson.toJson(new EmptyResponse());
        } catch (ServerException e) {
            return gson.toJson(new ErrorResponse(e));
        }
    }

    public String changePassword(String requestJsonString) {
        try{
            ChangePasswordDtoRequest changePasswordDtoRequest = ServiceUtils.getClassFromJson(requestJsonString,
                    ChangePasswordDtoRequest.class);
            Validator.changePasswordValidate(changePasswordDtoRequest);
            userDao.changePassword(changePasswordDtoRequest.getLogin(), changePasswordDtoRequest.getOldPassword(),
                    changePasswordDtoRequest.getNewPassword());
            return gson.toJson(new EmptyResponse());
        }catch (ServerException e){
            return gson.toJson(new ErrorResponse(e));
        }
    }
}
