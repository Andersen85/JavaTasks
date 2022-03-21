package net.thumbtack.school.file;

import com.google.gson.Gson;
import net.thumbtack.school.exceptions.v3.GraphicException;
import net.thumbtack.school.pictures.v3.PictureFormat;
import net.thumbtack.school.pictures.v3.Point;
import net.thumbtack.school.pictures.v3.RectPicture;
import net.thumbtack.school.ttschool.Trainee;
import net.thumbtack.school.ttschool.TrainingException;

import java.io.*;
import java.nio.charset.StandardCharsets;

public class FileService {
    public static void writeByteArrayToBinaryFile(String fileName, byte[] array) throws IOException {
        //Записывает массив байтов в двоичный файл, имя файла задается текстовой строкой.
        writeByteArrayToBinaryFile(new File(fileName), array);
    }

    public static void writeByteArrayToBinaryFile(File file, byte[] array) throws IOException {
        //Записывает массив байтов в двоичный файл, имя файла задается экземпляром класса File.
        try (FileOutputStream out = new FileOutputStream(file)) {
            out.write(array);
        }
    }

    public static byte[] readByteArrayFromBinaryFile(String fileName) throws IOException {
        //Читает массив байтов из двоичного файла, имя файла задается текстовой строкой.
        return readByteArrayFromBinaryFile(new File(fileName));
    }

    public static byte[] readByteArrayFromBinaryFile(File file) throws IOException {
        //Читает массив байтов из двоичного файла, имя файла задается экземпляром класса File.
        try (FileInputStream input = new FileInputStream(file)) {
            return input.readAllBytes();
        }
    }

    public static byte[] writeAndReadByteArrayUsingByteStream(byte[] array) throws IOException {
        //Записывает массив байтов в ByteArrayOutputStream, создает на основе данных в полученном ByteArrayOutputStream
        //экземпляр ByteArrayInputStream и читает из ByteArrayInputStream байты с четными номерами.Возвращает массив
        //прочитанных байтов.
        try (ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream()) {
            byteArrayOutputStream.write(array);
            try (ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(byteArrayOutputStream.toByteArray())) {
                byte[] arr = new byte[array.length / 2];
                for (int i = 0; i < arr.length; i++) {
                    byteArrayInputStream.read(arr, i, 1);
                    byteArrayInputStream.skip(1);
                }
                return arr;
            }
        }
    }

    public static void writeByteArrayToBinaryFileBuffered(String fileName, byte[] array) throws IOException {
        //Записывает массив байтов в двоичный файл, используя буферизованный вывод, имя файла задается текстовой строкой.
        writeByteArrayToBinaryFileBuffered(new File(fileName), array);
    }

    public static void writeByteArrayToBinaryFileBuffered(File file, byte[] array) throws IOException {
        //Записывает массив байтов в двоичный файл, используя буферизованный вывод, имя файла задается экземпляром класса
        //File.
        try (BufferedOutputStream out = new BufferedOutputStream(new FileOutputStream(file))) {
            out.write(array);
        }
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(String fileName) throws IOException {
        //Читает массив байтов из двоичного файла, используя буферизованный ввод, имя файла задается текстовой строкой.
        return readByteArrayFromBinaryFileBuffered(new File(fileName));
    }

    public static byte[] readByteArrayFromBinaryFileBuffered(File file) throws IOException {
        //Читает массив байтов из двоичного файла, используя буферизованный ввод, имя файла задается экземпляром класса
        //File.
        try (BufferedInputStream input = new BufferedInputStream(new FileInputStream(file))) {
            byte[] array = new byte[(int) file.length()];
            array = input.readAllBytes();
            return array;
        }
    }

    public static void writeRectPictureToBinaryFile(File file, RectPicture rectPicture) throws IOException {
        //Записывает RectPicture в двоичный файл, имя файла задается экземпляром класса File.Формат картинки
        //записывается в виде текстовой строки в формате UTF.Используйте DataOutputStream.
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file))) {
            out.writeInt(rectPicture.getTopLeft().getX());
            out.writeInt(rectPicture.getTopLeft().getY());
            out.writeInt(rectPicture.getBottomRight().getX());
            out.writeInt(rectPicture.getBottomRight().getY());
            out.writeUTF(rectPicture.getFormat().getFormatName());
        }
    }

    public static RectPicture readRectPictureFromBinaryFile(File file) throws GraphicException, IOException {
        //Читает данные для RectPicture из двоичного файла и создает на их основе экземпляр RectPicture, имя
        //файла задается экземпляром класса File.Предполагается, что данные в файл записаны в формате
        //предыдущего упражнения.Используйте DataInputStream.
        try (DataInputStream input = new DataInputStream(new FileInputStream(file))) {
            return new RectPicture(new Point(input.readInt(), input.readInt()), new Point(input.readInt(), input.readInt()), input.readUTF());
        }
    }

    public static void writeRectPictureArrayToBinaryFile(File file, RectPicture[] rects) throws IOException {
        //Записывает массив из RectPicture в двоичный файл, имя файла задается экземпляром класса File.Формат картинки
        //не записывается.
        try (DataOutputStream out = new DataOutputStream(new FileOutputStream(file))) {
            for (RectPicture picture : rects) {
                out.writeInt(picture.getTopLeft().getX());
                out.writeInt(picture.getTopLeft().getY());
                out.writeInt(picture.getBottomRight().getX());
                out.writeInt(picture.getBottomRight().getY());
            }
        }
    }


    public static void modifyRectPictureArrayInBinaryFile(File file) throws IOException, GraphicException {
        //В файле массива данных RectPicture из предыдущего упражнения увеличивает на 1 значение x каждой точки
        //каждого RectPicture.Имя файла задается экземпляром класса File.Метод не должен читать все RectPicture из файла.
        try (RandomAccessFile raf = new RandomAccessFile(file, "rw")) {
            int size = (int) raf.length();
            RectPicture[] rpArray = new RectPicture[size / 16];
            for (int i = 0; i < rpArray.length; i++) {
                raf.seek(16 * i);
                rpArray[i] = new RectPicture(new Point(raf.readInt() + 1, raf.readInt()),
                        new Point(raf.readInt() + 1, raf.readInt()));
            }
            writeRectPictureArrayToBinaryFile(file, rpArray);
        }
    }

    public static RectPicture[] readRectPictureArrayFromBinaryFile(File file) throws IOException, GraphicException {
        //Читает данные, записанные в формате предыдущего упражнения и создает на их основе массив RectPicture в формате “
        //GIF”.
        try (RandomAccessFile raf = new RandomAccessFile(file, "r")) {
            int size = (int) raf.length();
            RectPicture[] rpArray = new RectPicture[size / 16];
            for (int i = 0; i < rpArray.length; i++) {
                raf.seek(16 * i);
                rpArray[i] = new RectPicture(new Point(raf.readInt(), raf.readInt()), new Point(raf.readInt(), raf.readInt()));
            }
            return rpArray;
        }
    }


    public static void writeRectPictureToTextFileOneLine(File file, RectPicture rectPicture) throws IOException {
        //Записывает RectPicture в текстовый файл в одну строку, имя файла задается экземпляром класса File.Поля в
        //файле разделяются пробелами.
        try (DataOutputStream output = new DataOutputStream(new FileOutputStream(file))) {
            output.writeInt(rectPicture.getTopLeft().getX());
            output.writeInt(rectPicture.getTopLeft().getY());
            output.writeInt(rectPicture.getBottomRight().getX());
            output.writeInt(rectPicture.getBottomRight().getY());
        }
    }

    public static RectPicture readRectPictureFromTextFileOneLine(File file) throws GraphicException, IOException {
        //Читает данные для RectPicture из текстового файла и создает на их основе экземпляр RectPicture, имя
        //файла задается экземпляром класса File.Предполагается, что данные в файл записаны в формате
        //предыдущего упражнения.
        try (DataInputStream input = new DataInputStream(new FileInputStream(file))) {
            return new RectPicture(new Point(input.readInt(), input.readInt()), new Point(input.readInt(), input.readInt()));
        }
    }

    public static void writeRectPictureToTextFileFiveLines(File file, RectPicture rectPicture) throws IOException {
        //Записывает RectPicture в текстовый файл В первые 4 строки записываются
        //координаты(каждое число в отдельной строке), в следующую - формат.Имя файла задается экземпляром класса File.
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            writer.write(rectPicture.getTopLeft().getX() + "\n");
            writer.write(rectPicture.getTopLeft().getY() + "\n");
            writer.write(rectPicture.getBottomRight().getX() + "\n");
            writer.write(rectPicture.getBottomRight().getY() + "\n");
            writer.write(rectPicture.getFormat().getFormatName());
        }
    }

    public static RectPicture readRectPictureFromTextFileFiveLines(File file) throws IOException, GraphicException {
        //Читает данные для RectPicture из текстового файла и создает на их основе экземпляр RectPicture, имя
        //файла задается экземпляром класса File.Предполагается, что данные в файл записаны в формате
        //предыдущего упражнения.
        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream(file), StandardCharsets.UTF_8))) {
            return new RectPicture(new Point(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine())),
                    new Point(Integer.parseInt(reader.readLine()), Integer.parseInt(reader.readLine())), PictureFormat.GIF);
        }
    }

    public static void writeTraineeToTextFileOneLine(File file, Trainee trainee) throws IOException {
        //Записывает Trainee в текстовый файл в одну строку в кодировке UTF - 8, имя файла задается экземпляром класса
        //File.Имя, фамилия и оценка в файле разделяются пробелами.
        try (BufferedWriter writer = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(file)))) {
            writer.write(trainee.getFullName() + " " + trainee.getRating());
        }
    }


    public static Trainee readTraineeFromTextFileOneLine(File file) throws TrainingException, IOException {
        //Читает данные для Trainee из текстового файла и создает на их основе экземпляр Trainee, имя файла задается
        //экземпляром класса File.Предполагается, что данные в файл записаны в формате предыдущего упражнения.
        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream(file), StandardCharsets.UTF_8))) {
            String[] strings = reader.readLine().split(" ");
            return new Trainee(strings[0], strings[1], Integer.parseInt(strings[2]));
        }
    }

    public static void writeTraineeToTextFileThreeLines(File file, Trainee trainee) throws IOException {
        //Записывает Trainee в текстовый файл в кодировке UTF -8, каждое поле в отдельной строке, имя файла задается
        //экземпляром класса File.
        try (Writer writer = new OutputStreamWriter(new FileOutputStream(file), StandardCharsets.UTF_8)) {
            writer.write(trainee.getFirstName() + "\n");
            writer.write(trainee.getLastName() + "\n");
            writer.write(trainee.getRating() + "\n");
        }
    }


    public static Trainee readTraineeFromTextFileThreeLines(File file) throws IOException, TrainingException {
        //Читает данные для Trainee из текстового файла и создает на их основе экземпляр Trainee, имя файла задается
        //экземпляром класса File.Предполагается, что данные в файл записаны в формате предыдущего упражнения.
        try (BufferedReader reader = new BufferedReader(new InputStreamReader
                (new FileInputStream(file), StandardCharsets.UTF_8))) {
            return new Trainee(reader.readLine(), reader.readLine(), Integer.parseInt(reader.readLine()));
        }
    }

    public static void serializeTraineeToBinaryFile(File file, Trainee trainee) throws IOException {
        //Сериализует Trainee в двоичный файл, имя файла задается экземпляром класса File.
        try (ObjectOutputStream output = new ObjectOutputStream(new FileOutputStream(file))) {
            output.writeObject(trainee);
        }
    }

    public static Trainee deserializeTraineeFromBinaryFile(File file) throws IOException, ClassNotFoundException {
        //Десериализует Trainee из двоичного файла, имя файла задается экземпляром класса File.Предполагается, что
        //данные в файл записаны в формате предыдущего упражнения.

        try (ObjectInputStream input = new ObjectInputStream(new FileInputStream(file))) {
            return (Trainee) input.readObject();
        }

    }

    public static String serializeTraineeToJsonString(Trainee trainee) {
        //Сериализует Trainee в формате Json в текстовую строку.
        Gson gson = new Gson();
        return gson.toJson(trainee);
    }

    public static Trainee deserializeTraineeFromJsonString(String json) {
        //Десериализует Trainee из текстовой строки с Json - представлением Trainee.
        Gson gson = new Gson();
        return gson.fromJson(json, Trainee.class);
    }

    public static void serializeTraineeToJsonFile(File file, Trainee trainee) throws IOException {
        //Сериализует Trainee в формате Json в файл, имя файла задается экземпляром класса File.
        try (BufferedWriter bw = new BufferedWriter(new FileWriter(file))) {
            Gson gson = new Gson();
            gson.toJson(trainee, bw);
        }
    }

    public static Trainee deserializeTraineeFromJsonFile(File file) throws IOException {
        //Десериализует Trainee из файла с Json -представлением Trainee, имя файла задается экземпляром класса File.
        try (BufferedReader br = new BufferedReader(new FileReader(file))) {
            Gson gson = new Gson();
            return gson.fromJson(br, Trainee.class);
        }
    }
}
