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
    private BidiMap<String, String> mapTokenToUsersLogin;
    private BidiMap<String, User> mapLoginToUser;


    public DataBase() {
        mapIdToUser = new HashMap<>();
        mapTokenToUsersLogin = new DualHashBidiMap<>();
        mapLoginToUser = new DualHashBidiMap<>();
        counter = 0;
    }


    public String insert(User user) throws ServerException {
        counter++;
        user.setId(counter);
        //Проверка дубликатов логина
        if (mapLoginToUser.inverseBidiMap().getKey(user) != null) {
            throw new ServerException(ServerErrorCode.LOGIN_IS_ALREADY_TAKEN);
        }
        //Добавления в базу + проверки
        String token = UUID.randomUUID().toString();
        if (mapIdToUser.putIfAbsent(counter, user) == null ||
                mapTokenToUsersLogin.putIfAbsent(token, user.getLogin()) == null ||
                mapLoginToUser.putIfAbsent(user.getLogin(), user) == null) {
            throw new ServerException(ServerErrorCode.YOU_ARE_ALREADY_REGISTERED);
        }
        return token;
    }


    public String login(String login) throws ServerException {

        if (mapTokenToUsersLogin.inverseBidiMap().getKey(login) != null) throw new
                ServerException(ServerErrorCode.USER_ALREADY_LOGINED);
        String token = UUID.randomUUID().toString();
        mapTokenToUsersLogin.put(token, login);
        return token;
    }

    public void logout(String token) throws ServerException {
        if (mapTokenToUsersLogin.remove(token) == null)
            throw new ServerException(ServerErrorCode.YOU_ARE_NOT_LOGGED_IN);
    }

    public void leave(String token) throws ServerException {
        String login = mapTokenToUsersLogin.get(token);
        if (login == null) throw new ServerException(ServerErrorCode.YOU_ARE_NOT_LOGGED_IN);
        User user = mapLoginToUser.get(login);
        mapIdToUser.remove(user.getId());
        mapLoginToUser.remove(user.getLogin());
        mapTokenToUsersLogin.remove(token);
    }

    public static DataBase getDataBase() {
        if (singletonDataBase == null) {
            singletonDataBase = new DataBase();
        }
        return singletonDataBase;
    }

    public Map<String, User> getMapLoginToUser() {
        return mapLoginToUser;
    }
}
