package net.thumbtack.school.ttschool;

import java.util.*;

public class Group {
    private String name;
    private String room;
    private List<Trainee> Trainees;

    public Group(String name, String classroom) throws TrainingException {
        if (name == null || name.equals("")) throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        if (classroom == null || classroom.equals(""))
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        this.name = name;
        this.room = classroom;
        Trainees = new ArrayList<>();
    }

    public String getName() {
        return name;
    }

    public void setName(String groupName) throws TrainingException {
        if (groupName == null || groupName.equals(""))
            throw new TrainingException(TrainingErrorCode.GROUP_WRONG_NAME);
        this.name = groupName;
    }

    public String getRoom() {
        return room;
    }

    public void setRoom(String room) throws TrainingException {
        if (room == null || room.equals("")) throw new TrainingException(TrainingErrorCode.GROUP_WRONG_ROOM);
        this.room = room;
    }

    public List<Trainee> getTrainees() {
        return Trainees;
    }

    public void addTrainee(Trainee trainee) {
        //Добавляет Trainee в группу.
        Trainees.add(trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {
        //Удаляет Trainee из группы. Если такого Trainee в группе нет, выбрасывает TrainingException
        // с TrainingErrorCode.TRAINEE_NOT_FOUND
        if (!Trainees.contains(trainee)) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        Trainees.remove(trainee);
    }

    public void removeTrainee(int index) throws TrainingException {
        //Удаляет Trainee с данным номером в списке из группы.Если номер не является допустимым, выбрасывает
        //TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        if (index < 0 || index > Trainees.size() - 1) throw new
                TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        Trainees.remove(index);
    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        //Возвращает первый найденный в списке группы Trainee, у которого имя равно firstName.Если такого Trainee в
        //группе нет, выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        for (Trainee trainee : Trainees) {
            if (trainee.getFirstName().equals(firstName)) {
                return trainee;
            }
        }
        //?   + мб итератором будет проще
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Trainee getTraineeByFullName(String fullName) throws TrainingException {
        //Возвращает первый найденный в списке группы Trainee, у которого полное имя равно fullName.Если такого Trainee в
        //группе нет, выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        for (Trainee trainee : Trainees) {
            if (trainee.getFullName().equals(fullName)) {
                return trainee;
            }
        }
        //?
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void sortTraineeListByFirstNameAscendant() {
        //Сортирует список Trainee группы, упорядочивая его по возрастанию имени Trainee.
        SortedSet<Trainee> set = new TreeSet<Trainee>((p1,p2) -> p1.getFirstName().compareTo(p2.getFirstName()));
        set.addAll(Trainees);
        Trainees.clear();
        Trainees.addAll(set);
    }

    public void sortTraineeListByRatingDescendant() {
        //Сортирует список Trainee группы, упорядочивая его по убыванию оценки Trainee.
        SortedSet<Trainee> set = new TreeSet<Trainee>(new Comparator<Trainee>() {
            @Override
            public int compare(Trainee t1, Trainee t2) {
                if(t1.getRating() == t2.getRating()){
                    return 0;
                } else if(t1.getRating() > t2.getRating()){
                    return -1;
                } else return 1;
            }
        });
        set.addAll(Trainees);
        Trainees.clear();
        Trainees.addAll(set);
    }

    public void reverseTraineeList() {
        //Переворачивает список Trainee группы, то есть последний элемент списка становится начальным, предпоследний
        //-следующим за начальным и т.д..
        Collections.reverse(Trainees);
    }

    public void rotateTraineeList(int positions) {
        //Циклически сдвигает список Trainee группы на указанное число позиций.Для положительного значения positions
        //сдвигает вправо, для отрицательного - влево на модуль значения positions.
        Collections.rotate(getTrainees(), positions);
    }

    public List<Trainee> getTraineesWithMaxRating() throws TrainingException {
        //Возвращает список тех Trainee группы, которые имеют наивысшую оценку.Иными словами, если в группе есть Trainee
        //с оценкой 5, возвращает список получивших оценку 5, если же таких нет, но есть Trainee с оценкой 4, возвращает
        //список получивших оценку 4 и т.д.Для пустого списка выбрасывает TrainingException с
        //TrainingErrorCode.TRAINEE_NOT_FOUND.Желательно сделать этот метод без сортировки и в один проход по списку.
        int maxRating = 0;
        List<Trainee> list = new ArrayList<>();
        for (Trainee trainee : Trainees) {
            if (trainee.getRating() > maxRating) {
                maxRating = trainee.getRating();
            }
        }
        for (Trainee trainee : Trainees) {
            if (trainee.getRating() == maxRating) {
                list.add(trainee);
            }
        }
        if (list.isEmpty()) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        return list;
    }

    public boolean hasDuplicates() {
        //Проверяет, есть ли в группе хотя бы одна пара Trainee, для которых совпадают имя, фамилия и оценка.
        for (Trainee trainee : Trainees) {
            if (Trainees.indexOf(trainee) != Trainees.lastIndexOf(trainee))
                return true;
        }
        return false;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Group group = (Group) o;

        if (getName() != null ? !getName().equals(group.getName()) : group.getName() != null) return false;
        if (getRoom() != null ? !getRoom().equals(group.getRoom()) : group.getRoom() != null) return false;
        return getTrainees() != null ? getTrainees().equals(group.getTrainees()) : group.getTrainees() == null;
    }

    @Override
    public int hashCode() {
        int result = getName() != null ? getName().hashCode() : 0;
        result = 31 * result + (getRoom() != null ? getRoom().hashCode() : 0);
        result = 31 * result + (getTrainees() != null ? getTrainees().hashCode() : 0);
        return result;
    }
}
