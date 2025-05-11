package controller;

import dao.UserDao;
import domain.User;
import domain.entity.UserEntity;
import exception.ControllerException;
import exception.DaoException;
import exception.EncryptorException;
import exception.NotInitializedException;
import util.Encryptor;
import util.UserConverter;

import java.util.ArrayList;
import java.util.List;

public class UserController {
    private static UserController instance;
    private UserConverter userConverter;
    private UserDao userDao;

    private UserController(UserDao userDao) {
        this.userConverter = new UserConverter();
        this.userDao = userDao;
    }

    public static synchronized UserController initInstance(UserDao userDao) {
        if (instance == null) {
            instance = new UserController(userDao);
        }
        return instance;
    }

    public static synchronized UserController getInstance() throws ControllerException {
        if (instance == null) {
            throw new ControllerException(
                    "UserController cannot be returned because it has not yet been initialized");
        }
        return instance;
    }

    public void createUser(String passwordUser, String login) throws  ControllerException {
        Encryptor encryptor = new Encryptor();
        String password = null;
        try {
            password = encryptor.hashWithSHA256(passwordUser);
        } catch (EncryptorException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot hash password");
        }
        User user = User.builder()
                .login(login)
                .password(password)
                .build();
        UserEntity userEntity = userConverter.convertDoMainToEntity(user);
        try {
            userDao.create(userEntity);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot create user");
        }
    }

    public void update(User user) throws ControllerException {
        UserEntity userEntity = userConverter.convertDoMainToEntity(user);
        try {
            userDao.update(userEntity);
        } catch (DaoException e) {
            throw new ControllerException("Cannot update user");
        }
    }

    public void delete(int id) throws ControllerException {
        try {
            userDao.delete(id);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot delete user");
        }
    }

    public User get(int id) throws ControllerException {
        UserEntity userEntity = null;
        try {
            userEntity = (UserEntity) userDao.get(id);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get user");
        }
        return userConverter.convertEntityToDoMain(userEntity);
    }

    public List<User> getAll() throws ControllerException {
        List<UserEntity> userEntityList = null;
        try {
            userEntityList = userDao.getAll();
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list users");
        }
        List<User> userList = new ArrayList<>();
        for (int i = 0; i < userEntityList.size(); i++) {
            User user = userConverter.convertEntityToDoMain(userEntityList.get(i));
            userList.add(user);
        }
        return userList;
    }

    public boolean checkPassword(String login, String password) throws  ControllerException {
        String postgresPassword = null;
        try {
            postgresPassword = userDao.getPassword(login);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get password");
        }
        Encryptor encryptor = new Encryptor();

        try {
            return encryptor.checkPassword(postgresPassword, password);
        } catch (EncryptorException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot check password");
        }


    }

    public Boolean checkLogin(String login) throws ControllerException {
        try {
            if ((userDao.checkLogin(login)) == null) {
                return false;
            } else {
                return true;
            }
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot check login");
        }

    }

    public Boolean check(String login, String password) throws ControllerException {
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


