package controller;

import dao.DaoManager;
import dao.UserDao;
import domain.User;
import domain.entity.UserEntity;
import exception.*;
import util.Encryptor;
import util.UserConverter;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private static UserController instance;
    private UserConverter userConverter;
    private UserDao userDao;

    private UserController(UserDao userDao)  {
        this.userConverter = new UserConverter();
        this.userDao=userDao;
    }

    public static synchronized UserController initInstance(UserDao userDao) {
        if (instance == null) {
            instance = new UserController(userDao);
        }
        return instance;
    }

    public static synchronized UserController getInstance() throws NotInitializedException {
        if (instance == null) {
            throw new NotInitializedException(
                    "UserController cannot be returned because it has not yet been initialized");
        }
        return instance;
    }

    public void createUser(String passwordUser, String login) throws DaoException, EncryptorException {
        Encryptor encryptor = new Encryptor();
        String password = encryptor.hashWithSHA256(passwordUser);
        User user = User.builder()
                .login(login)
                .password(password)
                .build();
        UserEntity userEntity = userConverter.convertDoMainToEntity(user);
        userDao.create(userEntity);
    }

    public void update(User user) throws DaoException {
        UserEntity userEntity = userConverter.convertDoMainToEntity(user);
        userDao.update(userEntity);
    }

    public void delete(int id) throws DaoException {
        userDao.delete(id);
    }

    public User get(int id) throws DaoException {
        UserEntity userEntity = (UserEntity) userDao.get(id);
        return userConverter.convertEntityToDoMain(userEntity);
    }

    public List<User> getAll() throws DaoException {
        List<UserEntity> userEntityList = userDao.getAll();
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < userEntityList.size(); i++) {
            User user = userConverter.convertEntityToDoMain(userEntityList.get(i));
            userList.add(user);
        }
        return userList;
    }

    public boolean checkPassword(String login, String password) throws DaoException, EncryptorException {
        String postgresPassword = userDao.getPassword(login);
        Encryptor encryptor = new Encryptor();

        return encryptor.checkPassword(postgresPassword, password);


    }

    public Boolean checkLogin(String login) throws DaoException {
        if ((userDao.checkLogin(login)) == null) {
            return false;
        } else {
            return true;
        }

    }

    public Boolean check(String login, String password) throws DaoException, EncryptorException {
        if (checkLogin(login)) {
            if (checkPassword(login, password)) {
                return true;
            } else {
                return false;
            }
        } else {
            return false;
        }
    }


}


