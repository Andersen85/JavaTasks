package net.thumbtack.school.pictures.managers;

import net.thumbtack.school.exceptions.v3.GraphicErrorCode;
import net.thumbtack.school.exceptions.v3.GraphicException;
import net.thumbtack.school.iface.v3.Movable;
import net.thumbtack.school.pictures.v3.Picture;
import net.thumbtack.school.pictures.v3.Point;
import net.thumbtack.school.pictures.v3.RoundPicture;
import net.thumbtack.school.pictures.v3.SignedRoundPicture;
import net.thumbtack.school.pictures.v3.SignedRectPicture;

public class Manager <T extends Picture> {

    private T picture;

    public Manager(T picture) throws GraphicException{
        if(picture == null) throw new GraphicException(GraphicErrorCode.NULL_PICTURE);
        this.picture = picture;
    }

    public T getPicture() {
        return picture;
    }

    public void setPicture(T picture) throws GraphicException {
        if(picture == null) throw new GraphicException(GraphicErrorCode.NULL_PICTURE);
        this.picture = picture;
    }

    public void moveTo(int x, int y) {
        //Передвигает картинку, находящуюся под управлением менеджера, так, чтобы ее базовая
        //точка(левый верхний угол или центр соответственно) его оказалась в точке (x, y)
        picture.moveTo(x,y);
    }

    public void moveTo(Point point) {
        //Передвигает картинку так, так, чтобы ее базовая точка(левый верхний угол или центр соответственно) ее оказалась
        //в точке point.
        picture.moveTo(point);
    }
}
