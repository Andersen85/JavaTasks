package net.thumbtack.school.hospital.serviсe;

import com.google.gson.Gson;
import com.google.gson.JsonSyntaxException;
import net.thumbtack.school.hospital.exceptions.ServerErrorCode;
import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.model.Doctor;

public class ServiceUtils {
    private static final Gson gson = new Gson();

    public static <T> T getClassFromJson(String json, Class<T> tClass) throws ServerException {
        try {
            return gson.fromJson(json, tClass);
        } catch (JsonSyntaxException e) {
            throw new ServerException(ServerErrorCode.WROND_DATA_IN_REQUEST);
        }
    }


}
