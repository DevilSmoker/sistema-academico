package br.com.anderson.academico.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.TypedQuery;

import br.com.anderson.academico.dao.UsuarioDao;
import br.com.anderson.academico.modelo.Usuario;
import br.com.anderson.academico.util.JpaUtil;

public class UsuarioDaoImpl implements UsuarioDao {

	private EntityManager manager;

	public UsuarioDaoImpl() {
		this.manager = JpaUtil.getEntityManager();
	}

	@Override
	public void save(Usuario objeto) {
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
	public void update(Usuario objeto) {
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
	public void delete(Usuario objeto) {
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
	public Usuario getById(Long id) {
		Usuario usuario = manager.find(Usuario.class, id);
		manager.close();
		return usuario;
	}

	@Override
	public List<Usuario> list() {
		TypedQuery<Usuario> query = manager.createQuery("from Usuario", Usuario.class);
		List<Usuario> usuarios = query.getResultList();
		manager.close();
		return usuarios;
	}
	
	public Usuario autenticar(String login, String senha){
		TypedQuery<Usuario> query = manager.createQuery("from Usuario where login = :login and senha = :senha", Usuario.class);
		query.setParameter("login", login);
		query.setParameter("senha", senha);
		
		try {
			Usuario usuario = query.getSingleResult();
			return usuario;
		} catch (NoResultException e) {
			return null;
		} finally {
			manager.close();
		}
	}
	
}
