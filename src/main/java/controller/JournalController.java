package controller;

import dao.JournalDao;
import domain.Journal;
import domain.entity.JournalEntity;
import exception.ControllerException;
import exception.DaoException;
import util.JournalConverter;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class JournalController {
    private static JournalController instance;
    private JournalConverter journalConverter;
    private JournalDao journalDao;

    private JournalController(JournalDao journalDao) {
        this.journalConverter = new JournalConverter();
        this.journalDao = journalDao;
    }

    public static synchronized JournalController initInstance(JournalDao journalDao) {
        if (instance == null) {
            instance = new JournalController(journalDao);
        }
        return instance;
    }

    public static synchronized JournalController getInstance() throws ControllerException {
        if (instance == null) {
            throw new ControllerException(
                    "JournalController cannot be returned because it has not yet been initialized");
        }
        return instance;
    }

    public void create(String name, String description) throws ControllerException {

        Date date = new Date();
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        Journal journal = Journal.builder()
                .name(name)
                .description(description)
                .date(dateSql)
                .build();
        JournalEntity journalEntity = journalConverter.convertDoMainToEntity(journal);
        try {
            journalDao.create(journalEntity);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot create journal");
        }

    }

    public void createJournalEntity(JournalEntity journalEntity) throws ControllerException {
        try {
            journalDao.create(journalEntity);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot create journal");
        }
    }


    public void delete(int id) throws ControllerException {
        try {
            journalDao.delete(id);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot delete journal");
        }
    }

    public void update(int journalId, String name, String description) throws ControllerException {
        Date date = new Date();
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        Journal journal = Journal.builder()
                .id(journalId)
                .name(name)
                .description(description)
                .date(dateSql)
                .build();
        JournalEntity journalEntity = journalConverter.convertDoMainToEntity(journal);
        try {
            journalDao.update(journalEntity);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot update journal");
        }
    }

    public void updateEntity(JournalEntity journalEntity) throws ControllerException {
        try {
            journalDao.update(journalEntity);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot update journal");
        }
    }

    public Journal get(int id) throws ControllerException {
        JournalEntity journalEntity = null;
        try {
            journalEntity = (JournalEntity) journalDao.get(id);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get journal");
        }
        return journalConverter.convertEntityToDoMain(journalEntity);
    }

    public List<JournalEntity> getJournalEntity(int id) throws ControllerException {
        List<JournalEntity> journalList = new ArrayList<>();
        try {
            journalList.add(journalDao.get(id));
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get journal");
        }
        return journalList;
    }

    public List<JournalEntity> getJournalEntityArray(int[] id) throws ControllerException {
        List<JournalEntity> journalList = new ArrayList<>();
        for (int i = 0; i < id.length; i++) {
            try {
                journalList.add(journalDao.get(id[i]));
            } catch (DaoException e) {
                System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                        new java.util.Date() + ". Message: " + e);
                throw new ControllerException("Cannot get journal");
            }
        }

        return journalList;
    }

    public List<Journal> getAll() throws ControllerException {
        List<JournalEntity> journalEntityList = null;
        try {
            journalEntityList = journalDao.getAll();
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list journals");
        }
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<JournalEntity> getAllEntity() throws ControllerException {
        try {
            return journalDao.getAll();
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list journals");
        }
    }

    public List<Journal> getAllNameAsc() throws ControllerException {
        List<JournalEntity> journalEntityList = null;
        try {
            journalEntityList = journalDao.getAllNameAsc();
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list journals");
        }
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllNameDesc() throws ControllerException {
        List<JournalEntity> journalEntityList = null;
        try {
            journalEntityList = journalDao.getAllNameDesc();
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list journals");
        }
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }


    public List<Journal> getAllDescriptionAsc() throws ControllerException {
        List<JournalEntity> journalEntityList = null;
        try {
            journalEntityList = journalDao.getAllDescriptionAsc();
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list journals");
        }
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllDescriptionDesc() throws ControllerException {
        List<JournalEntity> journalEntityList = null;
        try {
            journalEntityList = journalDao.getAllDescriptionDesc();
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list journals");
        }
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllDateAsc() throws ControllerException {
        List<JournalEntity> journalEntityList = null;
        try {
            journalEntityList = journalDao.getAllDateAsc();
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list journals");
        }
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllDateDesc() throws ControllerException {
        List<JournalEntity> journalEntityList = null;
        try {
            journalEntityList = journalDao.getAllDateDesc();
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list journals");
        }
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllFilterByDescription(String value) throws ControllerException {
        List<JournalEntity> journalEntityList = null;
        try {
            journalEntityList = journalDao.getAllFilterByDescription(value);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list journals");
        }
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllFilterByDate(String value) throws ControllerException {
        List<JournalEntity> journalEntityList = null;
        try {
            journalEntityList = journalDao.getAllFilterByDate(value);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list journals");
        }
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllFilterByName(String value) throws ControllerException {
        List<JournalEntity> journalEntityList = null;
        try {
            journalEntityList = journalDao.getAllFilterByName(value);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot get list journals");
        }
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public boolean checkJournal(JournalEntity journalEntity) throws ControllerException {
        try {
            return journalDao.checkJournal(journalEntity);
        } catch (DaoException e) {
            System.out.println("Error! Class: " + getClass().getName() + ". Date: " +
                    new java.util.Date() + ". Message: " + e);
            throw new ControllerException("Cannot check journal");
        }
    }


}
