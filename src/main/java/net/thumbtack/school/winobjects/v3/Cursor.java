package net.thumbtack.school.winobjects.v3;

import net.thumbtack.school.iface.v3.Movable;
import net.thumbtack.school.pictures.v3.Point;

public class Cursor implements Movable {

    private int x;
    private int y;
    private CursorForm cursorForm;

    public Cursor(int x, int y, CursorForm cursorForm) {
        //Создает курсор указанной формы. Мы пока не будем конкретизировать понятие вида курсора, а просто будем
        // считать, что имеются различные формы курсоров, каждая форма имеет свой номер, нумерация произвольная.
        // Курсор помещается в точку (x,y).
        this.x = x;
        this.y = y;
        this.cursorForm = cursorForm;
    }

    public Cursor(Point point, CursorForm cursorForm){
    //Создает курсор указанной формы. Курсор помещается в точку point.
        this(point.getX(), point.getY(), cursorForm);
    }

    public CursorForm getCursorForm() {
        //Возвращает форму курсора.
        return cursorForm;
    }

    public void setCursorForm(CursorForm cursorForm) {
        this.cursorForm = cursorForm;
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    @Override
    public void moveTo(int x, int y) {
        //Перемещает курсор в точку (x,y).
        setX(x);
        setY(y);
    }

    @Override
    public void moveTo(Point point) {
        //Перемещает курсор в точку point.
        setX(point.getX());
        setY(point.getY());
    }

    @Override
    public void moveRel(int dx, int dy) {
        //Перемещает курсор на (dx,dy).
        setX(x+dx);
        setY(y+dy);
    }
}
