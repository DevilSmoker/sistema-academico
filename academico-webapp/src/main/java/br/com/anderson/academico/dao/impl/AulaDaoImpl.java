package br.com.anderson.academico.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.anderson.academico.dao.AulaDao;
import br.com.anderson.academico.modelo.Aula;
import br.com.anderson.academico.util.JpaUtil;

public class AulaDaoImpl implements AulaDao {

	private EntityManager manager;

	public AulaDaoImpl() {
		this.manager = JpaUtil.getEntityManager();
	}

	@Override
	public void save(Aula objeto) {
		try {
			manager.getTransaction().begin();
			manager.persist(objeto);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	@Override
	public void update(Aula objeto) {
		try {
			manager.getTransaction().begin();
			manager.merge(objeto);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}
	}

	@Override
	public void delete(Aula objeto) {
		try {
			manager.getTransaction().begin();
			objeto = manager.merge(objeto);
			manager.remove(objeto);
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}

	}

	@Override
	public Aula getById(Long id) {
		Aula professorTurma = manager.find(Aula.class, id);
		manager.close();
		return professorTurma;
	}

	@Override
	public List<Aula> list() {
		TypedQuery<Aula> query = manager.createQuery("from ProfessorTurma", Aula.class);
		List<Aula> professorTurmas = query.getResultList();
		manager.close();
		return professorTurmas;
	}
	
}
