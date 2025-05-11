package controller;

import dao.TaskDao;
import domain.Status;
import domain.Task;
import domain.entity.TaskEntity;
import exception.ControllerException;
import exception.DaoException;
import exception.DateConverterException;
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

    public static synchronized TaskController getInstance() throws ControllerException {
        if (instance == null) {
            throw new ControllerException(
                    "TaskController cannot be returned because it has not yet been initialized");
        }
        return instance;
    }

    public void createTask(int journalId, String name, String description, String date) throws
            ControllerException {
        DateConverter dateConverter = new DateConverter();
        Timestamp timestamp = null;
        try {
            timestamp = dateConverter.stringToTimeStamp(date);
        } catch (DateConverterException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot convert date");
        }
        Task task = Task.builder()
                .name(name)
                .description(description)
                .date(timestamp)
                .journalId(journalId)
                .taskStatus(Status.pending)
                .build();
        TaskEntity taskEntity = taskConverter.convertDoMainToEntity(task);
        try {
            taskDao.create(taskEntity);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot create task");
        }
    }

    public void createTaskEntity(TaskEntity taskEntity) throws ControllerException {
        try {
            taskDao.create(taskEntity);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot create task");
        }
    }

    public void delete(int id) throws ControllerException {
        try {
            taskDao.delete(id);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot delete task");
        }
    }

    public void update(int taskId, int journalId, String name, String description, String date) throws
            ControllerException {
        DateConverter dateConverter = new DateConverter();
        Timestamp timestamp = null;
        try {
            timestamp = dateConverter.stringToTimeStamp(date);
        } catch (DateConverterException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot convert date");
        }
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
        try {
            taskDao.update(taskEntity);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot update task");
        }
    }

    public void updateEntity(TaskEntity taskEntity) throws ControllerException {
        try {
            taskDao.update(taskEntity);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot update task");
        }
    }

    public Task get(int id) throws ControllerException {
        TaskEntity taskEntity = null;
        try {
            taskEntity = (TaskEntity) taskDao.get(id);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get task");
        }
        return taskConverter.convertEntityToDoMain(taskEntity);
    }

    public List<TaskEntity> getTaskEntityList(int id) throws ControllerException {
        List<TaskEntity> taskEntityList = new ArrayList<>();
        try {
            taskEntityList.add(taskDao.get(id));
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        return taskEntityList;
    }


    public List<TaskEntity> getTaskEntityListArray(int[] id) throws ControllerException {
        List<TaskEntity> taskEntityList = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            try {
                taskEntityList.add(taskDao.get(id[i]));
            } catch (DaoException e) {
                System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + e);
                throw new ControllerException("Cannot get list tasks");
            }
        }

        return taskEntityList;
    }




    public List<Task> getAll() throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getAll();
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < taskEntity.size(); i++) {
            Task task = taskConverter.convertEntityToDoMain(taskEntity.get(i));
            taskList.add(task);
        }
        return taskList;
    }

    public List<Task> getTasksByJournalId(int journalId) throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getTasksByJournalId(journalId);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < taskEntity.size(); i++) {
            Task task = taskConverter.convertEntityToDoMain(taskEntity.get(i));
            taskList.add(task);
        }
        return taskList;
    }


    public List<TaskEntity> getTasksEntityByJournalId(int[] journalId) throws ControllerException {

        try {
            return taskDao.getTasksByJournalIdArray(journalId);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get tasks");
        }
    }

    public int getJournalId(int taskId) throws ControllerException {
        TaskEntity taskEntity = null;
        try {
            taskEntity = taskDao.get(taskId);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get journal id");
        }
        return (int) taskEntity.getJournalId();
    }

    public List<Task> getTasksByJournalIdNameAsc(int journalId) throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getTasksByJournalIdNameAsc(journalId);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < taskEntity.size(); i++) {
            Task task = taskConverter.convertEntityToDoMain(taskEntity.get(i));
            taskList.add(task);
        }
        return taskList;
    }

    public List<Task> getTasksByJournalIdNameDesc(int journalId) throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getTasksByJournalIdNameDesc(journalId);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        List<Task> taskList = new ArrayList<>();
        for (int i = 0; i < taskEntity.size(); i++) {
            Task task = taskConverter.convertEntityToDoMain(taskEntity.get(i));
            taskList.add(task);
        }
        return taskList;
    }

    public List<Task> getTasksByJournalIdDescriptionAsc(int journalId) throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getTasksByJournalIdDescriptionAsc(journalId);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdDescriptionDesc(int journalId) throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getTasksByJournalIdDescriptionDesc(journalId);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdDateAsc(int journalId) throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getTasksByJournalIdDateAsc(journalId);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdDateDesc(int journalId) throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getTasksByJournalIdDateDesc(journalId);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdStatusAsc(int journalId) throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getTasksByJournalIdStatusAsc(journalId);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdStatusDesc(int journalId) throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getTasksByJournalIdStatusDesc(journalId);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdFilterByName(int journalId, String value) throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getTasksByJournalIdFilterByName(journalId, value);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdFilterByDescription(int journalId, String value) throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getTasksByJournalIdFilterByDescription(journalId, value);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdFilterByDate(int journalId, String value) throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getTasksByJournalIdFilterByDate(journalId, value);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
        return getTaskList(taskEntity);
    }

    public List<Task> getTasksByJournalIdFilterByStatus(int journalId, String value) throws ControllerException {
        List<TaskEntity> taskEntity = null;
        try {
            taskEntity = taskDao.getTasksByJournalIdFilterByStatus(journalId, value);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list tasks");
        }
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

    public boolean checkTask(TaskEntity taskEntity) throws ControllerException {
        try {
            return taskDao.checkTask(taskEntity);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot check task");
        }
    }


}
