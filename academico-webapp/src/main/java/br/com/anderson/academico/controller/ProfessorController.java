package br.com.anderson.academico.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.anderson.academico.dao.impl.ProfessorDaoImpl;
import br.com.anderson.academico.modelo.Professor;

@ManagedBean
@SessionScoped
public class ProfessorController {

	private Professor professor;
	private List<Professor> professores;

	@PostConstruct
	public void construct() {
		ProfessorDaoImpl dao = new ProfessorDaoImpl();
		professores = dao.list();
	}

	public ProfessorController() {
		professor = new Professor();
		professores = new ArrayList<Professor>();
	}

	public void recarregar() {
		ProfessorDaoImpl dao = new ProfessorDaoImpl();
		professores = dao.list();
	}

	public void prepararNovo() {
		professor = new Professor();
	}

	public String adicionar() {
		ProfessorDaoImpl dao = new ProfessorDaoImpl();
		professor.setDataAdmissao(Calendar.getInstance());
		dao.save(professor);
		return "professores?faces-redirect=true";
	}

	public String alterar() {
		ProfessorDaoImpl dao = new ProfessorDaoImpl();
		dao.update(professor);
		return "professores";
	}

	public String deletar() {
		ProfessorDaoImpl dao = new ProfessorDaoImpl();
		dao.delete(professor);
		return "professores";
	}

	public Professor getProfessor() {
		return professor;
	}

	public void setProfessor(Professor professor) {
		this.professor = professor;
	}

	public List<Professor> getProfessores() {
		recarregar();
		return professores;
	}

	public void setProfessores(List<Professor> professores) {
		this.professores = professores;
	}

}
