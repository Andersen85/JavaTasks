package net.thumbtack.school.matrix;

import java.util.*;

public class MatrixNonSimilarRows {

    private int[][] matrix;

    public MatrixNonSimilarRows(int[][] matrix) {
        //Создает MatrixNonSimilarRows по заданной матрице.
        this.matrix = matrix;
    }

    public List<int[]> getNonSimilarRows() {
        //Возвращает список непохожих строк.
        Map<Set<Integer>, int[]> map = new HashMap<>();
        for (int[] array : matrix) {
            Set<Integer> set = new HashSet<>();
            for (int num : array) {
                set.add(num);
            }
            map.put(set, array);
        }
        return new ArrayList<>(map.values());
    }

}
