package net.thumbtack.school.pictures.v2;

import net.thumbtack.school.pictures.v2.iface.Signed;
import net.thumbtack.school.winobjects.v2.Desktop;

public class SignedRoundPicture extends RoundPicture implements Signed {

    private String signature;

    public SignedRoundPicture(Point center, int radius, int format, String signature) {
        //Создает SignedRoundPicture по координатам центра, значению радиуса, формату и подписи.
        super(center,radius, format);
        this.signature = signature;
    }

    public SignedRoundPicture(int xCenter, int yCenter, int radius, int format, String signature){
        //Создает SignedRoundPicture по координатам центра, значению радиуса, формату  и подписи.
        this(new Point(xCenter, yCenter), radius, format, signature);
    }

    public SignedRoundPicture(Point center, int radius, String signature) {
        //Создает SignedRoundPicture формата 1 по координатам центра, значению радиуса и подписи.
        this(center, radius, 1, signature);
    }

    public SignedRoundPicture(int xCenter, int yCenter, int radius, String signature) {
        //Создает SignedRoundPicture формата 1 по координатам центра, значению радиуса и подписи.
        this( xCenter,yCenter, radius, 1, signature);
    }

    @Override
    public String getSignature() {
        //Возвращает подпись
        return signature;
    }

    @Override
    public void setSignature(String signature) {
        //Устанавливает новую подпись
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
