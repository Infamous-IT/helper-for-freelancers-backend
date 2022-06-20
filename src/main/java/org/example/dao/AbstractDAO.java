package org.example.dao;

import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;

import java.lang.reflect.ParameterizedType;
import java.util.List;
import java.util.UUID;

public abstract class AbstractDAO<T> implements BaseDAO<T>{

    @Autowired
    private SessionFactory sessionFactory;

    public SessionFactory getSessionFactory() {
        return sessionFactory;
    }

    public Class<T> genericClass() {
        return  (Class<T>) ((ParameterizedType) getClass()
                .getGenericSuperclass()).getActualTypeArguments()[0];
    }

    @Override
    public List<T> getAll() {
        Session session = getSessionFactory().getCurrentSession();
        Query<T> query = session.createQuery("from " + genericClass().getName(), genericClass());
        return query.getResultList();
    }

//    @Override
//    public Page<T> getAll() {
//        Session session = getSessionFactory().getCurrentSession();
//        Query<T> query = session.createQuery("from " + genericClass().getName(), genericClass());
//        return query.getResultList();
//    }

    @Override
    public T update(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.update(t);
        return t;
    }

    @Override
    public T create(T t) {
        Session session = sessionFactory.getCurrentSession();
        session.save(t);
        return t;
    }

    @Override
    public T getById(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        T t = session.get(genericClass(), uuid);
        return t;
    }

    @Override
    public void delete(UUID uuid) {
        Session session = sessionFactory.getCurrentSession();
        session.delete(getById(uuid));
    }
}