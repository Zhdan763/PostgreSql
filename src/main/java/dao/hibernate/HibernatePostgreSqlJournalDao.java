package dao.hibernate;

import dao.JournalDao;
import dao.ObjectDao;
import domain.entity.JournalEntity;
import exception.DaoException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.List;

public class HibernatePostgreSqlJournalDao implements ObjectDao<JournalEntity>, JournalDao {
    private SessionFactory sessionFactory;

    public HibernatePostgreSqlJournalDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(JournalEntity object) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            JournalEntity journal = session.get(JournalEntity.class, object.getId());
            if(journal!=null) {
                throw new DaoException("A journal with that id already exists");
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
            JournalEntity journalEntity = session.get(JournalEntity.class, id);
            session.remove(journalEntity);
            session.flush();
            transaction.commit();
        }
    }

    @Override
    public void update(JournalEntity object) {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(object);
            transaction.commit();
        }
    }

    @Override
    public JournalEntity get(int id) {

        try (Session session = sessionFactory.openSession()) {
            JournalEntity journal = session.get(JournalEntity.class, id);
            return journal;
        }

    }

    @Override
    public List<JournalEntity> getAll() {
        try (Session session = sessionFactory.openSession()) {
            Query<JournalEntity> query = session.createQuery("from JournalEntity", JournalEntity.class);
            return query.list();
        }
    }

    @Override
    public List<JournalEntity> getAllNameAsc() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from JournalEntity order by name asc";
            Query<JournalEntity> query = session.createQuery(hql, JournalEntity.class);
            return query.list();
        }
    }

    @Override
    public List<JournalEntity> getAllNameDesc() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from JournalEntity order by name desc";
            Query<JournalEntity> query = session.createQuery(hql, JournalEntity.class);
            return query.list();
        }
    }

    @Override
    public List<JournalEntity> getAllDescriptionAsc()  {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from JournalEntity order by description asc";
            Query<JournalEntity> query = session.createQuery(hql, JournalEntity.class);
            return query.list();
        }
    }

    @Override
    public List<JournalEntity> getAllDescriptionDesc()  {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from JournalEntity order by description desc";
            Query<JournalEntity> query = session.createQuery(hql, JournalEntity.class);
            return query.list();
        }
    }

    @Override
    public List<JournalEntity> getAllDateAsc() {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from JournalEntity order by date asc";
            Query<JournalEntity> query = session.createQuery(hql, JournalEntity.class);
            return query.list();
        }
    }

    @Override
    public List<JournalEntity> getAllDateDesc()  {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from JournalEntity order by date desc";
            Query<JournalEntity> query = session.createQuery(hql, JournalEntity.class);
            return query.list();
        }
    }

    @Override
    public List<JournalEntity> getAllFilterByDescription(String value) {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from JournalEntity where description like '%%%s%%'", value);
            Query<JournalEntity> query = session.createQuery(hql, JournalEntity.class);
            return query.list();
        }
    }

    @Override
    public List<JournalEntity> getAllFilterByDate(String value) {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from JournalEntity where date = '%s'", value);
            Query<JournalEntity> query = session.createQuery(hql, JournalEntity.class);
            return query.list();
        }
    }

    @Override
    public List<JournalEntity> getAllFilterByName(String value) {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from JournalEntity where name like '%%%s%%'", value);
            Query<JournalEntity> query = session.createQuery(hql, JournalEntity.class);
            return query.list();
        }
    }

    @Override
    public Boolean checkJournal(JournalEntity journal) {
        boolean exist = false;
        try (Session session = sessionFactory.openSession()) {
            JournalEntity journalEntity = session.get(JournalEntity.class, journal.getId());
            if (journalEntity != null) {
                exist = true;
            } else {
                exist = false;
            }
            return exist;
        }

    }
}
