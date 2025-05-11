package dao.postgresqlimpl;

import dao.ObjectDao;
import dao.TaskDao;
import domain.entity.TaskEntity;
import exception.DaoException;
import util.Constants;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class PostgreSqlTaskDao implements ObjectDao<TaskEntity>, TaskDao {
    private Connection connection;

    public PostgreSqlTaskDao(Connection connection) {
        this.connection = connection;
    }

    @Override
    public void create(TaskEntity taskEntity) throws DaoException {
        String sql = "INSERT INTO task (name, date, status, journal_id, description) VALUES (?, ?, ?, ?, ?)";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, taskEntity.getName());
            preparedStatement.setTimestamp(2, taskEntity.getDate());
            preparedStatement.setString(3, taskEntity.getStatus());
            preparedStatement.setLong(4, taskEntity.getJournalId());
            preparedStatement.setString(5, taskEntity.getDescription());
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to create task, because: " + e.getMessage());
        }

    }

    @Override
    public void delete(int id) throws DaoException {
        String sql = "DELETE FROM task WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            preparedStatement.executeUpdate();

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to delete task, because incorrect index");
        }
    }

    @Override
    public void update(TaskEntity object) throws DaoException {
        String sql = "UPDATE task SET id=?, name = ?, date= ?, status=?, journal_id=?, description=? WHERE id=?";

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setLong(1, object.getId());
            preparedStatement.setString(2, object.getName());
            preparedStatement.setTimestamp(3, object.getDate());
            preparedStatement.setString(4, object.getStatus());
            preparedStatement.setLong(5, object.getJournalId());
            preparedStatement.setString(6, object.getDescription());
            preparedStatement.setLong(7, object.getId());
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to update task, because: " + e);
        }

    }

    @Override
    public TaskEntity get(int id) throws DaoException {
        String sql = "SELECT * FROM task WHERE id=?";
        ResultSet rs = null;
        TaskEntity taskEntity = new TaskEntity();

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                taskEntity.setId(id);
                taskEntity.setName(rs.getString(Constants.TASK_NAME_COLUMN));
                taskEntity.setDate(rs.getTimestamp(Constants.TASK_DATE_COLUMN));
                taskEntity.setStatus(rs.getString(Constants.TASK_STATUS_COLUMN));
                taskEntity.setJournalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN));
                taskEntity.setDescription(Constants.TASK_DESCRIPTION_COLUMN);
            }
        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntity;
    }


    @Override
    public List<TaskEntity> getAll() throws DaoException {
        String sql = "SELECT * FROM task";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get all tasks, because incorrect index");
        }
        return taskEntities;
    }

    public List<TaskEntity> getTasksByJournalId(int journalId) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=?";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {


            preparedStatement.setInt(1, journalId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdArray(int[] journalId) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=?";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {

            for (int i = 0; i < journalId.length; i++) {
                preparedStatement.setInt(1, journalId[i]);
                rs = preparedStatement.executeQuery();
                while (rs.next()) {
                    TaskEntity taskEntity = TaskEntity.builder()
                            .id(rs.getInt(Constants.TASK_ID_COLUMN))
                            .name(rs.getString(Constants.TASK_NAME_COLUMN))
                            .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                            .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                            .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                            .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                            .build();
                    taskEntities.add(taskEntity);
                }
            }
        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdNameAsc(int journalId) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=? ORDER BY name ASC";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, journalId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdNameDesc(int journalId) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=? ORDER BY name DESC";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, journalId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdDescriptionAsc(int journalId) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=? ORDER BY description ASC";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, journalId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdDescriptionDesc(int journalId) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=? ORDER BY description DESC";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, journalId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdDateAsc(int journalId) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=? ORDER BY date ASC";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, journalId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdDateDesc(int journalId) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=? ORDER BY date DESC";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, journalId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdStatusAsc(int journalId) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=? ORDER BY status ASC";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, journalId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdStatusDesc(int journalId) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=? ORDER BY status DESC";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, journalId);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdFilterByName(int journalId, String value) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=? AND name LIKE ?";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, journalId);
            preparedStatement.setString(2, "%" + value + "%");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdFilterByDescription(int journalId, String value) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=? AND description LIKE ?";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, journalId);
            preparedStatement.setString(2, "%" + value + "%");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdFilterByDate(int journalId, String value) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=? AND date =?";
        ResultSet rs = null;
        Date date = Date.valueOf(value);
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, journalId);
            preparedStatement.setDate(2, date);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdFilterByStatus(int journalId, String value) throws DaoException {
        String sql = "SELECT * FROM task WHERE journal_id=? AND status LIKE ?";
        ResultSet rs = null;
        List<TaskEntity> taskEntities = new ArrayList<>();
        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, journalId);
            preparedStatement.setString(2, "%" + value + "%");
            rs = preparedStatement.executeQuery();
            while (rs.next()) {
                TaskEntity taskEntity = TaskEntity.builder()
                        .id(rs.getInt(Constants.TASK_ID_COLUMN))
                        .name(rs.getString(Constants.TASK_NAME_COLUMN))
                        .description(rs.getString(Constants.TASK_DESCRIPTION_COLUMN))
                        .date(rs.getTimestamp(Constants.TASK_DATE_COLUMN))
                        .status(rs.getString(Constants.TASK_STATUS_COLUMN))
                        .journalId(rs.getInt(Constants.TASK_JOURNAL_ID_COLUMN))
                        .build();
                taskEntities.add(taskEntity);
            }

        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlTaskDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to get task, because incorrect index");
        }
        return taskEntities;
    }

    @Override
    public Boolean checkTask(TaskEntity taskEntity) throws DaoException {
        String sql = "SELECT EXISTS (SELECT id FROM task WHERE id = ?)";
        int id = (int) taskEntity.getId();
        Boolean exist = null;
        ResultSet rs = null;

        try (PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setInt(1, id);
            rs = preparedStatement.executeQuery();
            while (rs.next()) {

                exist = rs.getBoolean(Constants.EXISTS_COLUMN);
            }
        } catch (SQLException e) {
            System.out.println("Error! Class: " + PostgreSqlJournalDao.class.getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new DaoException("Unable to check task, because: " + e);
        }
        return exist;
    }
}
