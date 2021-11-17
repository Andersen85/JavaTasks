package net.thumbtack.school.base;

import java.text.DecimalFormat;
import java.util.Locale;
import java.math.*;
public class StringOperations {

    public static int getSummaryLength(String[] strings) {
        // REVU int sum
        int output = 0;
        for (String str : strings) {
            output += str.length();
        }
        return output;
    }

    public static String getFirstAndLastLetterString(String string) {
        //Возвращает двухсимвольную строку, состоящую из начального и конечного символов заданной строки

        return String.valueOf(new char[]{string.charAt(0), string.charAt(string.length() - 1)});
    }

    public static boolean isSameCharAtPosition(String string1, String string2, int index) {
        //Возвращает true, если обе строки в позиции index содержат один и тот же символ, иначе false.
        return isEqual(Character.toString(string1.charAt(index)), Character.toString(string2.charAt(index)));
    }

    public static boolean isSameFirstCharPosition(String string1, String string2, char character) {
        //Возвращает true, если в обеих строках первый встреченный символ character находится в одной
        // и той же позиции. Просмотр строк ведется от начала.
        return string1.indexOf(character) == string2.indexOf(character);
    }

    public static boolean isSameLastCharPosition(String string1, String string2, char character) {
        //Возвращает true, если в обеих строках первый встреченный символ character находится в одной и той же позиции.
        // Просмотр строк ведется от конца.
        return string1.lastIndexOf(character) == string2.lastIndexOf(character);
    }

    public static boolean isSameFirstStringPosition(String string1, String string2, String str) {
        //Возвращает true, если в обеих строках первая встреченная подстрока str начинается в одной и той же позиции.
        // Просмотр строк ведется от начала.
        return string1.indexOf(str) == string2.indexOf(str);
    }

    public static boolean isSameLastStringPosition(String string1, String string2, String str) {
        //Возвращает true, если в обеих строках первая встреченная подстрока str начинается в одной и той же позиции.
        // Просмотр строк ведется от конца.
        return string1.lastIndexOf(str) == string2.lastIndexOf(str);
    }

    public static boolean isEqual(String string1, String string2) {
        //Возвращает true, если строки равны.
        return string1.equals(string2);
    }

    public static boolean isEqualIgnoreCase(String string1, String string2) {
        //Возвращает true, если строки равны без учета регистра (например, строки “abc” и “aBC” в этом смысле равны).

        return isEqual(string1.toLowerCase(Locale.ROOT),string2.toLowerCase(Locale.ROOT));
    }

    public static boolean isLess(String string1, String string2) {
        //Возвращает true, если строка string1 меньше строки string2.
        return string1.compareTo(string2) <0;
    }

    public static boolean isLessIgnoreCase(String string1, String string2) {
        //Возвращает true, если строка string1 меньше строки string2 без учета регистра (например, строка “abc” меньше
        // строки “ABCd” в этом смысле).
        string1 = string1.toLowerCase();
        string2 = string2.toLowerCase();
        return isLess(string1, string2);
    }

    public static String concat(String string1, String string2) {
        //Возвращает строку, полученную путем сцепления двух строк.
        return string1.concat(string2);
    }

    public static boolean isSamePrefix(String string1, String string2, String prefix) {
        //Возвращает true, если обе строки string1 и string2 начинаются с одной и той же подстроки prefix.
        return string1.startsWith(prefix) && string2.startsWith(prefix);
    }

    public static boolean isSameSuffix(String string1, String string2, String suffix) {
        //Возвращает true, если обе строки string1 и string2 заканчиваются одной и той же подстрокой suffix.
        return string1.endsWith(suffix) && string2.endsWith(suffix);
    }

    public static String getCommonPrefix(String string1, String string2) {
        //Возвращает самое длинное общее “начало” двух строк. Если у строк нет общего начала, возвращает пустую строку.
        int index = 0;
        for (int i = 0; i < getMinLength(string1,string2); i++) {
            if (string1.charAt(i) == string2.charAt(i)) {
                index++;
            }
            if(string1.charAt(i) != string2.charAt(i)) {
                break;
            }
        }
        if (index == 0) return "";
        return string1.substring(0, index);
    }

    public static String reverse(String string) {
        //Возвращает перевернутую строку.
        // REVU StringBuilder.reverse
        char[] arr = string.toCharArray();
        String resultString = "";
        for (int i = arr.length - 1; i >= 0; i--) {
            resultString += arr[i];
        }
        return resultString;
    }

    public static boolean isPalindrome(String string) {
        //Возвращает true, если строка является палиндромом, то есть читается слева направо так же, как и справа налево.
        if(string == "") return true;
        // REVU half = half = string.length()/2 всегда
        int half = 0;
        if(string.length()%2 == 1) { half = (string.length()+1)/2;}
        if(string.length()%2 != 1) { half = string.length()/2;}
        // REVU сделайте без reverse
        String rev = reverse(string.substring(half,string.length()));
        return isEqual(string.substring(0,half-1),rev);
    }

    public static boolean isPalindromeIgnoreCase(String string) {
        //Возвращает true, если строка является палиндромом, то есть читается слева направо так же,
        // как и справа налево, без учета регистра.
        string = string.toLowerCase();
        return isPalindrome(string);
    }

    public static String getLongestPalindromeIgnoreCase(String[] strings) {
        //Возвращает самый длинный палиндром (без учета регистра) из массива заданных строк.
        // Если в массиве нет палиндромов, возвращает пустую строку.
        String output = "";
        for (String str : strings) {
            String str1 = str;
            str = str.toLowerCase();
            if (isPalindrome(str) && str.length()>=output.length()) {
                output = str1;
            }
        }
        return output;
    }

    public static boolean hasSameSubstring(String string1, String string2, int index, int length) {
        //Возвращает true, если обе строки содержат один и тот же фрагмент длиной length, начиная с позиции index.

        if(string1.length()<length+index || string2.length()<length+index) return false;
        String str1 = string1.substring(index,length+index);
        String str2 = string2.substring(index,length+index);
        return str1.equals(str2);
        //return isEqual(string1.substring(index, index + length), string2.substring(index, index + length));
    }

    public static boolean isEqualAfterReplaceCharacters(String string1, char replaceInStr1, char replaceByInStr1,
                                                        String string2, char replaceInStr2, char replaceByInStr2) {
        //Возвращает true, если после замены в string1 всех вхождений replaceInStr1 на replaceByInStr1 и замены в string2
        // всех вхождений replaceInStr2 на replaceByInStr2 полученные строки равны.
        string1 = string1.replace(replaceInStr1, replaceByInStr1);
        string2 = string2.replace(replaceInStr2, replaceByInStr2);
        return isEqual(string1, string2);
    }

    public static boolean isEqualAfterReplaceStrings(String string1, String replaceInStr1, String replaceByInStr1,
                                                     String string2, String replaceInStr2, String replaceByInStr2) {
        //Возвращает true, если после замены в string1 всех вхождений строки replceInStr1 на replaceByInStr1 и замены в
        // string2 всех вхождений replceInStr2 на replaceByInStr2 полученные строки равны.
        string1 = string1.replaceAll(replaceInStr1, replaceByInStr1);
        string2 = string2.replaceAll(replaceInStr2, replaceByInStr2);
        return isEqual(string1, string2);
    }

    public static boolean isPalindromeAfterRemovingSpacesIgnoreCase(String string) {
        //Возвращает true, если строка после выбрасывания из нее всех пробелов является палиндромом, без учета регистра.
        string = string.replace(" ", "");
        string = string.toLowerCase();
        return isPalindrome(string);
    }

    public static boolean isEqualAfterTrimming(String string1, String string2) {
        //Возвращает true, если две строки равны, если не принимать во внимание все пробелы в начале и конце каждой строки.
        string1 = string1.trim();
        string2 = string2.trim();
        return isEqual(string1, string2);
    }

    public static String makeCsvStringFromInts(int[] array) {
        //Для заданного массива целых чисел создает текстовую строку, в которой числа разделены знаком “запятая”
        // (т.н. формат CSV - comma separated values). Для пустого массива возвращается пустая строка.
        // REVU вызовите метод, возвращающий StringBuilder, и toString
        String output = "";
        for (int i=0;i<array.length;i++) {
            // REVU += в цикле для строк - очень плохо
            if(i==array.length-1) output += array[i];
            if(i!=array.length-1) output += array[i] + ",";
        }
        return output;
    }


    public static String makeCsvStringFromDoubles(double[] array) {
        // REVU аналогично
        //Для заданного массива вещественных чисел создает текстовую строку, в которой числа разделены знаком “запятая”,
        // причем каждое число записывается с двумя знаками после точки. Для пустого массива возвращается пустая строка.
        if (array==null) return "";
        String output = "";
        DecimalFormat f = new DecimalFormat("##.00");
        for(int i=0;i<array.length;i++){
            if(i==array.length-1) output += f.format(array[i]);
            if(i!=array.length-1) output += f.format(array[i]) + ",";
        }
        return output;
    }


    public static StringBuilder makeCsvStringBuilderFromInts(int[] array) {
        //То же, что и в упражнении 25, но возвращает StringBuilder.
        // REVU а вот здесь напишите код, который делает StringBuilder из массива
        // используя его метод append
        return new StringBuilder(makeCsvStringFromInts(array));

    }

    public static StringBuilder makeCsvStringBuilderFromDoubles(double[] array) {
        //То же, что и в упражнении 26, но возвращает StringBuilder.
        // REVU аналогично
        return new StringBuilder(makeCsvStringFromDoubles(array));
    }

    public static StringBuilder removeCharacters(String string, int[] positions) {
        //Удаляет из строки символы, номера которых заданы в массиве positions. Предполагается, что будут передаваться
        // только допустимые номера, упорядоченные по возрастанию. Номера позиций для удаления указаны для исходной строки.
        // Возвращает полученный в результате StringBuilder.
        StringBuilder output = new StringBuilder(string);
        /*for (int index : positions) {
            //string = string.replace( string.charAt(index),"");       мб всё накроется из-за постепенного смещения,
            output.deleteCharAt(index);                              //а не одновременного
        }*/
        for(int i=0;i<positions.length;i++){
           output.deleteCharAt(positions[positions.length-i-1]);
        }
        return output;
    }

    public static StringBuilder insertCharacters(String string, int[] positions, char[] characters) {
        //Вставляет в строку символы. Массивы positions и characters имеют одинаковую длину. В позицию positions[i]
        // в исходной строке string вставляется символ characters[i]. Если в массиве positions один и тот же номер позиции
        // повторяется несколько раз, это значит, что в указанную позицию вставляется несколько символов, в том порядке, в
        // котором они перечислены в массиве characters. Предполагается, что будут передаваться только допустимые номера,
        // упорядоченные по неубыванию.  Возвращает полученный в результате StringBuilder.
        StringBuilder output = new StringBuilder(string);
        for (int i=0;i<positions.length;i++){
            output = output.insert(positions[positions.length-i-1],characters[characters.length-i-1]);
        }
        return output;
    }

    public static int getMinLength(String string1,String string2){
        if(string1.length()<=string2.length()) return string1.length();
        return string2.length();
    }

    // REVU не нужен
    public static String method(String str) {
        if (str.charAt(str.length()-1)=='x'){
            str = str.replace(str.substring(str.length()-1), "");
            return str;
        } else{
            return str;
        }
    }

}
