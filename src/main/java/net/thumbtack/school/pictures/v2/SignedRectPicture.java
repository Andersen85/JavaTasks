package net.thumbtack.school.pictures.v2;

import net.thumbtack.school.pictures.v2.iface.Signed;
import net.thumbtack.school.winobjects.v2.Desktop;

public class SignedRectPicture extends RectPicture implements Signed {


    private String signature;

    public SignedRectPicture(Point topLeft, Point bottomRight, int format, String signature) {
        //Создает SignedRectPicture по координатам углов - левого верхнего и правого нижнего, формату и подписи.
        //Обращаем внимание на то, что обе точки входят в картинку, так что если создать картинку с topLeft.
        //equals(bottomRight), то будет создана картинка ширины и высоты 1.
        super(topLeft,bottomRight,format);
        this.signature = signature;
    }

    public SignedRectPicture(int xLeft, int yTop, int width, int height, int format, String signature) {
        //Создает SignedRectPicture по координатам левого верхнего угла, ширине, высоте, формату и подписи.
        this(new Point(xLeft,yTop), new Point(xLeft+width-1,yTop+height-1), format, signature);
    }

    public SignedRectPicture(Point topLeft, Point bottomRight, String signature) {
        //Создает SignedRectPicture формата 1 по координатам углов - левого верхнего и правого нижнего и подписи.
        this(topLeft,bottomRight,1,signature);
    }

    public SignedRectPicture(int xLeft, int yTop, int width, int height, String signature) {
        //Создает SignedRectPicture формата 1 по координатам левого верхнего угла, ширине и высоте и подписи.
        this( xLeft, yTop, width, height, 1, signature);
    }

    @Override
    public String getSignature() {
        return signature;
    }

    @Override
    public void setSignature(String signature) {
        this.signature = signature;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SignedRectPicture that = (SignedRectPicture) o;

        return getSignature() != null ? getSignature().equals(that.getSignature()) : that.getSignature() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getSignature() != null ? getSignature().hashCode() : 0);
        return result;
    }
}
