package net.thumbtack.school.pictures.v3;

import net.thumbtack.school.exceptions.v3.GraphicErrorCode;
import net.thumbtack.school.exceptions.v3.GraphicException;
import net.thumbtack.school.iface.v3.Signed;

public class SignedRoundPicture extends RoundPicture implements Signed {

    private String signature;

    public SignedRoundPicture(Point center, int radius, PictureFormat format, String signature) throws GraphicException {
        //Создает SignedRoundPicture по координатам центра, значению радиуса, формату и подписи.
        super(center,radius, format);
        // REVU сначала проверка, потом присваивание
        if(signature == null) {
            throw new GraphicException(GraphicErrorCode.NULL_SIGNATURE);
        }
        this.signature = signature;
    }

    public SignedRoundPicture(Point center, int radius, String format, String signature) throws GraphicException {
        this(center, radius, PictureFormat.fromString(format), signature);
    }

    public SignedRoundPicture(int xCenter, int yCenter, int radius, PictureFormat format, String signature) throws GraphicException {
        //Создает SignedRoundPicture по координатам центра, значению радиуса, формату  и подписи.
        this(new Point(xCenter, yCenter), radius, format, signature);
    }

    public SignedRoundPicture(int xCenter, int yCenter, int radius, String format, String signature) throws GraphicException {
        this(xCenter, yCenter, radius, PictureFormat.fromString(format), signature);
    }

    public SignedRoundPicture(Point center, int radius, String signature) throws GraphicException {
        //Создает SignedRoundPicture формата GIF по координатам центра, значению радиуса и подписи.
        this(center, radius, PictureFormat.GIF, signature);
    }

    public SignedRoundPicture(int xCenter, int yCenter, int radius, String signature) throws GraphicException {
        //Создает SignedRoundPicture формата GIF по координатам центра, значению радиуса и подписи.
        this( xCenter,yCenter, radius, PictureFormat.GIF, signature);
    }

    @Override
    public String getSignature()  {
        //Возвращает подпись

        return signature;
    }

    @Override
    public void setSignature(String signature) throws GraphicException {
        //Устанавливает новую подпись
        if(signature == null){throw new GraphicException(GraphicErrorCode.NULL_SIGNATURE);}
        this.signature = signature;
    }


    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        if (!super.equals(o)) return false;

        SignedRoundPicture that = (SignedRoundPicture) o;

        return getSignature() != null ? getSignature().equals(that.getSignature()) : that.getSignature() == null;
    }

    @Override
    public int hashCode() {
        int result = super.hashCode();
        result = 31 * result + (getSignature() != null ? getSignature().hashCode() : 0);
        return result;
    }
}
