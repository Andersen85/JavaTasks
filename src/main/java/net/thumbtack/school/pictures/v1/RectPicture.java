package net.thumbtack.school.pictures.v1;

import java.util.Objects;

public class RectPicture {
    private Point topLeft;
    private Point bottomRight;
    private int format;

    public RectPicture(Point topLeft, Point bottomRight, int format){
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
        this.format = format;
    }

    public RectPicture(int xLeft, int yTop, int width, int height, int format){
        this(new Point(xLeft,yTop), new Point(xLeft+width-1, yTop+height-1), format);
    }

    public RectPicture(Point topLeft, Point bottomRight){
        this(topLeft, bottomRight, 1);
    }

    public RectPicture(int xLeft, int yTop, int width, int height){
        this(new Point(xLeft,yTop), new Point(xLeft+width-1,yTop+height-1));
    }

    public Point getTopLeft(){
        return new Point(topLeft.getX(),topLeft.getY());
    }

    public Point getBottomRight(){
        return new Point(bottomRight.getX(), bottomRight.getY());
    }

    public int getFormat() {
        return format;
    }

    public void setTopLeft(Point topLeft) {
        this.topLeft = topLeft;
    }

    public void setBottomRight(Point bottomRight) {
        this.bottomRight = bottomRight;
    }

    public int getWidth(){
        return Math.abs(getBottomRight().getX()-getTopLeft().getX()+1);
    }

    public int getHeight(){
        return Math.abs(getBottomRight().getY()-getTopLeft().getY()+1);
    }

    public void moveTo(int x, int y){
        int w = getWidth();
        int h = getHeight();
        setTopLeft(new Point(x,y));
        setBottomRight(new Point(x+w-1,y+h-1));
    }

    public void moveTo(Point point){
        moveTo(point.getX(),point.getY());
    }

    public void moveRel(int dx, int dy){
        topLeft.setX(topLeft.getX()+dx);
        bottomRight.setX(bottomRight.getX()+dx);
        topLeft.setY(topLeft.getY()+dy);
        bottomRight.setY(bottomRight.getY()+dy);
    }

    public void resize(double ratio){
        double newWidth = getWidth()*ratio;
        double newHeight = getHeight()*ratio;
        if(Double.compare(newWidth,1)<0) newWidth = 1;
        if(Double.compare(newHeight,1)<0) newHeight = 1;
        bottomRight = new Point(getTopLeft().getX()+(int)newWidth-1,
                (getTopLeft().getY()+(int)newHeight-1));
    }

    public boolean isInside(int x, int y){
        return (x >= getTopLeft().getX() && y >= getTopLeft().getY() &&
                x <= getBottomRight().getY() && y <= getBottomRight().getY());    }

    public boolean isInside(Point point){
        return isInside(point.getX(),point.getY());
    }


    public boolean isIntersects(RectPicture rectPicture){  // Закончить
        int count=0;
        for(int i=0;i<rectPicture.getWidth();i++){
            for (int j=0;j<rectPicture.getHeight();j++){
                if(isInside(rectPicture.getTopLeft().getX()+i,rectPicture.getTopLeft().getY() +j)) count++;
            }
        }
        if (count==0) return false;
        return true;
    }


    public boolean isInside(RectPicture rectPicture){
        return rectPicture.getTopLeft().getX()>=topLeft.getX() && rectPicture.getBottomRight().getX()<= bottomRight.getX() &&
                rectPicture.getTopLeft().getY()>=topLeft.getY() && rectPicture.getBottomRight().getY()<= bottomRight.getY();
    }


    public boolean isFullyVisibleOnDesktop(Desktop desktop){
    return getTopLeft().isVisibleOnDesktop(desktop) && getBottomRight().isVisibleOnDesktop(desktop)  ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        RectPicture that = (RectPicture) o;
        return format == that.format && Objects.equals(topLeft, that.topLeft) && Objects.equals(bottomRight, that.bottomRight);
    }

    @Override
    public int hashCode() {
        return Objects.hash(topLeft, bottomRight, format);
    }


}
