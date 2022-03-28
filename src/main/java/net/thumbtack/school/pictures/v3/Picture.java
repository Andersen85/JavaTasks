package net.thumbtack.school.pictures.v3;

import net.thumbtack.school.exceptions.v3.GraphicErrorCode;
import net.thumbtack.school.exceptions.v3.GraphicException;
import net.thumbtack.school.iface.v3.Movable;
import net.thumbtack.school.iface.v3.Resizable;
import net.thumbtack.school.pictures.v3.Point;
import net.thumbtack.school.winobjects.v3.Desktop;

public abstract class Picture implements Movable, Resizable {

    private PictureFormat format;

    public Picture(PictureFormat format) throws GraphicException {
        setFormat(format);

    }

    public PictureFormat getFormat() {
        return format;
    }

    public void setFormat(PictureFormat format) throws GraphicException {
        if(format == null){throw new GraphicException(GraphicErrorCode.NULL_PICTURE_FORMAT);}
        this.format = format;
    }

    public abstract boolean isInside(int x, int y);
    // REVU этот метод не должен быть абстрактным
    // его можно тут и написать
    // и во всех наследниках удалить
    public abstract boolean isInside(Point point);
    public abstract boolean isFullyVisibleOnDesktop(Desktop desktop);
}
