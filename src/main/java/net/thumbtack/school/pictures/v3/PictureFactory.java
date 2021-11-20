package net.thumbtack.school.pictures.v3;

import net.thumbtack.school.exceptions.v3.GraphicException;
import net.thumbtack.school.pictures.v3.Point;
import net.thumbtack.school.pictures.v3.RectPicture;
import net.thumbtack.school.pictures.v3.RoundPicture;

public class PictureFactory {

    private static int quantityRect=0;
    private static int quantityRound=0;

    public static RectPicture createRectPicture(Point leftTop, Point rightBottom, PictureFormat format) throws GraphicException {
        quantityRect++;
        return new RectPicture(leftTop.getX(),rightBottom.getX(),
                leftTop.getY(),rightBottom.getY(),format);
    }

    public static RoundPicture createRoundPicture(Point center, int radius, PictureFormat format) throws GraphicException {
        quantityRound++;
        return  new RoundPicture(center, radius, format);
    }

    public static int getRectPictureCount(){
        return  quantityRect;
    }

    public static int getRoundPictureCount(){
        return quantityRound;
    }

    public static int getPictureCount(){
        return quantityRect+quantityRound;
    }

    public static void reset(){
        quantityRound=0;
        quantityRect=0;
    }

}
