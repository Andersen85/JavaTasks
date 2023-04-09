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
    // REVU лучше BidiMap<String, User> mapTokenToUser
    private BidiMap<String, String> mapTokenToUsersLogin;
    // REVU а тут зачем BidiMap ? логин же есть внутри User
    private BidiMap<String, User> mapLoginToUser;


    public DataBase() {
        mapIdToUser = new HashMap<>();
        mapTokenToUsersLogin = new DualHashBidiMap<>();
        mapLoginToUser = new DualHashBidiMap<>();
        counter = 0;
    }


    public String insert(User user) throws ServerException {
        // REVU спешите. А ну как LOGIN_IS_ALREADY_TAKEN ?
        counter++;
        user.setId(counter);
        //Проверка дубликатов логина
        // REVU не надо проверять
        // просто mapLoginToUser.putIfAbsent и проверить результат
        if (mapLoginToUser.inverseBidiMap().getKey(user) != null) {
            throw new ServerException(ServerErrorCode.LOGIN_IS_ALREADY_TAKEN);
        }
        //Добавления в базу + проверки
        String token = UUID.randomUUID().toString();
        // REVU а для mapIdToUser не нужно putIfAbsent
        // просто put
        // не может там такого номера быть. Подумайте, почему
        if (mapIdToUser.putIfAbsent(counter, user) == null ||
                mapTokenToUsersLogin.putIfAbsent(token, user.getLogin()) == null ||
                mapLoginToUser.putIfAbsent(user.getLogin(), user) == null) {
            throw new ServerException(ServerErrorCode.YOU_ARE_ALREADY_REGISTERED);
        }
        return token;
    }


    public String login(String login) throws ServerException {

        if (mapTokenToUsersLogin.inverseBidiMap().getKey(login) != null) throw new
                // REVU верните прежний токен или создайте новый , а прежний удалите
                // исключения тут не надо
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
