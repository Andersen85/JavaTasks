package net.thumbtack.school.ttschool;

public class TrainingException extends Exception{

    private TrainingErrorCode errorCode;

    public TrainingException(TrainingErrorCode trainingErrorCode){
        this.errorCode = trainingErrorCode;
    }

    public TrainingErrorCode getErrorCode() {
        return errorCode;
    }
}
