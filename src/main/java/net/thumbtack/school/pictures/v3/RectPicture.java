package net.thumbtack.school.pictures.v3;

import net.thumbtack.school.exceptions.v3.GraphicException;
import net.thumbtack.school.winobjects.v3.Desktop;


public class RectPicture extends Picture {
    private Point topLeft;
    private Point bottomRight;

    public RectPicture(Point topLeft, Point bottomRight, PictureFormat format) throws GraphicException {
        super(format);
        this.topLeft = topLeft;
        this.bottomRight = bottomRight;
    }

    public RectPicture(Point topLeft, Point bottomRight, String format) throws GraphicException {
        this(topLeft,bottomRight,PictureFormat.fromString(format));
    }

    public RectPicture(int xLeft, int yTop, int width, int height, PictureFormat format) throws GraphicException {
        this(new Point(xLeft,yTop), new Point(xLeft+width-1, yTop+height-1), format);
    }

    public RectPicture(int xLeft, int yTop, int width, int height, String format) throws GraphicException {
        this(xLeft, yTop, width, height, PictureFormat.fromString(format));
    }

    public RectPicture(Point topLeft, Point bottomRight) throws GraphicException {
        this(topLeft, bottomRight, PictureFormat.GIF);
    }

    public RectPicture(int xLeft, int yTop, int width, int height) throws GraphicException {
        this(new Point(xLeft,yTop), new Point(xLeft+width-1,yTop+height-1));
    }

    public Point getTopLeft(){
        return new Point(topLeft.getX(),topLeft.getY());
    }

    public Point getBottomRight(){
        return new Point(bottomRight.getX(), bottomRight.getY());
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

    @Override
    public void moveTo(int x, int y){
        int w = getWidth();
        int h = getHeight();
        setTopLeft(new Point(x,y));
        setBottomRight(new Point(x+w-1,y+h-1));
    }


    @Override
    public void moveRel(int dx, int dy){
        topLeft.setX(topLeft.getX()+dx);
        bottomRight.setX(bottomRight.getX()+dx);
        topLeft.setY(topLeft.getY()+dy);
        bottomRight.setY(bottomRight.getY()+dy);
    }

    @Override
    public void resize(double ratio){
        double newWidth = getWidth()*ratio;
        double newHeight = getHeight()*ratio;
        if(Double.compare(newWidth,1)<0) newWidth = 1;
        if(Double.compare(newHeight,1)<0) newHeight = 1;
        bottomRight = new Point(getTopLeft().getX()+(int)newWidth-1,
                (getTopLeft().getY()+(int)newHeight-1));
    }

    @Override
    public boolean isInside(int x, int y){
        return (x >= getTopLeft().getX() && y >= getTopLeft().getY() &&
                x <= getBottomRight().getY() && y <= getBottomRight().getY());    }


    public boolean isIntersects(RectPicture rectPicture){
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


    @Override
    public boolean isFullyVisibleOnDesktop(Desktop desktop){
    return getTopLeft().isVisibleOnDesktop(desktop) && getBottomRight().isVisibleOnDesktop( desktop)  ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RectPicture that = (RectPicture) o;

        if (getTopLeft() != null ? !getTopLeft().equals(that.getTopLeft()) : that.getTopLeft() != null) return false;
        return getBottomRight() != null ? getBottomRight().equals(that.getBottomRight()) : that.getBottomRight() == null;
    }

    @Override
    public int hashCode() {
        int result = getTopLeft() != null ? getTopLeft().hashCode() : 0;
        result = 31 * result + (getBottomRight() != null ? getBottomRight().hashCode() : 0);
        return result;
    }

}
