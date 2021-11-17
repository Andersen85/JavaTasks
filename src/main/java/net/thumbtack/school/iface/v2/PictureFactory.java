package net.thumbtack.school.iface.v2;

public class PictureFactory {

    private static int quantityRect=0;
    private static int quantityRound=0;

    public static RectPicture createRectPicture(Point leftTop, Point rightBottom, int format){
        quantityRect++;
        return new RectPicture(leftTop.getX(),rightBottom.getX(),
                leftTop.getY(),rightBottom.getY(),format);
    }

    public static RoundPicture createRoundPicture(Point center, int radius, int format){
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
