package net.thumbtack.school.pictures.v2;

import net.thumbtack.school.iface.v2.Movable;
import net.thumbtack.school.iface.v2.Resizable;
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
}
