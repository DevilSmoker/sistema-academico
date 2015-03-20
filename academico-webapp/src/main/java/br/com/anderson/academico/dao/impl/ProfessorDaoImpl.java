package br.com.anderson.academico.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.anderson.academico.dao.ProfessorDao;
import br.com.anderson.academico.modelo.Professor;
import br.com.anderson.academico.util.JpaUtil;

public class ProfessorDaoImpl implements ProfessorDao {

	private EntityManager manager;

	public ProfessorDaoImpl() {
		this.manager = JpaUtil.getEntityManager();
	}

	@Override
	public void save(Professor objeto) {
		try {
			manager.getTransaction().begin();
			manager.persist(objeto);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			manager.close();
		}
	}

	@Override
	public void update(Professor objeto) {
		try {
			manager.getTransaction().begin();
			manager.merge(objeto);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			manager.close();
		}
	}

	@Override
	public void delete(Professor objeto) {
		try {
			manager.getTransaction().begin();
			objeto = manager.merge(objeto);
			manager.remove(objeto);
			manager.getTransaction().commit();
		} catch (Exception e) {
			manager.getTransaction().rollback();
			e.printStackTrace();
		} finally {
			manager.close();
		}

	}

	@Override
	public Professor getById(Long id) {
		Professor professor = manager.find(Professor.class, id);
		manager.close();
		return professor;
	}

	@Override
	public List<Professor> list() {
		TypedQuery<Professor> query = manager.createQuery("from Professor", Professor.class);
		List<Professor> professores = query.getResultList();
		manager.close();
		return professores;
	}
	
}
