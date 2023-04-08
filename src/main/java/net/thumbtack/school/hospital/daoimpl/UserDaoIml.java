package net.thumbtack.school.hospital.daoimpl;

import net.thumbtack.school.hospital.dao.UserDao;
import net.thumbtack.school.hospital.data.DataBase;
import net.thumbtack.school.hospital.exceptions.ServerException;

public class UserDaoIml implements UserDao {
    @Override
    public String login(String login) throws ServerException {
        return DataBase.getDataBase().login(login);
    }

    @Override
    public void logout(String token) throws ServerException {
        DataBase.getDataBase().logout(token);
    }

    @Override
    public void leave(String token) throws ServerException {
        DataBase.getDataBase().leave(token);
    }
}
