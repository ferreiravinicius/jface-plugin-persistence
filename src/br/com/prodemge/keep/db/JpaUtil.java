package br.com.prodemge.keep.db;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	
	private static final EntityManagerFactory factory = Persistence
			.createEntityManagerFactory("keep.jpa");

	public static EntityManager getEntityManager() {
		return factory.createEntityManager();
	}
	
	public static EntityManagerFactory getFactory() {
		return factory;
	}

	public static void close() {
		factory.close();
	}

}
