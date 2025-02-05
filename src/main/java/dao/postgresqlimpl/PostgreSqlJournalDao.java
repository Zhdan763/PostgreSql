package dao.postgresqlimpl;

import dao.JournalDao;
import dao.ObjectDao;
import domain.entity.JournalEntity;
import exception.ConnectionException;
import exception.DaoException;
import util.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSqlJournalDao implements ObjectDao<JournalEntity>, JournalDao {
    private Connection connection;

    public PostgreSqlJournalDao(Connection connection) throws ConnectionException {
        if (connection == null) {
            throw new ConnectionException("Connection is null");
        } else {
            this.connection = connection;
        }
    }

    @Override
    public void create(JournalEntity object) throws DaoException {
        String sql = "INSERT INTO journal (description, date, name) VALUES (?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, object.getDescription());
            preparedStatement.setDate(2, object.getDate());
            preparedStatement.setString(3, object.getName());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            if (e.getMessage().contains("существует")) {
                throw new DaoException("Unable to create journal, because such a name exists ");
            } else {
                throw new DaoException("Unable to create user, because incorrect index");
            }


        }
    }

    @Override
    public void delete(int id) throws DaoException {
        String sql = "DELETE FROM journal WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to delete journal, because incorrect index");
        }
    }

    @Override
    public void update(JournalEntity object) throws DaoException {
        String sql = "UPDATE journal SET id=?, description = ?, date= ?, name=? WHERE id=?";
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, object.getId());
            preparedStatement.setString(2, object.getDescription());
            preparedStatement.setDate(3, object.getDate());
            preparedStatement.setString(4, object.getName());
            preparedStatement.setLong(5, object.getId());

            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to update journal, because incorrect index");
        }
    }

    @Override
    public JournalEntity get(int id) throws DaoException {
        String sql = "SELECT * FROM journal WHERE id=?";
        ResultSet rs = null;
        JournalEntity journal = new JournalEntity();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                journal.setId(id);
                journal.setDescription(rs.getString(Constants.JOURNAL_DESCRIPTION_COLUMN));
                journal.setDate(rs.getDate(Constants.JOURNAL_DATE_COLUMN));
                journal.setName(rs.getString(Constants.JOURNAL_NAME_COLUMN));
            }
        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get journal, because incorrect index");
        }
        return journal;
    }

    @Override
    public List<JournalEntity> getAll() throws DaoException {

        String sql = "SELECT * FROM journal";
        ResultSet rs = null;
        List<JournalEntity> journals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JournalEntity journal = JournalEntity.builder()
                        .id(rs.getInt(Constants.JOURNAL_ID_COLUMN))
                        .name(rs.getString(Constants.JOURNAL_NAME_COLUMN))
                        .description(rs.getString(Constants.JOURNAL_DESCRIPTION_COLUMN))
                        .date(rs.getDate(Constants.JOURNAL_DATE_COLUMN))
                        .build();
                journals.add(journal);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get journal, because incorrect index");
        }
        return journals;
    }


    @Override
    public List<JournalEntity> getAllDescriptionAsc() throws DaoException {
        String sql = "SELECT * FROM journal ORDER BY description ASC";
        ResultSet rs = null;
        List<JournalEntity> journals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JournalEntity journal = JournalEntity.builder()
                        .id(rs.getInt(Constants.JOURNAL_ID_COLUMN))
                        .name(rs.getString(Constants.JOURNAL_NAME_COLUMN))
                        .description(rs.getString(Constants.JOURNAL_DESCRIPTION_COLUMN))
                        .date(rs.getDate(Constants.JOURNAL_DATE_COLUMN))
                        .build();
                journals.add(journal);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get journal, because incorrect index");
        }
        return journals;
    }

    @Override
    public List<JournalEntity> getAllDescriptionDesc() throws DaoException {
        String sql = "SELECT * FROM journal ORDER BY description DESC";
        ResultSet rs = null;
        List<JournalEntity> journals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JournalEntity journal = JournalEntity.builder()
                        .id(rs.getInt(Constants.JOURNAL_ID_COLUMN))
                        .name(rs.getString(Constants.JOURNAL_NAME_COLUMN))
                        .description(rs.getString(Constants.JOURNAL_DESCRIPTION_COLUMN))
                        .date(rs.getDate(Constants.JOURNAL_DATE_COLUMN))
                        .build();
                journals.add(journal);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get journal, because incorrect index");
        }
        return journals;
    }


    @Override
    public List<JournalEntity> getAllDateAsc() throws DaoException {
        String sql = "SELECT * FROM journal ORDER BY date ASC";
        ResultSet rs = null;
        List<JournalEntity> journals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JournalEntity journal = JournalEntity.builder()
                        .id(rs.getInt(Constants.JOURNAL_ID_COLUMN))
                        .name(rs.getString(Constants.JOURNAL_NAME_COLUMN))
                        .description(rs.getString(Constants.JOURNAL_DESCRIPTION_COLUMN))
                        .date(rs.getDate(Constants.JOURNAL_DATE_COLUMN))
                        .build();
                journals.add(journal);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get journal, because incorrect index");
        }
        return journals;
    }

    @Override
    public List<JournalEntity> getAllDateDesc() throws DaoException {
        String sql = "SELECT * FROM journal ORDER BY date DESC";
        ResultSet rs = null;
        List<JournalEntity> journals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JournalEntity journal = JournalEntity.builder()
                        .id(rs.getInt(Constants.JOURNAL_ID_COLUMN))
                        .name(rs.getString(Constants.JOURNAL_NAME_COLUMN))
                        .description(rs.getString(Constants.JOURNAL_DESCRIPTION_COLUMN))
                        .date(rs.getDate(Constants.JOURNAL_DATE_COLUMN))
                        .build();
                journals.add(journal);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get journal, because incorrect index");
        }
        return journals;
    }


    @Override
    public List<JournalEntity> getAllFilterByDescription(String value) throws DaoException {
        String sql = "SELECT * FROM journal WHERE description LIKE ? ";
        ResultSet rs = null;
        List<JournalEntity> journals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + value + "%");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JournalEntity journal = JournalEntity.builder()
                        .id(rs.getInt(Constants.JOURNAL_ID_COLUMN))
                        .name(rs.getString(Constants.JOURNAL_NAME_COLUMN))
                        .description(rs.getString(Constants.JOURNAL_DESCRIPTION_COLUMN))
                        .date(rs.getDate(Constants.JOURNAL_DATE_COLUMN))
                        .build();
                journals.add(journal);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get journal, because incorrect index");
        }
        return journals;
    }

    @Override
    public List<JournalEntity> getAllFilterByDate(String value) throws DaoException {
        String sql = "SELECT * FROM journal WHERE date = ?";
        ResultSet rs = null;
        Date date = Date.valueOf(value);
        List<JournalEntity> journals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setDate(1, date);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JournalEntity journal = JournalEntity.builder()
                        .id(rs.getInt(Constants.JOURNAL_ID_COLUMN))
                        .name(rs.getString(Constants.JOURNAL_NAME_COLUMN))
                        .description(rs.getString(Constants.JOURNAL_DESCRIPTION_COLUMN))
                        .date(rs.getDate(Constants.JOURNAL_DATE_COLUMN))
                        .build();
                journals.add(journal);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get journal, because incorrect index");
        }
        return journals;
    }

    @Override
    public List<JournalEntity> getAllNameAsc() throws DaoException {
        String sql = "SELECT * FROM journal ORDER BY name ASC";
        ResultSet rs = null;
        List<JournalEntity> journals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JournalEntity journal = JournalEntity.builder()
                        .id(rs.getInt(Constants.JOURNAL_ID_COLUMN))
                        .name(rs.getString(Constants.JOURNAL_NAME_COLUMN))
                        .description(rs.getString(Constants.JOURNAL_DESCRIPTION_COLUMN))
                        .date(rs.getDate(Constants.JOURNAL_DATE_COLUMN))
                        .build();
                journals.add(journal);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get journal, because incorrect index");
        }
        return journals;
    }

    @Override
    public List<JournalEntity> getAllNameDesc() throws DaoException {
        String sql = "SELECT * FROM journal ORDER BY name DESC";
        ResultSet rs = null;
        List<JournalEntity> journals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JournalEntity journal = JournalEntity.builder()
                        .id(rs.getInt(Constants.JOURNAL_ID_COLUMN))
                        .name(rs.getString(Constants.JOURNAL_NAME_COLUMN))
                        .description(rs.getString(Constants.JOURNAL_DESCRIPTION_COLUMN))
                        .date(rs.getDate(Constants.JOURNAL_DATE_COLUMN))
                        .build();
                journals.add(journal);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get journal, because incorrect index");
        }
        return journals;
    }

    @Override
    public List<JournalEntity> getAllFilterByName(String value) throws DaoException {
        String sql = "SELECT * FROM journal WHERE name LIKE ? ";
        ResultSet rs = null;
        List<JournalEntity> journals = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, "%" + value + "%");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                JournalEntity journal = JournalEntity.builder()
                        .id(rs.getInt(Constants.JOURNAL_ID_COLUMN))
                        .name(rs.getString(Constants.JOURNAL_NAME_COLUMN))
                        .description(rs.getString(Constants.JOURNAL_DESCRIPTION_COLUMN))
                        .date(rs.getDate(Constants.JOURNAL_DATE_COLUMN))
                        .build();
                journals.add(journal);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get journal, because incorrect index");
        }
        return journals;
    }

}
