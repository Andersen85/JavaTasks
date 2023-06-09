package net.thumbtack.school.hospital.dao;

import net.thumbtack.school.hospital.exceptions.ServerException;

public interface UserDao {
    String login(String login, String password) throws ServerException;
    void logout(String token) throws ServerException;
    void leave(String token) throws ServerException;
    String changePassword(String login, String oldPassword, String newPassword) throws ServerException;
}
