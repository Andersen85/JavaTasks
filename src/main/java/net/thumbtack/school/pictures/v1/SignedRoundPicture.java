package net.thumbtack.school.pictures.v1;

public class SignedRoundPicture extends RoundPicture {

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

    public Point getCenter() {
        //Возвращает центр SignedRoundPicture.
       return super.getCenter();
    }

    public int getRadius(){
        //Возвращает радиус SignedRoundPicture.
        return super.getRadius();
    }

    public int getFormat() {
        //Возвращает формат картинки
        return super.getFormat();
    }

    public String getSignature() {
        //Возвращает подпись
        return signature;
    }

    public void setSignature(String signature) {
        //Устанавливает новую подпись
        this.signature = signature;
    }


    public void moveTo(int x, int y) {
        //Передвигает SignedRoundPicture так, чтобы центр его оказался в точке(x, y).
        super.moveTo(x,y);
    }

    public void moveTo(Point point) {
        //Передвигает SignedRoundPicture так, чтобы центр его оказался в точке point.
        super.moveTo(point);
    }

    public void setCenter(int x, int y) {
        //Устанавливает центр SignedRoundPicture.
        super.setCenter(new Point(x,y));
    }

    public void setRadius(int radius) {
        //Устанавливает радиус SignedRoundPicture.
        super.setRadius(radius);
    }

    public void setActive(int format) {
        //Устанавливает состояние активности SignedRoundPicture.
        super.setFormat(format);
    }

    public void moveRel(int dx, int dy) {
        //Передвигает SignedRoundPicture на(dx, dy).
        super.moveRel(dx, dy);
    }

    public void resize(double ratio) {
        //Изменяет радиус SignedRoundPicture в ratio раз, не изменяя центра.Дробная часть вычисленного таким
        //образом радиуса отбрасывается.Если вычисленный радиус окажется меньше 1, то он принимается равным 1.
        super.resize(ratio);
    }

    public boolean isInside(int x, int y) {
        //Определяет, лежит ли точка (x, y)внутри SignedRoundPicture.Если точка лежит на окружности, считается, что
        //она лежит внутри.В этом методе мы пренебрегаем пиксельной структурой изображения и рассматриваем
        //SignedRoundPicture как математический круг.
        return super.isInside(x,y);
    }

    public boolean isInside(Point point) {
        //Определяет, лежит ли точка point внутри SignedRoundPicture.Если точка лежит на окружности, считается, что
        //она лежит внутри.В этом методе мы пренебрегаем пиксельной структурой изображения и рассматриваем
        //SignedRoundPicture как математический круг.
        return super.isInside(point);
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        //Определяет, верно ли, что вся SignedRoundPicture находится в пределах Desktop.
        return super.isFullyVisibleOnDesktop(desktop);
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
