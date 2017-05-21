package com.normativeanimal.social.persistence;

import java.util.List;
import java.util.Optional;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import com.normativeanimal.social.persistence.bean.TweetBean;

public class DatabaseConnectionManager {
	private static final EntityManagerFactory SESSION_FACTORY;

	static {
		SESSION_FACTORY = Persistence.createEntityManagerFactory("com.normativeanimal.social.persistence");
	}

	public void persist(TweetBean bean) {
		final EntityManager entityManager = beginTransaction();
		entityManager.merge(bean);
		cleanUp(entityManager);
	}

	public List<TweetBean> fetchAll() {
		final EntityManager entityManager = beginTransaction();
		final List<TweetBean> beans = entityManager.createQuery("FROM TweetBean", TweetBean.class).getResultList();
		cleanUp(entityManager);
		return beans;
	}

	public Optional<TweetBean> getById(Long id) {
		final EntityManager entityManager = beginTransaction();
		final List<TweetBean> beans = entityManager.createQuery("FROM TweetBean tb WHERE tb.id = :id", TweetBean.class)
		        .setParameter("id", id)
		        .getResultList();
		cleanUp(entityManager);
		if (beans.isEmpty()) {
			return Optional.empty();
		} else {
			return Optional.of(beans.get(0));
		}
	}

	private EntityManager beginTransaction() {
		final EntityManager entityManager = SESSION_FACTORY.createEntityManager();
		entityManager.getTransaction().begin();
		return entityManager;
	}

	private void cleanUp(final EntityManager entityManager) {
		entityManager.getTransaction().commit();
		entityManager.close();
	}

}
