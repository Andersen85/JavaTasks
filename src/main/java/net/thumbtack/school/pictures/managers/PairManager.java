package net.thumbtack.school.pictures.managers;

import net.thumbtack.school.exceptions.v3.GraphicErrorCode;
import net.thumbtack.school.exceptions.v3.GraphicException;
import net.thumbtack.school.pictures.v3.Picture;
import net.thumbtack.school.winobjects.v3.Desktop;

public class PairManager<T extends Picture, S extends Picture> {

    private T firstPicture;
    private S secondPicture;

    public PairManager(T firstPicture, S secondPicture) throws GraphicException {
        if (firstPicture == null || secondPicture == null) {
            throw new GraphicException(GraphicErrorCode.NULL_PICTURE);
        }
        this.firstPicture = firstPicture;
        this.secondPicture = secondPicture;
    }

    public T getFirstPicture() {
        return firstPicture;
    }

    public void setFirstPicture(T firstPicture) throws GraphicException {
        if (firstPicture == null) {
            throw new GraphicException(GraphicErrorCode.NULL_PICTURE);
        }
        this.firstPicture = firstPicture;
    }

    public S getSecondPicture() {
        return secondPicture;
    }

    public void setSecondPicture(S secondPicture) throws GraphicException {
        if (secondPicture == null) {
            throw new GraphicException(GraphicErrorCode.NULL_PICTURE);
        }
        this.secondPicture = secondPicture;
    }

    // REVU нет, метод не должен быть шаблонным
    // обычный метод, только параметр типа PairManager<? extends...>
    public boolean allPicturesFullyVisibleOnDesktop(PairManager<? extends Picture, ? extends Picture> pairManager,
                                                    Desktop desktop) {
        //проверяющий, верно ли, что одновременно находятся на Desktop все картинки данного PairManager
        // и еще одного PairManager.
        return firstPicture.isFullyVisibleOnDesktop(desktop) && secondPicture.isFullyVisibleOnDesktop(desktop)
                && pairManager.getFirstPicture().isFullyVisibleOnDesktop(desktop)
                && pairManager.getSecondPicture().isFullyVisibleOnDesktop(desktop);
    }


    // REVU то же
    public static <K extends Picture, F extends Picture> boolean allPicturesFullyVisibleOnDesktop(PairManager<K, F> pairManager1,
                                                                                                  PairManager<K, F> pairManager2,
                                                                                                  Desktop desktop) throws GraphicException {
        //проверяющий, верно ли это для двух разных PairManager.
        return pairManager1.getFirstPicture().isFullyVisibleOnDesktop(desktop)
                && pairManager1.getSecondPicture().isFullyVisibleOnDesktop(desktop)
                && pairManager2.getFirstPicture().isFullyVisibleOnDesktop(desktop)
                && pairManager2.getSecondPicture().isFullyVisibleOnDesktop(desktop);
    }

}
