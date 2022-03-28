package net.thumbtack.school.ttschool;

import java.util.HashSet;
import java.util.Iterator;
import java.util.Set;

public class School {
    private String name;
    private int year;
    private Set<Group> groups;

    public School(String name, int year) throws TrainingException {
        // REVU вызовите сеттеры, не дублируйте код
        if(name == null || name.equals("")) throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_NAME);
        // где-то в Задании есть требование насчет 2020 - 2022 ?
        // Ваша программа же в следующем году работать перестанет :-)
        if(year > 2022 || year < 2000) throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_YEAR);
        this.name = name;
        this.year = year;
        groups = new HashSet<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String name) throws TrainingException {
        if(name == null || name.equals("")) throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_NAME);
        this.name = name;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) throws TrainingException {
        if(year > 2022 || year < 2000) throw new TrainingException(TrainingErrorCode.SCHOOL_WRONG_YEAR);
        this.year = year;
    }

    public Set<Group> getGroups() {
        return groups;
    }

    public void  addGroup(Group group) throws TrainingException {
        //Добавляет Group в школу.Если группа с таким именем уже есть, выбрасывает TrainingException с
        //TrainingErrorCode.DUPLICATE_GROUP_NAME
        // REVU Линейный проход для добавления - это плохо, медленно. Подумайте, как сделать, чтобы при формировании Set использовалось только name. Подсказка - кроме HashSet, есть и другой
        for (Group g : groups) {
            if (g.getName().equals(group.getName())) throw new TrainingException(TrainingErrorCode.DUPLICATE_GROUP_NAME);
        }
        groups.add(group);
    }

    public void  removeGroup(Group group) throws TrainingException {
        //Удаляет Group из школы.Если такой Group в школе нет, выбрасывает TrainingException с
        //TrainingErrorCode.GROUP_NOT_FOUND
        // REVU та же проблема, то же лечение
        if(!groups.contains(group)) throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
        groups.remove(group);
    }

    public void  removeGroup(String name) throws TrainingException {
        //Удаляет Group с данным названием из школы.Если группа с таким названием не найдена, выбрасывает
        //TrainingException с TrainingErrorCode.GROUP_NOT_FOUND
        // REVU можно просто for(Group group : groups)
        Iterator<Group> iterator = groups.iterator();
        while (iterator.hasNext()){
            if(iterator.next().getName().equals(name)) {
                iterator.remove();
                return;
            }
        }
       //?
        throw new TrainingException(TrainingErrorCode.GROUP_NOT_FOUND);
    }

    public boolean  containsGroup(Group group) {
        //Определяет, есть ли в школе такая группа.
        return groups.contains(group);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        School school = (School) o;

        if (getYear() != school.getYear()) return false;
        if (getName() != null ? !getName().equals(school.getName()) : school.getName() != null) return false;
        return getGroups() != null ? getGroups().equals(school.getGroups()) : school.getGroups() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + getYear();
        result = 31 * result + (getGroups() != null ? getGroups().hashCode() : 0);
        return result;
    }
}
