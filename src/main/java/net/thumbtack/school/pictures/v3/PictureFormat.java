package net.thumbtack.school.pictures.v3;

import net.thumbtack.school.exceptions.v3.GraphicErrorCode;
import net.thumbtack.school.exceptions.v3.GraphicException;

public enum PictureFormat {
    TIFF,
    GIF,
    PNG,
    JPG;

    public static PictureFormat fromString(String formatString) throws GraphicException {
        //Возвращает PictureFormat по переданной текстовой строке
        if(formatString==null) {
            throw new GraphicException(GraphicErrorCode.NULL_PICTURE_FORMAT);
        }
        PictureFormat format;
        try{
            format = valueOf(formatString);
        }
        catch (IllegalArgumentException E) {
            throw new GraphicException(GraphicErrorCode.WRONG_PICTURE_FORMAT);
        }
        return format;
    }

}
