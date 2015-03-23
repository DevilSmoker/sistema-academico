package br.com.anderson.academico.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.anderson.academico.dao.ProfessorTurmaDao;
import br.com.anderson.academico.modelo.ProfessorTurma;
import br.com.anderson.academico.util.JpaUtil;

public class ProfessorTurmaDaoImpl implements ProfessorTurmaDao {

	private EntityManager manager;

	public ProfessorTurmaDaoImpl() {
		this.manager = JpaUtil.getEntityManager();
	}

	@Override
	public void save(ProfessorTurma objeto) {
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
	public void update(ProfessorTurma objeto) {
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
	public void delete(ProfessorTurma objeto) {
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
	public ProfessorTurma getById(Long id) {
		ProfessorTurma professorTurma = manager.find(ProfessorTurma.class, id);
		manager.close();
		return professorTurma;
	}

	@Override
	public List<ProfessorTurma> list() {
		TypedQuery<ProfessorTurma> query = manager.createQuery("from ProfessorTurma", ProfessorTurma.class);
		List<ProfessorTurma> professorTurmas = query.getResultList();
		manager.close();
		return professorTurmas;
	}
	
}
