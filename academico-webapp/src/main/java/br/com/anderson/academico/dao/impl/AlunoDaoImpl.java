package br.com.anderson.academico.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.anderson.academico.dao.AlunoDao;
import br.com.anderson.academico.modelo.Aluno;
import br.com.anderson.academico.util.JpaUtil;

public class AlunoDaoImpl implements AlunoDao {

	private EntityManager manager;

	public AlunoDaoImpl() {
		this.manager = JpaUtil.getEntityManager();
	}

	@Override
	public void save(Aluno objeto) {
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
	public void update(Aluno objeto) {
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
	public void delete(Aluno objeto) {
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
	public Aluno getById(Long id) {
		Aluno aluno = manager.find(Aluno.class, id);
		manager.close();
		return aluno;
	}

	@Override
	public List<Aluno> list() {
		TypedQuery<Aluno> query = manager.createQuery("from Aluno", Aluno.class);
		List<Aluno> alunos = query.getResultList();
		manager.close();
		return alunos;
	}

}
