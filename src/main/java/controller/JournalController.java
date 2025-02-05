package controller;

import dao.JournalDao;
import domain.Journal;
import domain.entity.JournalEntity;
import exception.DaoException;
import exception.NotInitializedException;
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

    public static synchronized JournalController getInstance() throws NotInitializedException {
        if (instance == null) {
            throw new NotInitializedException(
                    "JournalController cannot be returned because it has not yet been initialized");
        }
        return instance;
    }

    public void create(String name, String description) throws DaoException {

        Date date = new Date();
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        Journal journal = Journal.builder()
                .name(name)
                .description(description)
                .date(dateSql)
                .build();
        JournalEntity journalEntity = journalConverter.convertDoMainToEntity(journal);
        journalDao.create(journalEntity);

    }

    public void delete(int id) throws DaoException {
        journalDao.delete(id);
    }

    public void update(int journalId, String name, String description) throws DaoException {
        Date date = new Date();
        java.sql.Date dateSql = new java.sql.Date(date.getTime());
        Journal journal = Journal.builder()
                .id(journalId)
                .name(name)
                .description(description)
                .date(dateSql)
                .build();
        JournalEntity journalEntity = journalConverter.convertDoMainToEntity(journal);
        journalDao.update(journalEntity);
    }

    public Journal get(int id) throws DaoException {
        JournalEntity journalEntity = (JournalEntity) journalDao.get(id);
        return journalConverter.convertEntityToDoMain(journalEntity);
    }

    public List<Journal> getAll() throws DaoException {
        List<JournalEntity> journalEntityList = journalDao.getAll();
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllNameAsc() throws DaoException {
        List<JournalEntity> journalEntityList = journalDao.getAllNameAsc();
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllNameDesc() throws DaoException {
        List<JournalEntity> journalEntityList = journalDao.getAllNameDesc();
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }


    public List<Journal> getAllDescriptionAsc() throws DaoException {
        List<JournalEntity> journalEntityList = journalDao.getAllDescriptionAsc();
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllDescriptionDesc() throws DaoException {
        List<JournalEntity> journalEntityList = journalDao.getAllDescriptionDesc();
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllDateAsc() throws DaoException {
        List<JournalEntity> journalEntityList = journalDao.getAllDateAsc();
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllDateDesc() throws DaoException {
        List<JournalEntity> journalEntityList = journalDao.getAllDateDesc();
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllFilterByDescription(String value) throws DaoException {
        List<JournalEntity> journalEntityList = journalDao.getAllFilterByDescription(value);
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllFilterByDate(String value) throws DaoException {
        List<JournalEntity> journalEntityList = journalDao.getAllFilterByDate(value);
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }

    public List<Journal> getAllFilterByName(String value) throws DaoException {
        List<JournalEntity> journalEntityList = journalDao.getAllFilterByName(value);
        List<Journal> journalList = new ArrayList<>();
        for (int i = 0; i < journalEntityList.size(); i++) {
            Journal journal = journalConverter.convertEntityToDoMain(journalEntityList.get(i));
            journalList.add(journal);
        }
        return journalList;
    }


}
