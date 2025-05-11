package dao.hibernate;

import dao.ObjectDao;
import dao.UserDao;
import domain.entity.UserEntity;
import exception.DaoException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernatePostgreSqlUserDao implements ObjectDao<UserEntity>, UserDao {
    private SessionFactory sessionFactory;

    public HibernatePostgreSqlUserDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(UserEntity object) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            UserEntity userEntity = session.get(UserEntity.class, object.getId());
            if (userEntity != null) {
                throw new DaoException("An user with that id already exists");
            } else {
                session.persist(object);
            }
            transaction.commit();
        }
    }

    @Override
    public void delete(int id) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            UserEntity userEntity = session.get(UserEntity.class, id);
            session.remove(userEntity);
            session.flush();
            transaction.commit();
        }
    }

    @Override
    public void update(UserEntity object) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(object);
            transaction.commit();
        }
    }

    @Override
    public UserEntity get(int id) {
        try (Session session = sessionFactory.openSession()) {
            UserEntity userEntity = session.get(UserEntity.class, id);
            return userEntity;
        }
    }

    @Override
    public List<UserEntity> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<UserEntity> query = session.createQuery("from UserEntity", UserEntity.class);
            return query.list();
        }
    }

    @Override
    public String getPassword(String name) {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from UserEntity where login like '%s'", name);
            Query<UserEntity> query = session.createQuery(hql, UserEntity.class);
            List<UserEntity> list = query.list();
            String password = null;
            for (UserEntity user : list
            ) {
                password = user.getPassword();
            }
            return password;
        }
    }

    @Override
    public Boolean checkLogin(String login) {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from UserEntity where login like '%s'", login);
            Query<UserEntity> query = session.createQuery(hql, UserEntity.class);
            List<UserEntity> list = query.list();
            boolean exist;
            if (list.isEmpty()) {
                exist = false;
            } else {
                exist = true;
            }
            return exist;
        }

    }
}
