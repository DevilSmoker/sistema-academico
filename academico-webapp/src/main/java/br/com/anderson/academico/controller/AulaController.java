package br.com.anderson.academico.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.anderson.academico.dao.impl.AulaDaoImpl;
import br.com.anderson.academico.modelo.Aula;
import br.com.anderson.academico.modelo.DiasDaSemana;

@ManagedBean
@SessionScoped
public class AulaController {

	private Aula aula;
	private List<Aula> aulas;

	@PostConstruct
	public void construct() {
		AulaDaoImpl dao = new AulaDaoImpl();
		aulas = dao.list();
	}

	public AulaController() {
		aula = new Aula();
		aulas = new ArrayList<Aula>();
	}

	public void recarregar() {
		AulaDaoImpl dao = new AulaDaoImpl();
		aulas = dao.list();
	}

	public void prepararNovo() {
		aula = new Aula();
	}

	public String adicionar() {
		AulaDaoImpl dao = new AulaDaoImpl();
		dao.save(aula);
		return "aulas?faces-redirect=true";
	}

	public String alterar() {
		AulaDaoImpl dao = new AulaDaoImpl();
		dao.update(aula);
		return "aulas";
	}

	public void deletar() {
		AulaDaoImpl dao = new AulaDaoImpl();
		dao.delete(aula);
	}

	public Aula getAula() {
		return aula;
	}

	public void setAula(Aula aula) {
		this.aula = aula;
	}

	public List<Aula> getAulas() {
		recarregar();
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}
	
	public DiasDaSemana[] getDiasDaSemana() {
		return DiasDaSemana.values();
	}

}
