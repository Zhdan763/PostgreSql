package dao;

import domain.entity.UserEntity;
import exception.DaoException;

public interface UserDao extends ObjectDao<UserEntity>{

    String getPassword(String name) throws DaoException;

    Boolean checkLogin(String login) throws DaoException;
}
