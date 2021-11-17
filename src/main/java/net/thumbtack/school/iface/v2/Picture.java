package net.thumbtack.school.iface.v2;

import net.thumbtack.school.iface.Movable;
import net.thumbtack.school.iface.Resizable;
import net.thumbtack.school.winobjects.v2.Desktop;

public abstract class Picture implements Movable, Resizable {

    private int format;

    public Picture(int format) {
        this.format = format;
    }

    public int getFormat() {
        return format;
    }

    public void setFormat(int format) {
        this.format = format;
    }

    public abstract boolean isInside(int x, int y);
    public abstract boolean isInside(Point point);
    public abstract boolean isFullyVisibleOnDesktop(Desktop desktop);
    public abstract void moveTo(int x,int y);



}
