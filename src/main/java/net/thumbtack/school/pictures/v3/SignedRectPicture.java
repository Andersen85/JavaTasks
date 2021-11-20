package net.thumbtack.school.pictures.v3;

import net.thumbtack.school.exceptions.v3.GraphicErrorCode;
import net.thumbtack.school.exceptions.v3.GraphicException;
import net.thumbtack.school.iface.v3.Signed;

public class SignedRectPicture extends RectPicture implements Signed {


    private String signature;

    public SignedRectPicture(Point topLeft, Point bottomRight, PictureFormat format, String signature) throws GraphicException {
        //Создает SignedRectPicture по координатам углов - левого верхнего и правого нижнего, формату и подписи.
        //Обращаем внимание на то, что обе точки входят в картинку, так что если создать картинку с topLeft.
        //equals(bottomRight), то будет создана картинка ширины и высоты 1.

        super(topLeft,bottomRight,format);
        if(signature == null) {
            throw new GraphicException(GraphicErrorCode.NULL_SIGNATURE);
        }
        this.signature = signature;
    }

    public SignedRectPicture(Point topLeft, Point bottomRight, String format, String signature) throws GraphicException {
        this(topLeft,bottomRight,PictureFormat.fromString(format),signature);

    }

    public SignedRectPicture(int xLeft, int yTop, int width, int height, PictureFormat format, String signature) throws GraphicException {
        //Создает SignedRectPicture по координатам левого верхнего угла, ширине, высоте, формату и подписи.
        this(new Point(xLeft,yTop), new Point(xLeft+width-1,yTop+height-1), format, signature);
    }

    public SignedRectPicture(int xLeft, int yTop, int width, int height, String format, String signature) throws GraphicException {
        this(xLeft,yTop,width,height,PictureFormat.fromString(format),signature);
    }

    public SignedRectPicture(Point topLeft, Point bottomRight, String signature) throws GraphicException {
        //Создает SignedRectPicture формата GIF по координатам углов - левого верхнего и правого нижнего и подписи.
        this(topLeft,bottomRight,PictureFormat.GIF,signature);
    }

    public SignedRectPicture(int xLeft, int yTop, int width, int height, String signature) throws GraphicException {
        //Создает SignedRectPicture формата GIF по координатам левого верхнего угла, ширине и высоте и подписи.
        this( xLeft, yTop, width, height, PictureFormat.GIF, signature);
    }

    @Override
    public String getSignature() {
        return signature;
    }

    @Override
    public void setSignature(String signature) throws GraphicException {
        if(signature == null){throw new GraphicException(GraphicErrorCode.NULL_SIGNATURE);}
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
