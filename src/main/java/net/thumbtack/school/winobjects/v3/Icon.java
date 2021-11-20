package net.thumbtack.school.winobjects.v3;

import net.thumbtack.school.exceptions.v3.GraphicErrorCode;
import net.thumbtack.school.exceptions.v3.GraphicException;
import net.thumbtack.school.iface.v3.Movable;
import net.thumbtack.school.pictures.v3.Point;
import net.thumbtack.school.iface.v3.Signed;

public class Icon implements Movable, Signed {

    private int x;
    private int y;
    private String signature;
    public Icon(int x, int y, String signature) throws GraphicException {
        //Создает иконку с заданным текстом.Иконка помещается в точку (x, y).
        if(signature == null) {throw new GraphicException(GraphicErrorCode.NULL_SIGNATURE);}

        this.x = x;
        this.y = y;
        this.signature = signature;
    }

    public Icon(Point point, String signature) throws GraphicException {
        //Создает иконку с заданным текстом. Иконка помещается в точку point.
        this(point.getX(), point.getY(), signature);
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public String getSignature() {
        return signature;
    }

    @Override
    public void setSignature(String signature) throws GraphicException {
        if(signature == null){ throw new GraphicException(GraphicErrorCode.NULL_SIGNATURE);
        }
        this.signature = signature;
    }

    @Override
    public void moveTo(int x, int y) {
        //Перемещает иконку в точку (x,y).
        setX(x);
        setY(y);
    }

    @Override
    public void moveTo(Point point) {
        //Перемещает иконку в точку point.
        setX(point.getX());
        setY(point.getY());
    }
    @Override
    public void moveRel(int dx, int dy) {
        //Перемещает иконку на (dx,dy).
        setX(x+dx);
        setY(y+dy);
    }


}
