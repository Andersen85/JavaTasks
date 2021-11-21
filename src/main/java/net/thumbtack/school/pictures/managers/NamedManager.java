package net.thumbtack.school.pictures.managers;

import net.thumbtack.school.exceptions.v3.GraphicException;
import net.thumbtack.school.pictures.v3.Picture;

public class NamedManager<T extends Picture> extends Manager{

    private String name;

    public NamedManager(T picture, String name) throws GraphicException {
        super(picture);
        this.name = name;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }


}
