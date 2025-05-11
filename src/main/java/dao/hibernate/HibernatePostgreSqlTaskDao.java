package dao.hibernate;

import dao.ObjectDao;
import dao.TaskDao;
import domain.entity.TaskEntity;
import exception.DaoException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.query.Query;

import java.util.ArrayList;
import java.util.List;

public class HibernatePostgreSqlTaskDao implements ObjectDao<TaskEntity>, TaskDao {
    private SessionFactory sessionFactory;

    public HibernatePostgreSqlTaskDao(SessionFactory sessionFactory) {
        this.sessionFactory = sessionFactory;
    }

    @Override
    public void create(TaskEntity object) throws DaoException {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            TaskEntity taskEntity = session.get(TaskEntity.class, object.getId());
            if (taskEntity != null) {
                throw new DaoException("A task with that id already exists");
            } else {
                session.persist(object);
            }

            transaction.commit();
        }
    }

    @Override
    public void delete(int id)  {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            TaskEntity taskEntity = session.get(TaskEntity.class, id);
            session.remove(taskEntity);
            session.flush();
            transaction.commit();
        }
    }

    @Override
    public void update(TaskEntity object)  {
        try (Session session = sessionFactory.openSession()) {
            Transaction transaction = session.beginTransaction();
            session.merge(object);
            transaction.commit();
        }
    }

    @Override
    public TaskEntity get(int id) {
        try (Session session = sessionFactory.openSession()) {
            TaskEntity taskEntity = session.get(TaskEntity.class, id);
            return taskEntity;
        }
    }

    @Override
    public List<TaskEntity> getAll() {

        try (Session session = sessionFactory.openSession()) {
            Query<TaskEntity> query = session.createQuery("from TaskEntity", TaskEntity.class);
            return query.list();
        }

    }

    @Override
    public List<TaskEntity> getTasksByJournalId(int journalId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = "from TaskEntity where journalId=" + journalId;
            Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
            return query.list();
        }
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdArray(int[] journalId)  {
        List<TaskEntity> taskEntityList = new ArrayList<>();
        try (Session session = sessionFactory.openSession()) {

            for (int i = 0; i < journalId.length; i++) {
                String hql = "from TaskEntity where journalId=" + journalId[i];
                Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
                List<TaskEntity> list = query.list();
                for (TaskEntity task : list
                ) {
                    taskEntityList.add(task);
                }
            }

            return taskEntityList;
        }

    }

    @Override
    public List<TaskEntity> getTasksByJournalIdNameAsc(int journalId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from TaskEntity where journalId='%s' order by name asc", journalId);
            Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
            return query.list();
        }
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdNameDesc(int journalId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from TaskEntity where journalId='%s' order by name desc", journalId);
            Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
            return query.list();
        }
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdDescriptionAsc(int journalId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from TaskEntity where journalId='%s' order by description asc", journalId);
            Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
            return query.list();
        }
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdDescriptionDesc(int journalId) {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from TaskEntity where journalId='%s' order by description desc", journalId);
            Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
            return query.list();
        }
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdDateAsc(int journalId)  {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from TaskEntity where journalId='%s' order by date asc", journalId);
            Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
            return query.list();
        }
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdDateDesc(int journalId)  {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from TaskEntity where journalId='%s' order by date desc", journalId);
            Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
            return query.list();
        }
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdStatusAsc(int journalId)  {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from TaskEntity where journalId='%s' order by status asc", journalId);
            Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
            return query.list();
        }
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdStatusDesc(int journalId)  {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from TaskEntity where journalId='%s' order by status desc", journalId);
            Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
            return query.list();
        }
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdFilterByName(int journalId, String value) {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from TaskEntity where journalId='%s' and name like '%%%s%%'", journalId, value);
            Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
            return query.list();
        }
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdFilterByDescription(int journalId, String value) {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format
                    ("from TaskEntity where journalId='%s' and description like '%%%s%%'", journalId, value);
            Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
            return query.list();
        }
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdFilterByDate(int journalId, String value)  {
        try (Session session = sessionFactory.openSession()) {
            String hql = String.format("from TaskEntity where journalId='%s' and date= '%s'", journalId, value);
            Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
            return query.list();
        }
    }

    @Override
    public List<TaskEntity> getTasksByJournalIdFilterByStatus(int journalId, String value)  {
        try (Session session = sessionFactory.openSession()) {
            String hql =
                    String.format("from TaskEntity where journalId='%s' and status like '%%%s%%'", journalId, value);
            Query<TaskEntity> query = session.createQuery(hql, TaskEntity.class);
            return query.list();
        }
    }

    @Override
    public Boolean checkTask(TaskEntity taskEntity)  {
        boolean exist = false;
        try (Session session = sessionFactory.openSession()) {
            TaskEntity task = session.get(TaskEntity.class, taskEntity.getId());
            if (task != null) {
                exist = true;
            } else {
                exist = false;
            }
            return exist;
        }
    }
}
