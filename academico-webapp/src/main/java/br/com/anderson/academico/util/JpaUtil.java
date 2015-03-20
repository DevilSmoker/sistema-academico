package br.com.anderson.academico.util;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

public class JpaUtil {
	
	private static EntityManagerFactory factory;
	
	static {
		factory = Persistence.createEntityManagerFactory("AcademicoPU");
	}
	
	public static EntityManager getEntityManager(){
		return factory.createEntityManager();
	}

}
