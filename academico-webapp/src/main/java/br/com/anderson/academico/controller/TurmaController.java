package br.com.anderson.academico.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.anderson.academico.dao.impl.TurmaDaoImpl;
import br.com.anderson.academico.modelo.Turma;

@ManagedBean
@SessionScoped
public class TurmaController {

	private Turma turma;
	private List<Turma> turmas;
	
	@PostConstruct
	public void construct() {
		TurmaDaoImpl dao = new TurmaDaoImpl();
		turmas = dao.list();
	}
	
	public TurmaController() {
		turma = new Turma();
		turmas = new ArrayList<Turma>();
	}
	
	public void recarregar() {
		TurmaDaoImpl dao = new TurmaDaoImpl();
		turmas = dao.list();
	}
	
	public void prepararNovo() {
		turma = new Turma();
	}
	
	public String adicionar(){
		TurmaDaoImpl dao = new TurmaDaoImpl();
		dao.save(turma);
		return "turmas?faces-redirect=true";
	}
	
	public String alterar(){
		TurmaDaoImpl dao = new TurmaDaoImpl();
		dao.update(turma);
		return "turmas";
	}
	
	public void deletar(){
		TurmaDaoImpl dao = new TurmaDaoImpl();
		dao.delete(turma);
	}

	public Turma getTurma() {
		return turma;
	}

	public void setTurma(Turma turma) {
		this.turma = turma;
	}

	public List<Turma> getTurmas() {
		recarregar();
		return turmas;
	}

	public void setTurmas(List<Turma> turmas) {
		this.turmas = turmas;
	}

}
