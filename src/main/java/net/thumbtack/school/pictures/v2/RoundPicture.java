package net.thumbtack.school.pictures.v2;

import java.util.Objects;

public class RoundPicture {
    private Point center;
    private int radius;
    private int format;

    public RoundPicture(Point center, int radius, int format) {
        this.center = center;
        this.radius = radius;
        this.format = format;
    }

    public RoundPicture(int xCenter,int yCenter, int radius, int format) {
        this(new Point(xCenter,yCenter), radius,format);
    }

    public RoundPicture(Point center, int radius) {
        this(center,radius,1);
    }

    public RoundPicture(int xCenter,int yCenter, int radius) {
        this(new Point(xCenter,yCenter),radius);
    }

    public Point getCenter() {
        return center;
    }

    public int getRadius() {
        return radius;
    }

    public int getFormat() {
        return format;
    }

    public void moveTo(int x, int y){
        center.setX(x);
        center.setY(y);
    }

    public void moveTo(Point point){
        setCenter(point);
    }

    public void setCenter(Point center) {
        this.center = center;
    }

    public void setRadius(int radius) {
        this.radius = radius;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public void moveRel(int dx, int dy){
        center.setX(center.getX()+dx);
        center.setY(center.getY()+dy);
    }

    public void resize(double ratio){
        double R = ratio*radius;
        if (R<1) R=1;
        radius = (int) R;
    }


    public boolean isInside(int x, int y){
        double xCenter = (double) center.getX();
        double yCenter = (double) center.getY();
        double xPoint = (double) x;
        double yPoint = (double) y;
        return Math.sqrt(Math.pow(xCenter-xPoint,2)+Math.pow(yCenter-yPoint,2)) <= radius;
    }

    public boolean isInside(Point point){
        double xCenter = (double) center.getX();
        double yCenter = (double) center.getY();
        double xPoint = (double) point.getX();
        double yPoint = (double) point.getY();
        return Math.sqrt(Math.pow(xCenter-xPoint,2)+Math.pow(yCenter-yPoint,2)) <= radius;
    }

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
        return radius == that.radius && format == that.format && Objects.equals(center, that.center);
    }

    @Override
    public int hashCode() {
        return Objects.hash(center, radius, format);
    }
}

