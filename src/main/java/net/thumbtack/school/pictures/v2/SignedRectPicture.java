package net.thumbtack.school.pictures.v2;

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

    public Point getTopLeft() {
        return super.getTopLeft();
    }

    public Point getBottomRight() {
        return super.getBottomRight();
    }

    public int getFormat() {
        return super.getFormat();
    }

    @Override
    public String getSignature() {
        return signature;
    }

    public void setTopLeft(Point topLeft) {
        super.setTopLeft(topLeft);
    }

    public void setBottomRight(Point bottomRight) {
        super.setBottomRight(bottomRight);
    }


    @Override
    public void setSignature(String signature) {
        this.signature = signature;
    }

    // REVU Если метод у потомка только вызывает тот же метод родителя и ничего больше не делает, то его переопределять не надо. Удалите все такие методы
    // во всех классах
    public int getWidth() {
        //Возвращает ширину SignedRectPicture.
        return super.getWidth();
    }

    public int getHeight() {
        //Возвращает высоту SignedRectPicture.*/
    return super.getHeight();
    }

    @Override
    public void moveTo(int x, int y) {
        //Передвигает SignedRectPicture так, чтобы левый верхний угол его оказался в точке(x, y).
        super.moveTo(x,y);
    }


    @Override
    public void moveRel(int dx, int dy) {
        //Передвигает SignedRectPicture на(dx, dy).
        super.moveRel(dx,dy);
    }

    public void resize(double ratio) {
        //Изменяет ширину и длину SignedRectPicture в ratio раз при сохранении координат левой верхней точки.Дробная часть
        //вычисленной длины или ширины отбрасывается.Если при таком изменении длина или ширина какой -то из сторон
        //окажется меньше 1, то она принимается равной 1.
        super.resize(ratio);
    }
    public boolean isInside(int x, int y) {
        //Определяет, лежит ли точка (x, y)внутри SignedRectPicture.Если точка лежит на стороне, считается, что она лежит
        //внутри.
        return super.isInside(x,y);
    }

    public boolean isInside(Point point) {
       // Определяет, лежит ли точка point внутри SignedRectPicture.Если точка лежит на стороне, считается, что она лежит
        //внутри.
        return super.isInside(point);
    }

    public boolean isIntersects(SignedRectPicture SignedRectPicture) {
        //Определяет, пересекается ли SignedRectPicture с другим SignedRectPicture.Считается, что
        //картинки пересекаются, если у них есть хоть одна общая точка.
        return super.isIntersects(SignedRectPicture);
    }

    public boolean isInside(SignedRectPicture SignedRectPicture) {
        //Определяет, лежит ли SignedRectPicture целиком внутри текущего SignedRectPicture.
        return super.isInside(SignedRectPicture);
    }

    public boolean isFullyVisibleOnDesktop(Desktop desktop) {
        //Определяет, верно ли, что вся SignedRectPicture находится в пределах Desktop.
     return super.isFullyVisibleOnDesktop(desktop);
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
