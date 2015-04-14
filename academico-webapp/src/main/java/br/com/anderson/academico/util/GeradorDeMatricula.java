package br.com.anderson.academico.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

import br.com.anderson.academico.dao.impl.AlunoDaoImpl;

public class GeradorDeMatricula {

	private static GeradorDeMatricula instance;
	private Random rand;
	private List<Integer> matriculas;
	
	private GeradorDeMatricula() {
		rand = new Random();
		matriculas = new ArrayList<Integer>();
	}
	
	public static GeradorDeMatricula getInstance() {
		if (instance == null) {
			instance = new GeradorDeMatricula();
		} 
		
		return instance;
	}
	
	public Integer gerar() {
		matriculas = new AlunoDaoImpl().getMatriculas();
		Integer matricula = rand.nextInt(10001);
		
		while (matriculas.contains(matricula)) {
			matricula = rand.nextInt(10001);
		}
		
		return matricula;
	}
	
}
