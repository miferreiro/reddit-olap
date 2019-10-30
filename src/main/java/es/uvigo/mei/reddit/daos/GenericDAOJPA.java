package es.uvigo.mei.reddit.daos;

import java.util.Collections;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;

public class GenericDAOJPA<T, K> implements GenericDAO<T, K> {
	protected EntityManager em;
    protected Class<T> typeEntity;
    
    public GenericDAOJPA(EntityManager em, Class<T> typeEntity) {
        this.em = em;
        this.typeEntity = typeEntity;
    }

    @Override
    public T create(T entity) throws RedditException {
        T result = null;
        EntityTransaction tx = this.em.getTransaction();
        try {
            tx.begin();
            em.persist(entity);
            result = entity;
            tx.commit();
        } catch (Exception e) {
            if ((tx != null) && (tx.isActive())) {
                tx.rollback();
            }
            throw new RedditException("Exception create entity", e);
        }
        return result;
    }

    @Override
    public T refresh(T entity) throws RedditException {
        T result = null;
        EntityTransaction tx = this.em.getTransaction();
        try {
            tx.begin();
            result = em.merge(entity);
            tx.commit();
        } catch (Exception e) {
            if ((tx != null) && (tx.isActive())) {
                tx.rollback();
            }
            throw new RedditException("Exception refresh entity", e);
        }
        return result;
    }

    @Override
    public void remove(T entity) throws RedditException {
        EntityTransaction tx = this.em.getTransaction();
        try {
            tx.begin();
            em.remove(em.merge(entity));
            tx.commit();
        } catch (RuntimeException e) {
            if ((tx != null) && (tx.isActive())) {
                tx.rollback();
            }
            throw new RedditException("Exception remove entity", e);
        }
    }

    @Override
    public T searchByKey(K key) throws RedditException {
        T result = null;
        EntityTransaction tx = this.em.getTransaction();
        try {
            tx.begin();
            result = em.find(typeEntity, key);
            tx.commit();
        } catch (Exception e) {
            if ((tx != null) && (tx.isActive())) {
                tx.rollback();
            }
            throw new RedditException("Exception search by key", e);
        }
        return result;
    }

    @Override
    public List<T> searchAll() {
        List<T> result = Collections.emptyList();
        EntityTransaction tx = this.em.getTransaction();
        try {
            tx.begin();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<T> query = builder.createQuery(this.typeEntity);
            query.select(query.from(this.typeEntity));
            result = em.createQuery(query).getResultList();

            tx.commit();
        } catch (RuntimeException rte) {
            if ((tx != null) && (tx.isActive())) {
                tx.rollback();
            }
        }
        return result;
    }

    @Override
    public Long countAll() throws RedditException {
        Long result = null;
        EntityTransaction tx = this.em.getTransaction();
        try {
            tx.begin();

            CriteriaBuilder builder = em.getCriteriaBuilder();
            CriteriaQuery<Long> query = builder.createQuery(Long.class);
            query.select(builder.count(query.from(this.typeEntity)));
            result = em.createQuery(query).getSingleResult();

            tx.commit();
        } catch (Exception e) {
            if ((tx != null) && (tx.isActive())) {
                tx.rollback();
            }
            throw new RedditException("Exception search all", e);
        }
        return result;
    }
    
}
