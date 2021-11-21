package net.thumbtack.school.exceptions.v3;

public enum GraphicErrorCode {
    WRONG_PICTURE_FORMAT("WRONG_PICTURE_FORMAT"), //Недопустимая текстовая строка в качестве формата картинки
    NULL_PICTURE_FORMAT("NULL_PICTURE_FORMAT"), //Передан null в качестве формата картинки
    NULL_SIGNATURE("NULL_SIGNATURE"),
    NULL_PICTURE("NULL_PICTURE");

    private String errorString;

    private GraphicErrorCode(String errorString) {
        this.errorString = errorString;
    }

    public String getErrorString() {
        return errorString;
    }
}
