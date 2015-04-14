package br.com.anderson.academico.controller;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

import br.com.anderson.academico.dao.impl.AlunoDaoImpl;
import br.com.anderson.academico.modelo.Aluno;
import br.com.anderson.academico.util.GeradorDeMatricula;

@ManagedBean
@SessionScoped
public class AlunoController {

	private Aluno aluno;
	private List<Aluno> alunos;

	@PostConstruct
	public void construct() {
		AlunoDaoImpl dao = new AlunoDaoImpl();
		alunos = dao.list();
	}
	
	public AlunoController() {
		aluno = new Aluno();
		alunos = new ArrayList<Aluno>();
	}
	
	public void recarregar() {
		AlunoDaoImpl dao = new AlunoDaoImpl();
		alunos = dao.list();
	}
	
	public void prepararNovo(){
		aluno = new Aluno();
	}

	public String adicionar() {
		AlunoDaoImpl dao = new AlunoDaoImpl();
		aluno.setDataMatricula(Calendar.getInstance());
		aluno.setMatricula(GeradorDeMatricula.getInstance().gerar().toString());
		dao.save(aluno);
		return "alunos?faces-redirect=true";
	}

	public String alterar() {
		AlunoDaoImpl dao = new AlunoDaoImpl();
		dao.update(aluno);
		return "alunos";
	}

	public String deletar() {
		AlunoDaoImpl dao = new AlunoDaoImpl();
		dao.delete(aluno);
		return "alunos";
	}

	public Aluno getAluno() {
		return aluno;
	}

	public void setAluno(Aluno aluno) {
		this.aluno = aluno;
	}

	public List<Aluno> getAlunos() {
		recarregar();
		return alunos;
	}

	public void setAlunos(List<Aluno> alunos) {
		this.alunos = alunos;
	}

}
