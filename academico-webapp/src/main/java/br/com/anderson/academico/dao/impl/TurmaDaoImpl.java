package br.com.anderson.academico.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.anderson.academico.dao.TurmaDao;
import br.com.anderson.academico.modelo.Turma;
import br.com.anderson.academico.util.JpaUtil;

public class TurmaDaoImpl implements TurmaDao {

	private EntityManager manager;

	public TurmaDaoImpl() {
		this.manager = JpaUtil.getEntityManager();
	}

	@Override
	public void save(Turma objeto) {
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
	public void update(Turma objeto) {
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
	public void delete(Turma objeto) {
		try {
			manager.getTransaction().begin();
			objeto = manager.merge(objeto);
			manager.remove(objeto);
			manager.flush();
			manager.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
			manager.getTransaction().rollback();
		} finally {
			manager.close();
		}

	}

	@Override
	public Turma getById(Long id) {
		Turma turma = manager.find(Turma.class, id);
		manager.close();
		return turma;
	}

	@Override
	public List<Turma> list() {
		TypedQuery<Turma> query = manager.createQuery("from Turma", Turma.class);
		List<Turma> turmas = query.getResultList();
		manager.close();
		return turmas;
	}
	
}
