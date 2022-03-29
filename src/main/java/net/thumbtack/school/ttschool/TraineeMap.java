package net.thumbtack.school.ttschool;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

public class TraineeMap {
    private Map<Trainee, String> map;

    public TraineeMap() {
        map = new HashMap<>();
    }

    public void addTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        //Добавляет пару Trainee - String в Map.Если Map уже содержит информацию об этом Trainee, выбрасывает
        //TrainingException с TrainingErrorCode.DUPLICATE_TRAINEE.
        // REVU не нужно containsKey, putIfAbsent сама скажет
        if(map.putIfAbsent(trainee,institute) != null) throw new TrainingException(TrainingErrorCode.DUPLICATE_TRAINEE);
    }

    public void replaceTraineeInfo(Trainee trainee, String institute) throws TrainingException {
        //Если в Map уже есть информация о данном Trainee, заменяет пару Trainee -String в Map на новую пару, иначе
        //выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND.
        // REVU не нужно containsKey, replace сама скажет
        if(map.put(trainee, institute) == null) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public void removeTraineeInfo(Trainee trainee) throws TrainingException {
        //Удаляет информацию о Trainee из Map.Если Map не содержит информации о таком Trainee, выбрасывает
        //TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND.
        // REVU не нужно containsKey, remove сама скажет
        if(map.remove(trainee) == null) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
    }

    public int getTraineesCount() {
        //Возвращает количество элементов в Map, иными словами, количество студентов.
        return map.size();
    }

    public String getInstituteByTrainee(Trainee trainee) throws TrainingException {
        //Возвращает институт, в котором учится данный Trainee.Если Map не содержит информации о
        //таком Trainee, выбрасывает TrainingException с TrainingErrorCode.TRAINEE_NOT_FOUND
        // REVU не нужно containsKey, get сама скажет
        if(map.get(trainee) == null) throw new TrainingException(TrainingErrorCode.TRAINEE_NOT_FOUND);
        return map.get(trainee);
    }

    public Set<Trainee> getAllTrainees() {
        //Возвращает Set из всех имеющихся в Map Trainee.
        return map.keySet();
    }

    public Set<String> getAllInstitutes() {
        //Возвращает Set из всех институтов.
        return new HashSet<>(map.values());
    }

    public boolean isAnyFromInstitute(String institute) {
        //Возвращает true, если хоть один студент учится в этом institute, иначе false.
        return map.containsValue(institute);
    }
}
