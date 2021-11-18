package net.thumbtack.school.pictures.v2.iface;

// REVU в пакет net.thumbtack.school.pictures.v2.iface его
public interface Signed {
    String getSignature();
    //Возвращает подпись

    void setSignature(String signature);
    //Устанавливает новую подпись

}
