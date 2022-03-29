package net.thumbtack.school.ttschool;

import java.util.*;

public class Group {
    private String name;
    private String room;
    // REVU trainees. Имена начинаются со строчной буквы
    private List<Trainee> trainees;

    public Group(String name, String classroom) throws TrainingException {
        // REVU вызовите сеттеры, не дублируйте код
        setName(name);
        setRoom(classroom);
        trainees = new ArrayList<>();
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
        return trainees;
    }

    public void addTrainee(Trainee trainee) {
        //Добавляет Trainee в группу.
        trainees.add(trainee);
    }

    public void removeTrainee(Trainee trainee) throws TrainingException {
        //Удаляет Trainee из группы. Если такого Trainee в группе нет, выбрасывает TrainingException
        // с TrainingErrorCode.TRAINEE_NOT_FOUND
        if (!trainees.contains(trainee)) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        trainees.remove(trainee);
    }

    public void removeTrainee(int index) throws TrainingException {
        //Удаляет Trainee с данным номером в списке из группы.Если номер не является допустимым, выбрасывает
        //TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        if (index < 0 || index > trainees.size() - 1) throw new
                TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        trainees.remove(index);
    }

    public Trainee getTraineeByFirstName(String firstName) throws TrainingException {
        //Возвращает первый найденный в списке группы Trainee, у которого имя равно firstName.Если такого Trainee в
        //группе нет, выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        for (Trainee trainee : trainees) {
            if (trainee.getFirstName().equals(firstName)) {
                return trainee;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public Trainee getTraineeByFullName(String fullName) throws TrainingException {
        //Возвращает первый найденный в списке группы Trainee, у которого полное имя равно fullName.Если такого Trainee в
        //группе нет, выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        for (Trainee trainee : trainees) {
            if (trainee.getFullName().equals(fullName)) {
                return trainee;
            }
        }
        throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void sortTraineeListByFirstNameAscendant() {
        // REVU Collections.sort
        //Сортирует список Trainee группы, упорядочивая его по возрастанию имени Trainee.

        Collections.sort(trainees);

    }

    public void sortTraineeListByRatingDescendant() {
        // REVU Collections.sort
        //Сортирует список Trainee группы, упорядочивая его по убыванию оценки Trainee.
        Comparator<Trainee> comparator = new Comparator<>() {
            @Override
            public int compare(Trainee t1, Trainee t2) {
                return Integer.compare(t2.getRating(), t1.getRating());
            }
        };
        trainees.sort(comparator);
    }

    public void reverseTraineeList() {
        //Переворачивает список Trainee группы, то есть последний элемент списка становится начальным, предпоследний
        //-следующим за начальным и т.д..
        Collections.reverse(trainees);
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
        // REVU а без нахождения maxRating сможете ?
        if(trainees.isEmpty()) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        sortTraineeListByRatingDescendant();
        List<Trainee> list = new ArrayList<>();
        int maxRating = trainees.get(0).getRating();
        int i = 0;
        while (maxRating == trainees.get(i).getRating()){
            list.add(trainees.get(i));
            i++;
        }
        return list;
    }

    public boolean hasDuplicates() {
        // Вы на ровном месте устроили фактически двойной цикл, так как indexOf - это проход
        // REVU проще.
        // Кто у нас дубликаты не любит ?
        //Проверяет, есть ли в группе хотя бы одна пара Trainee, для которых совпадают имя, фамилия и оценка.
        Set<Trainee> set = new HashSet<>(trainees);
        return trainees.size() != set.size();
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
