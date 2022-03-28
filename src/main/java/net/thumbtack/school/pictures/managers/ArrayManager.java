package net.thumbtack.school.pictures.managers;

import net.thumbtack.school.exceptions.v3.GraphicErrorCode;
import net.thumbtack.school.exceptions.v3.GraphicException;
import net.thumbtack.school.pictures.v3.Picture;
import net.thumbtack.school.pictures.v3.Point;
import net.thumbtack.school.winobjects.v3.Desktop;
import net.thumbtack.school.winobjects.v3.Cursor;

public class ArrayManager<T extends Picture> {

    private T[] pictures;

    public ArrayManager(T[] pictures) throws GraphicException {
        if(pictures == null){ throw new GraphicException(GraphicErrorCode.NULL_PICTURE);}
        for(Picture picture: pictures){
            if(picture == null) throw new GraphicException(GraphicErrorCode.NULL_PICTURE);
        }
        this.pictures = pictures;
    }

    public T[] getPictures() {
        return pictures;
    }

    public void setPictures(T[] pictures) throws GraphicException {
        if(pictures == null){throw new GraphicException(GraphicErrorCode.NULL_PICTURE);}
        this.pictures = pictures;
    }

    public T getPicture(int i){
        return pictures[i];
    }

    public void setPicture(T picture, int i) throws GraphicException {
        for (int j = 0; j < getPictures().length; j++) {
            if (j == i) {
                this.pictures[j] = picture;
            }
        }
    }

    // REVU нет, метод не должен быть шаблонным
    // обычный метод, только параметр типа ArrayManager<? extends...>
    public <K extends Picture> boolean isSameSize(ArrayManager<K> arrayManager){
        return arrayManager.getPictures().length == pictures.length;
    }

    public boolean allPicturesFullyVisibleOnDesktop(Desktop desktop) {
        //Определяет, лежат ли все картинки, находящиеся под контролем менеджера, в пределах некоторого Desktop.
        for(T picture: pictures){
            if(!picture.isFullyVisibleOnDesktop(desktop)){
                return false;
            }
        }
        return true;
    }

    public boolean anyPictureFullyVisibleOnDesktop(Desktop desktop) {
        //Определяет, лежит ли хоть одна картинка из находящихся под контролем менеджера, в пределах некоторого Desktop.
        for(T picture:pictures){
            if(picture.isFullyVisibleOnDesktop(desktop)){
                return true;
            }
        }
        return false;
    }

    public T getFirstPictureFromCursor(Cursor cursor) {
        //Возвращает первую картинку в списке менеджера, над которой находится некоторый Cursor.Считается, что
        //курсор находится над картинкой, если его координаты находятся в пределах картинки.Если такой
        //картинки нет, возвращает null.
        for (T picture: pictures){
            if(picture.isInside(cursor.getX(),cursor.getY())) {
                return picture;
            }
        }
        return null;
    }

}
