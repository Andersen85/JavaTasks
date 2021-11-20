package net.thumbtack.school.iface.v3;

import net.thumbtack.school.exceptions.v3.GraphicException;

// REVU в пакет net.thumbtack.school.pictures.v2.iface его
public interface Signed {
    String getSignature() throws GraphicException;
    //Возвращает подпись

    void setSignature(String signature) throws GraphicException;
    //Устанавливает новую подпись

}
