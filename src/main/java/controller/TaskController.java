package controller;

import dao.TaskDao;
import domain.Status;
import domain.Task;
import domain.entity.TaskEntity;
import exception.DaoException;
import exception.DateConverterException;
import exception.NotInitializedException;
import util.DateConverter;
import util.TaskConverter;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

public class TaskController {
    private static TaskController instance;
    private TaskConverter taskConverter;
    private TaskDao taskDao;


    private TaskController(TaskDao taskDao) {
        this.taskConverter = new TaskConverter();
        this.taskDao = taskDao;
    }

    public static synchronized TaskController initInstance(TaskDao taskDao) {
        if (instance == null) {
            instance = new TaskController(taskDao);
        }
        return instance;
    }

    public static synchronized TaskController getInstance() throws NotInitializedException {
        if (instance == null) {
            throw new NotInitializedException(
                    "TaskController cannot be returned because it has not yet been initialized");
        }
        return instance;
    }

    public void createTask(int journalId, String name, String description, String date) throws DaoException,
            DateConverterException {
        DateConverter dateConverter = new DateConverter();
        Timestamp timestamp = dateConverter.stringToTimeStamp(date);
        Task task = Task.builder()
                .name(name)
                .description(description)
                .date(timestamp)
                .journalId(journalId)
                .taskStatus(Status.pending)
                .build();
        TaskEntity taskEntity = taskConverter.convertDoMainToEntity(task);
        taskDao.create(taskEntity);
    }

    public void delete(int id) throws DaoException {
        taskDao.delete(id);
    }

    public void update(int taskId, int journalId, String name, String description, String date) throws DaoException,
            DateConverterException {
        DateConverter dateConverter = new DateConverter();
        Timestamp timestamp = dateConverter.stringToTimeStamp(date);
        Task task = Task.builder()
                .id(taskId)
                .name(name)
                .description(description)
                .date(timestamp)
                .journalId(journalId)
                .taskStatus(Status.pending)
                .journalId(journalId)
                .build();
        TaskEntity taskEntity = taskConverter.convertDoMainToEntity(task);
        taskDao.update(taskEntity);
    }

    public Task get(int id) throws DaoException {
        TaskEntity taskEntity = (TaskEntity) taskDao.get(id);
        return taskConverter.convertEntityToDoMain(taskEntity);
    }

    public List<Task> getAll() throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getAll();
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < taskEntity.size(); i++) {
            Task task = taskConverter.convertEntityToDoMain(taskEntity.get(i));
            taskList.add(task);
        }
        return taskList;
    }

    public List<Task> getTasksByJournalId(int journalId) throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getTasksByJournalId(journalId);
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < taskEntity.size(); i++) {
            Task task = taskConverter.convertEntityToDoMain(taskEntity.get(i));
            taskList.add(task);
        }
        return taskList;
    }

    public List<Task> getTasksByJournalIdNameAsc(int journalId) throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getTasksByJournalIdNameAsc(journalId);
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < taskEntity.size(); i++) {
            Task task = taskConverter.convertEntityToDoMain(taskEntity.get(i));
            taskList.add(task);
        }
        return taskList;
    }

    public List<Task> getTasksByJournalIdNameDesc(int journalId) throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getTasksByJournalIdNameDesc(journalId);
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < taskEntity.size(); i++) {
            Task task = taskConverter.convertEntityToDoMain(taskEntity.get(i));
            taskList.add(task);
        }
        return taskList;
    }

    public List<Task> getTasksByJournalIdDescriptionAsc(int journalId) throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getTasksByJournalIdDescriptionAsc(journalId);
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdDescriptionDesc(int journalId) throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getTasksByJournalIdDescriptionDesc(journalId);
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdDateAsc(int journalId) throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getTasksByJournalIdDateAsc(journalId);
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdDateDesc(int journalId) throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getTasksByJournalIdDateDesc(journalId);
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdStatusAsc(int journalId) throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getTasksByJournalIdStatusAsc(journalId);
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdStatusDesc(int journalId) throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getTasksByJournalIdStatusDesc(journalId);
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdFilterByName(int journalId, String value) throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getTasksByJournalIdFilterByName(journalId, value);
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdFilterByDescription(int journalId, String value) throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getTasksByJournalIdFilterByDescription(journalId, value);
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdFilterByDate(int journalId, String value) throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getTasksByJournalIdFilterByDate(journalId, value);
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdFilterByStatus(int journalId, String value) throws DaoException {
        List<TaskEntity> taskEntity = taskDao.getTasksByJournalIdFilterByStatus(journalId, value);
        return getTaskList(taskEntity);
    }

    public List<Task> getTaskList(List<TaskEntity> taskEntity) {
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < taskEntity.size(); i++) {
            Task task = taskConverter.convertEntityToDoMain(taskEntity.get(i));
            taskList.add(task);
        }
        return taskList;
    }


}
