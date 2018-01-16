package br.com.prodemge.keep.db;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;
import javax.persistence.TypedQuery;

public class DAO {
	
	public static <T> T save(T entity) {
		
		EntityManager session = JpaUtil.getEntityManager();
		EntityTransaction tx = session.getTransaction();
		tx.begin();
		session.persist(entity);
		tx.commit();
		session.close();
		
		return entity;
		
	}

	public static <T> T update(T entity) {
		
		EntityManager session = JpaUtil.getEntityManager();
		EntityTransaction tx = session.getTransaction();
		tx.begin();
		session.merge(entity);
		tx.commit();
		session.close();
		
		return entity;
	}
	
	public static <T> List<T> getAll(Class<T> cls) {
		
		EntityManager session = JpaUtil.getEntityManager();
		
		TypedQuery<T> query = session.createQuery("from " + cls.getSimpleName(), cls);
		return query.getResultList();
		
	}

}
