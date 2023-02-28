package net.thumbtack.school.hospital.data;

import net.thumbtack.school.hospital.exceptions.ServerErrorCode;
import net.thumbtack.school.hospital.exceptions.ServerException;
import net.thumbtack.school.hospital.model.User;
import org.apache.commons.collections4.BidiMap;
import org.apache.commons.collections4.bidimap.DualHashBidiMap;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class DataBase {

    private static DataBase singletonDataBase;
    private int counter;

    private Map<Integer, User> mapIdToUser;
    private Map<String, User> mapTokenToUser;
    private Map<String, User> mapLoginToUser;


    public DataBase() {
        mapIdToUser = new HashMap<>();
        mapTokenToUser = new HashMap<>();
        counter = 0;
    }


    public String insert(User user) {
        //+Проверка дубликатов логина
        counter++;
        user.setId(counter);
        mapIdToUser.put(counter, user);
        String token = UUID.randomUUID().toString();
        mapTokenToUser.put(token, user);
        mapLoginToUser.put(user.getLogin(), user);
        return token;
    }

    public String login(String login, String password) throws ServerException {
        User user = mapLoginToUser.get(login);
        if (user == null) throw new ServerException(ServerErrorCode.WRONG_LOGIN);
        if (user.getPassword().equals(password)) throw new ServerException(ServerErrorCode.WRONG_PASSWORD);
        if (mapTokenToUser.containsValue(user)) throw new ServerException(ServerErrorCode.USER_ALREADY_LOGINED);
        String token = UUID.randomUUID().toString();
        mapTokenToUser.put(token, user);
        return token;
    }

    public void logout(String token) throws ServerException {
        if (mapTokenToUser.remove(token) == null) throw new ServerException(ServerErrorCode.YOU_ARE_NOT_LOGGED_IN);
    }

    public void leave(String token) throws ServerException {
        User user = mapTokenToUser.get(token);
        if(user == null) throw new ServerException(ServerErrorCode.YOU_ARE_NOT_LOGGED_IN);
        mapIdToUser.remove(user.getId());
        mapLoginToUser.remove(user.getLogin());
        mapTokenToUser.remove(token);
    }

    public static DataBase getDataBase() {
        if (singletonDataBase == null) {
            singletonDataBase = new DataBase();
        }
        return singletonDataBase;
    }

}
