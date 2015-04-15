package br.com.anderson.academico.controller;

import java.util.ArrayList;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.anderson.academico.dao.impl.DisciplinaDaoImpl;
import br.com.anderson.academico.modelo.Disciplina;

@ManagedBean
@SessionScoped
public class DisciplinaController {

	private Disciplina disciplina;
	private List<Disciplina> disciplinas;

	@PostConstruct
	public void construct() {
		DisciplinaDaoImpl dao = new DisciplinaDaoImpl();
		disciplinas = dao.list();
	}

	public DisciplinaController() {
		disciplina = new Disciplina();
		disciplinas = new ArrayList<Disciplina>();
	}

	public void recarregar() {
		DisciplinaDaoImpl dao = new DisciplinaDaoImpl();
		disciplinas = dao.list();
	}

	public void prepararNovo() {
		disciplina = new Disciplina();
	}

	public String adicionar() {
		DisciplinaDaoImpl dao = new DisciplinaDaoImpl();
		dao.save(disciplina);
		return "disciplinas?faces-redirect=true";
	}

	public String alterar() {
		DisciplinaDaoImpl dao = new DisciplinaDaoImpl();
		dao.update(disciplina);
		return "disciplinas";
	}

	public void deletar() {
		DisciplinaDaoImpl dao = new DisciplinaDaoImpl();
		dao.delete(disciplina);
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Disciplina> getDisciplinas() {
		recarregar();
		return disciplinas;
	}

	public void setDisciplinas(List<Disciplina> disciplinas) {
		this.disciplinas = disciplinas;
	}

}
