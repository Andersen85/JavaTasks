package net.thumbtack.school.introduction;

public class FirstSteps {
    public int sum(int x, int y){
        return x+y;
    }

    public int mul(int x,int y){
        return x*y;
    }

    public int div(int x, int y){
        // REVU это не требовалось, но если уж сделади - почему NullPointerException ?
        // на это есть
        // https://docs.oracle.com/javase/7/docs/api/java/lang/ArithmeticException.html
        // здесь и далее
        if(y==0) throw new NullPointerException("Y is null!");
        return x/y;
    }

    public int mod(int x, int y){
        return x%y;
    }

    public boolean isEqual(int x,int y){
        // return x == y;
        // далее аналогично
        if(x==y) return true;
        return false;
    }

    public boolean isGreater(int x,int y){
        return x>y;
    }

    public boolean isInsideRect(int xLeft, int yTop, int xRight, int yBottom, int x, int y){
        if(xLeft>xRight||yTop>yBottom) throw new IllegalArgumentException("Such a rectangle does not exist!");
        return x >= xLeft && x <= xRight && y >= yTop && y <= yBottom;
    }

    public int sum(int[] array){
        if(array.length==0) return 0;
        int sum=0;
        for(int intem:array){
            sum+=intem;
        }
        return sum;
    }

    public int mul(int[] array){
        if(array.length==0) return 0;
        int mult=1;
        for(int item:array){
            mult*=item;
        }
        return mult;
    }

    public int min(int[] array){
        int min=Integer.MAX_VALUE;
        if(array.length==0) return min;

        for(int item:array){
            if(item<min) min=item;
        }
        return min;
    }

    public int max(int[] array){
        int max=Integer.MIN_VALUE;
        if(array.length==0) return max;
        for(int item:array){
            if(item>max) max=item;
        }
        return max;
    }

    public double average(int[] array) {
        if (array.length == 0) return 0.;
        double sum = 0;
        for (int number : array) {
            sum += number;
        }
        return sum / array.length;
    }

    public boolean isSortedDescendant(int[] array){
        if(array.length==1||array.length==0){
            return true; }
        for(int i=0;i<array.length-1;i++){
            if(array[i]<=array[i+1]) {
                return false;
            }
        }
        return true;
    }



    public void cube(int[] array){
        for(int i=0;i<array.length;i++){
            array[i] *= array[i]*array[i];
            // REVU *=

        }
    }

    public boolean find(int[] array,int value){
        for(int item:array){
            if(item==value) return true;
        }
        return false;
    }

    public void reverse(int[] array){
        for (int i = 0; i < array.length / 2; i++) {
            int temp = array[i];
            array[i] = array[array.length - 1 - i];
            array[array.length - 1 - i] = temp;
        }

    }

    public boolean isPalindrome(int[] array){
        for (int i = 0; i < array.length / 2; i++) {
            if(array[i] != array[array.length - 1 - i]) return false;
        }
        return true;
    }

    public int sum(int[][] matrix) {
        int summ = 0;
        for (int[] array : matrix) {
            summ += sum(array);
        }
        return summ;
    }

    public int max(int[][] matrix) {
        int max = Integer.MIN_VALUE;
        if (matrix.length == 0) return max;
        for (int[] array : matrix) {
            if (max(array) > max) max = max(array);
            }
        return max;
    }




    public int diagonalMax(int[][] matrix){
        int max=Integer.MIN_VALUE;
        if(matrix.length==0||matrix.length==1 && matrix[0].length==0){
            return max;
        }
        if(matrix.length==0) return 0;
        for(int i=0;i<matrix.length;i++){
            if (matrix[i][i] > max) {
                max = matrix[i][i];
            }
        }
        return max;
    }

    public boolean isSortedDescendant(int[][] matrix) {
        for (int[] array:matrix) {
            if(!isSortedDescendant(array)) return false;
        }
        return true;
    }
}
