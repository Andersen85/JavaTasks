package net.thumbtack.school.pictures.v3;

import net.thumbtack.school.exceptions.v3.GraphicException;
import net.thumbtack.school.winobjects.v3.Desktop;

public class RoundPicture extends Picture {

    private Point center;
    private int radius;


    public RoundPicture(Point center, int radius, PictureFormat format) throws GraphicException {
        super(format);
        this.center = center;
        this.radius = radius;
    }

    public RoundPicture(int xCenter, int yCenter, int radius, PictureFormat format) throws GraphicException {
        this(new Point(xCenter,yCenter), radius,format);
    }

    public RoundPicture(Point center, int radius, String format) throws GraphicException {
        this(center, radius, PictureFormat.fromString(format));
    }

    public RoundPicture(int xCenter, int yCenter, int radius, String format) throws GraphicException {
        this(xCenter, yCenter, radius, PictureFormat.fromString(format));
    }

    public RoundPicture(Point center, int radius) throws GraphicException {
        this(center,radius,PictureFormat.GIF);
    }

    public RoundPicture(int xCenter, int yCenter, int radius) throws GraphicException {
        this(new Point(xCenter,yCenter),radius);
    }

    public Point getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }


    @Override
    // REVU а этот метод должен быть в Picture
    // во всех остальных классах его переопределять не надо
    // (- Но ведь в Picture этот метод абстрактный, т.к. у разных картинок отличается его реализация)
    public void moveTo(int x, int y){
        center.setX(x);
        center.setY(y);
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    @Override
    public void moveRel(int dx, int dy){
        center.setX(center.getX()+dx);
        center.setY(center.getY()+dy);
    }

    @Override
    public void resize(double ratio){
        double R = ratio*radius;
        if (R<1) R=1;
        radius = (int) R;
    }


    @Override
    public boolean isInside(int x, int y){
        double xCenter = (double) center.getX();
        double yCenter = (double) center.getY();
        double xPoint = (double) x;
        double yPoint = (double) y;
        return Math.sqrt(Math.pow(xCenter-xPoint,2)+Math.pow(yCenter-yPoint,2)) <= radius;
    }

    @Override
    public boolean isFullyVisibleOnDesktop(Desktop desktop){
        Point bottomRight = new Point(desktop.getWidth(),desktop.getHeight());
        return     center.getX()+radius <= bottomRight.getX()-1
                && center.getX()-radius >= 0
                && center.getY()+radius <= bottomRight.getY()-1
                && center.getY()-radius >=0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RoundPicture that = (RoundPicture) o;

        if (getRadius() != that.getRadius()) return false;
        return getCenter() != null ? getCenter().equals(that.getCenter()) : that.getCenter() == null;
    }

    @Override
    public int hashCode() {
        int result = getCenter() != null ? getCenter().hashCode() : 0;
        result = 31 * result + getRadius();
        return result;
    }
}

