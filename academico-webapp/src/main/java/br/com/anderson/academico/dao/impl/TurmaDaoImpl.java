package br.com.anderson.academico.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;

import br.com.anderson.academico.dao.TurmaDao;
import br.com.anderson.academico.modelo.Aluno;
import br.com.anderson.academico.modelo.Aula;
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
	
	public List<Aluno> getAlunos(Long id) {
		TypedQuery<Aluno> query = manager.createQuery("SELECT A FROM Turma T, Aluno A WHERE T.id = A.turma AND T.id = :id", Aluno.class);
		query.setParameter("id", id);
		List<Aluno> alunos = query.getResultList();
		manager.close();
		return alunos;
	}
	
	public List<Aula> getAulas(Long id) {
		TypedQuery<Aula> query = manager.createQuery("SELECT A FROM Turma T, Aula A WHERE T.id = A.turma AND T.id = :id", Aula.class);
		query.setParameter("id", id);
		List<Aula> aulas = query.getResultList();
		manager.close();
		return aulas;
	}
	
}
