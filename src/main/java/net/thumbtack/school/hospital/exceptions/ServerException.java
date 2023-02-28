package net.thumbtack.school.hospital.exceptions;

public class ServerException extends Exception{

    private ServerErrorCode serverErrorCode;

    public ServerException(ServerErrorCode serverErrorCode) {
        this.serverErrorCode = serverErrorCode;
    }

    public ServerErrorCode getServerExceptionCode() {
        return serverErrorCode;
    }
}
