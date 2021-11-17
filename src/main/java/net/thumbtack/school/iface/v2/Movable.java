package net.thumbtack.school.iface.v2;

import net.thumbtack.school.pictures.v2.Point;

public interface Movable {

    void moveTo(int x, int y);
    //Передвигает картинку так,  так, чтобы его базовая точка (левый верхний угол или центр соответственно)
    // оказалась в точке (x,y)

    default void moveTo(Point point){
        moveTo(point.getX(), point.getY());
    };
    //Передвигает картинку так,  так, чтобы его базовая точка (левый верхний угол или центр соответственно)
    // оказалась в точке point

    void moveRel(int dx, int dy);
    //Передвигает картинку на (dx, dy)

}
