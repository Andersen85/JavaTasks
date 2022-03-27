package net.thumbtack.school.ttschool;

import java.util.LinkedList;
import java.util.Queue;

public class TraineeQueue {
    private Queue<Trainee> queue;

    public TraineeQueue(){
        queue = new LinkedList<>();
    }

    public void addTrainee(Trainee trainee) {
        //Добавляет студента в очередь.
        queue.offer(trainee);
    }

    public Trainee removeTrainee () throws TrainingException {
        //Удаляет студента из очереди.Метод возвращает удаленного Trainee.Если в очереди никого нет, выбрасывает
        //TrainingException с TrainingErrorCode.EMPTY_TRAINEE_QUEUE.
        if(queue.isEmpty()) throw new TrainingException(TrainingErrorCode.EMPTY_TRAINEE_QUEUE);
        return queue.poll();
    }

    public boolean isEmpty() {
        //Возвращает true, если очередь пуста, иначе false
        return queue.isEmpty();
    }
}
