package net.thumbtack.school.base;
import java.math.*;

public class NumberOperations {
    public static Integer find(int[] array, int value) {
        //Ищет в массиве array первый элемент, значение которого равно value. Если такое значение найдено,
        // возвращает его позицию в массиве array, в противном случае возвращает null.
        for(int i=0;i<array.length;i++){
            if(array[i]==value) {
                return i;
            }
        }
        return null;
    }

    public static Integer find(double[] array, double value, double eps){
        //Ищет в массиве array первый элемент, значение которого по модулю не отличается от value более чем на eps.
        // Если такое значение найдено, возвращает его позицию в массиве array, в противном случае возвращает null.
        for (int i=0;i<array.length;i++){
            if(Math.abs(array[i]-value)<=eps) return i;
        }
        return null;
    }

    public static Double calculateDensity(double weight, double volume, double min, double max) {
        //Вычисляет плотность вещества по формуле weight / volume. Если получившееся значение не превышает max
        // и не меньше min, возвращает полученное значение, в противном случае возвращает null.
        Double density = weight / volume;
        if (density >= min && density <= max) return density;
        return null;
    }

    public static Integer find(BigInteger[] array, BigInteger value) {
        //Ищет в массиве array первый элемент, значение которого равно value. Если такое значение найдено,
        // возвращает его позицию в массиве array, в противном случае возвращает null.
        for(int i=0;i<array.length;i++){
            if(array[i].compareTo(value)==0) {
                return i;
            }
        }
        return null;

    }


    public static BigDecimal calculateDensity(BigDecimal weight, BigDecimal volume, BigDecimal min, BigDecimal max) {
        //Вычисляет плотность вещества по формуле weight / volume. Если получившееся значение не превышает max
        // и не меньше min, возвращает полученное значение, в противном случае возвращает null.
        BigDecimal density = weight.divide(volume);
        if ( density.compareTo(min)>=0 && density.compareTo(max) <= 0) return density;
        return null;

    }

}
