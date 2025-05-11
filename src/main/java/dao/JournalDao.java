package dao;

import domain.entity.JournalEntity;
import exception.DaoException;

import java.util.List;

public interface JournalDao extends ObjectDao<JournalEntity> {
    List<JournalEntity> getAllNameAsc() throws DaoException;

    List<JournalEntity> getAllNameDesc() throws DaoException;

    List<JournalEntity> getAllDescriptionAsc() throws DaoException;

    List<JournalEntity> getAllDescriptionDesc() throws DaoException;

    List<JournalEntity> getAllDateAsc() throws DaoException;

    List<JournalEntity> getAllDateDesc() throws DaoException;

    List<JournalEntity> getAllFilterByDescription(String value) throws DaoException;

    List<JournalEntity> getAllFilterByDate(String value) throws DaoException;

    List<JournalEntity> getAllFilterByName(String value) throws DaoException;

    Boolean checkJournal(JournalEntity journal) throws DaoException;
}
