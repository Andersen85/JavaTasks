package net.thumbtack.school.hospital.dto.response;

import net.thumbtack.school.hospital.exceptions.ServerException;

public class ErrorResponse {

    private String error;

    public ErrorResponse(ServerException e){
        error = e.getServerExceptionCode().getErrorString();
    }
}
