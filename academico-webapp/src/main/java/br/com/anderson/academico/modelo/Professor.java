package br.com.anderson.academico.modelo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotEmpty;

@Entity
public class Professor extends Pessoa {

	private static final long serialVersionUID = 1L;

	@NotEmpty
	@Column(length = 12, nullable = false, unique = true)
	private String registro;

	@NotNull
	@Temporal(TemporalType.DATE)
	@Column(nullable = false)
	private Calendar dataAdmissao;

	@Temporal(TemporalType.DATE)
	private Calendar dataDemissao;

	@NotNull
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "idDisciplina")
	private Disciplina disciplina = new Disciplina();

	@OneToMany(fetch = FetchType.LAZY, mappedBy = "professor", cascade = CascadeType.ALL)
	private List<Aula> aulas = new ArrayList<Aula>();

	public Professor() {
		super();
	}

	public Professor(String nome, String email, String cpf, String sexo,
			String endereco, String cidade, String estado,
			Calendar dataNascimento, String registro, Calendar dataAdmissao,
			Calendar dataDemissao, Disciplina disciplina, List<Aula> aulas) {
		super(nome, email, cpf, sexo, endereco, cidade, estado, dataNascimento);
		this.registro = registro;
		this.dataAdmissao = dataAdmissao;
		this.dataDemissao = dataDemissao;
		this.disciplina = disciplina;
		this.aulas = aulas;
	}

	public String getRegistro() {
		return registro;
	}

	public void setRegistro(String registro) {
		this.registro = registro;
	}

	public Calendar getDataAdmissao() {
		return dataAdmissao;
	}

	public void setDataAdmissao(Calendar dataAdmissao) {
		this.dataAdmissao = dataAdmissao;
	}

	public Calendar getDataDemissao() {
		return dataDemissao;
	}

	public void setDataDemissao(Calendar dataDemissao) {
		this.dataDemissao = dataDemissao;
	}

	public Disciplina getDisciplina() {
		return disciplina;
	}

	public void setDisciplina(Disciplina disciplina) {
		this.disciplina = disciplina;
	}

	public List<Aula> getAulas() {
		return aulas;
	}

	public void setAulas(List<Aula> aulas) {
		this.aulas = aulas;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = super.hashCode();
		result = prime * result
				+ ((registro == null) ? 0 : registro.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (!super.equals(obj))
			return false;
		if (getClass() != obj.getClass())
			return false;
		Professor other = (Professor) obj;
		if (registro == null) {
			if (other.registro != null)
				return false;
		} else if (!registro.equals(other.registro))
			return false;
		return true;
	}

}
