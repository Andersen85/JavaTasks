package net.thumbtack.school.hospital.exceptions;

public enum ServerErrorCode {

    WRONG_LOGIN("Wrong login!"),
    WRONG_PASSWORD("Wrong password!"),
    USER_ALREADY_LOGINED("User already logined!"),
    YOU_ARE_NOT_LOGGED_IN("You aren't logged in!"),
    WROND_DATA_IN_REQUEST("Wrong data in request!"),
    YOU_ARE_ALREADY_REGISTERED("You are already registered!"),
    LOGIN_IS_ALREADY_TAKEN("Login is already taken by another user");

    private String errorString;

    private ServerErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
