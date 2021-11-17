package net.thumbtack.school.iface.v2;

import net.thumbtack.school.winobjects.v2.Desktop;

public class Point {
    private int x;
    private int y;

    public Point(int x, int y){
        this.x = x;
        this.y = y;
    }

    public Point(){
        x=0;
        y=0;
    }

    public Point(Point point){
        this(point.getX(), point.getY());
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setX(int x) {
        this.x = x;
    }

    public void setY(int y) {
        this.y = y;
    }

    public void moveTo(int x, int y){
        this.x=x;
        this.y=y;
    }

    public void moveRel(int dx, int dy){
        x+=dx;
        y+=dy;
    }

    public boolean isVisibleOnDesktop(Desktop desktop){
        if(desktop.getHeight()<=0||desktop.getWidth()<=0) throw new IllegalArgumentException("Such a desktop does not exist!");
        return x >= 0 && x < desktop.getWidth() && y >= 0 && y < desktop.getHeight();
    }

    public boolean isNotVisibleOnDesktop(Desktop desktop){
        if(desktop.getHeight()<=0||desktop.getWidth()<=0) throw new IllegalArgumentException("Such a desktop does not exist!");
        if(x <= 0 || x >= desktop.getWidth() || y <= 0 || y >= desktop.getHeight()) return true;
        return false;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + x;
        result = prime * result + y;
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Point other = (Point) obj;
        if (x != other.x)
            return false;
        return y == other.y;
    }

}
