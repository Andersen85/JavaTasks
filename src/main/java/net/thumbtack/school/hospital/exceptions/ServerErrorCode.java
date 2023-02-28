package net.thumbtack.school.hospital.exceptions;

public enum ServerErrorCode {

    WRONG_LOGIN("Wrong login!"),
    WRONG_PASSWORD("Wrong password!"),
    USER_ALREADY_LOGINED("User already logined!"),
    YOU_ARE_NOT_LOGGED_IN("You aren't logged in!");

    private String errorString;

    private ServerErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
