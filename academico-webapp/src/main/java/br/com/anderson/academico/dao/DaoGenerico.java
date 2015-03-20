package br.com.anderson.academico.dao;

import java.util.List;

public interface DaoGenerico<Objeto, Id> {

	public void save(Objeto objeto);

	public void update(Objeto objeto);

	public void delete(Objeto objeto);
	
	public Objeto getById(Id id);
	
	public List<Objeto> list();

}
