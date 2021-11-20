package net.thumbtack.school.winobjects.v3;

import java.util.Objects;

public class Desktop {
    private int width;
    private int height;

    public Desktop(int width, int height) {
        this.width=width;
        this.height=height;
    }

    public Desktop(){
        // REVU this(640,480);
        this(640,480);
    }

    public int getWidth() {
        return width;
    }

    public int getHeight() {
        return height;
    }

    public int getArea(){
        return width*height;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Desktop desktop = (Desktop) o;
        return width == desktop.width && height == desktop.height;
    }

    @Override
    public int hashCode() {
        return Objects.hash(width, height);
    }
}
