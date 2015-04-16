package br.com.anderson.academico.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.anderson.academico.dao.DisciplinaDao;
import br.com.anderson.academico.modelo.Disciplina;
import br.com.anderson.academico.modelo.Professor;
import br.com.anderson.academico.util.JpaUtil;

public class DisciplinaDaoImpl implements DisciplinaDao {

	private EntityManager manager;

	public DisciplinaDaoImpl() {
		this.manager = JpaUtil.getEntityManager();
	}

	@Override
	public void save(Disciplina objeto) {
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
	public void update(Disciplina objeto) {
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
	public void delete(Disciplina objeto) {
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
	public Disciplina getById(Long id) {
		Disciplina disciplina = manager.find(Disciplina.class, id);
		manager.close();
		return disciplina;
	}

	@Override
	public List<Disciplina> list() {
		TypedQuery<Disciplina> query = manager.createQuery("from Disciplina", Disciplina.class);
		List<Disciplina> disciplinas = query.getResultList();
		manager.close();
		return disciplinas;
	}
	
	public List<Professor> getProfessores(Long id) {
		TypedQuery<Professor> query = manager.createQuery("SELECT P FROM Disciplina D, Professor P WHERE D.id = P.disciplina AND D.id = :id", Professor.class);
		query.setParameter("id", id);
		List<Professor> professores = query.getResultList();
		manager.close();
		return professores;
	}

}
