package dao;

import exception.DaoException;

import java.util.List;

public interface ObjectDao<T> {
    void create(T object) throws DaoException;

    void delete(int id) throws DaoException;

    void update(T object) throws DaoException;

    T get(int id) throws DaoException;

    List<T> getAll() throws DaoException;



}
