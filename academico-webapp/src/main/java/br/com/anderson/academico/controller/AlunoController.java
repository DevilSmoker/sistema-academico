package br.com.anderson.academico.controller;

import java.util.ArrayList;
import java.util.List;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

import br.com.anderson.academico.dao.impl.AlunoDaoImpl;
import br.com.anderson.academico.modelo.Aluno;

@ManagedBean
@RequestScoped
public class AlunoController {

	private Aluno aluno;
	private List<Aluno> alunos;

	public AlunoController() {
		aluno = new Aluno();
		alunos = new ArrayList<Aluno>();
	}

	public String adicionar() {
		AlunoDaoImpl dao = new AlunoDaoImpl();
		dao.save(aluno);
		return "alunos?faces-redirect=true";
	}

	public String alterar() {
		AlunoDaoImpl dao = new AlunoDaoImpl();
		dao.update(aluno);
		return "alunos?faces-redirect=true";
	}

	public String deletar() {
		AlunoDaoImpl dao = new AlunoDaoImpl();
		dao.delete(aluno);
		return "alunos?faces-redirect=true";
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		AlunoDaoImpl dao = new AlunoDaoImpl();
		alunos = dao.list();
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}
