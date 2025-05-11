package dao.postgresqlimpl;

import dao.ObjectDao;
import dao.UserDao;
import domain.entity.UserEntity;
import exception.DaoException;
import util.Constants;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;

public class PostgreSqlUserDao implements ObjectDao<UserEntity>, UserDao {
    private Connection connection;

    public PostgreSqlUserDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(UserEntity userEntity) throws DaoException {
        String sql = "INSERT INTO users (login, password) VALUES (?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, userEntity.getLogin());
            preparedStatement.setString(2, userEntity.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlUserDao.class.getName() + ". Date: " +
                    new Date() + ". Message: " + e);
            if (e.getMessage().contains("существует")) {
                throw new DaoException("Unable to create user, because such a name exists ");
            } else {
                throw new DaoException("Unable to create user. Server error");
            }
        }

    }

    @Override
    public void delete(int id) throws DaoException {
        String sql = "DELETE FROM users WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlUserDao.class.getName() + ". Date: " +
                    new Date() + ". Message: " + e);
            throw new DaoException("Unable to delete user, because incorrect index");
        }
    }

    @Override
    public void update(UserEntity userEntity) throws DaoException {
        String sql = "UPDATE users SET id=?, login = ?, password= ? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, userEntity.getId());
            preparedStatement.setString(2, userEntity.getLogin());
            preparedStatement.setString(3, userEntity.getPassword());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlUserDao.class.getName() + ". Date: " +
                    new Date() + ". Message: " + e);
            throw new DaoException("Unable to update user, because incorrect index");
        }

    }

    @Override
    public UserEntity get(int id) throws DaoException {
        String sql = "SELECT * FROM users WHERE id=?";
        ResultSet rs = null;
        UserEntity userEntity = new UserEntity();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                userEntity.setId(id);
                userEntity.setLogin(rs.getString(Constants.USER_LOGIN_COLUMN));
                userEntity.setPassword(rs.getString(Constants.USER_PASSWORD_COLUMN));
            }
        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlUserDao.class.getName() + ". Date: " +
                    new Date() + ". Message: " + e);
            throw new DaoException("Unable to get user, because incorrect index");
        }
        return userEntity;

    }

    @Override
    public List<UserEntity> getAll() throws DaoException {
        String sql = "SELECT * FROM users";
        ResultSet rs = null;
        List<UserEntity> userEntityList = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                UserEntity userEntity = UserEntity.builder()
                        .id(rs.getInt(Constants.USER_ID_COLUMN))
                        .login(rs.getString(Constants.USER_LOGIN_COLUMN))
                        .password(rs.getString(Constants.USER_PASSWORD_COLUMN))
                        .build();
                userEntityList.add(userEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlUserDao.class.getName() + ". Date: " +
                    new Date() + ". Message: " + e);
            throw new DaoException("Unable to getAll user, because incorrect index");
        }
        return userEntityList;

    }


    @Override
    public String getPassword(String name) throws DaoException {
        String sql = "SELECT * FROM users WHERE login like ?";
        Map<String, String> map = new HashMap<>();

        ResultSet rs = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, name);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String password = rs.getString(Constants.USER_PASSWORD_COLUMN);
                map.put(Constants.USER_PASSWORD_COLUMN, password);
            }
        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlUserDao.class.getName() + ". Date: " +
                    new Date() + ". Message: " + e);
            throw new DaoException("Unable to get password user, because incorrect index");
        }
        return map.get(Constants.USER_PASSWORD_COLUMN);
    }

    @Override
    public Boolean checkLogin(String login) throws DaoException {
        String sql = "SELECT * FROM users WHERE login like ?";
        ResultSet rs = null;
        Map<String, Boolean> map = new HashMap<>();
        Boolean check = null;
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, login);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                String loginPostgreSql = rs.getString(Constants.USER_LOGIN_COLUMN);
                if (loginPostgreSql.equalsIgnoreCase("null")) {
                    check = true;
                } else {
                    check = false;
                }
            }
        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlUserDao.class.getName() + ". Date: " +
                    new Date() + ". Message: " + e);
            throw new DaoException("Unable to check login user, because incorrect index");
        }
        return check;
    }
}