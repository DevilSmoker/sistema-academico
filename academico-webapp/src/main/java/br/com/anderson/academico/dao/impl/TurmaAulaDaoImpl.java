package br.com.anderson.academico.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.anderson.academico.dao.TurmaAulaDao;
import br.com.anderson.academico.modelo.TurmaAula;
import br.com.anderson.academico.util.JpaUtil;

public class TurmaAulaDaoImpl implements TurmaAulaDao {

	private EntityManager manager;

	public TurmaAulaDaoImpl() {
		this.manager = JpaUtil.getEntityManager();
	}

	@Override
	public void save(TurmaAula objeto) {
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
	public void update(TurmaAula objeto) {
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
	public void delete(TurmaAula objeto) {
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
	public TurmaAula getById(Long id) {
		TurmaAula turmaAula = manager.find(TurmaAula.class, id);
		manager.close();
		return turmaAula;
	}

	@Override
	public List<TurmaAula> list() {
		TypedQuery<TurmaAula> query = manager.createQuery("from TurmaAula", TurmaAula.class);
		List<TurmaAula> turmaAulas = query.getResultList();
		manager.close();
		return turmaAulas;
	}
	
}
