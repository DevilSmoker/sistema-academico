package br.com.anderson.academico.util;

import javax.persistence.Persistence;

public class GerarTabelas {

	public static void main(String[] args) {
		Persistence.createEntityManagerFactory("AcademicoPU");

	}

}
