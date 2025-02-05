package dao;

import domain.entity.TaskEntity;
import exception.DaoException;

import java.util.List;

public interface TaskDao extends ObjectDao<TaskEntity> {
    List<TaskEntity> getTasksByJournalId(int journalId) throws DaoException;

    List<TaskEntity> getTasksByJournalIdNameAsc(int journalId) throws DaoException;

    List<TaskEntity> getTasksByJournalIdNameDesc(int journalId) throws DaoException;

    List<TaskEntity> getTasksByJournalIdDescriptionAsc(int journalId) throws DaoException;

    List<TaskEntity> getTasksByJournalIdDescriptionDesc(int journalId) throws DaoException;

    List<TaskEntity> getTasksByJournalIdDateAsc(int journalId) throws DaoException;

    List<TaskEntity> getTasksByJournalIdDateDesc(int journalId) throws DaoException;

    List<TaskEntity> getTasksByJournalIdStatusAsc(int journalId) throws DaoException;

    List<TaskEntity> getTasksByJournalIdStatusDesc(int journalId) throws DaoException;

    List<TaskEntity> getTasksByJournalIdFilterByName(int journalId, String value) throws DaoException;

    List<TaskEntity> getTasksByJournalIdFilterByDescription(int journalId, String value) throws DaoException;

    List<TaskEntity> getTasksByJournalIdFilterByDate(int journalId, String value) throws DaoException;

    List<TaskEntity> getTasksByJournalIdFilterByStatus(int journalId, String value) throws DaoException;





}
