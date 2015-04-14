package br.com.anderson.academico.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.anderson.academico.dao.impl.TurmaDaoImpl;
import br.com.anderson.academico.modelo.Turma;
import br.com.anderson.academico.util.JpaUtil;

@FacesConverter(forClass=Turma.class)
public class TurmaConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Turma retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		
		try {
			if (value != null && !"".equals(value)) {
				retorno = new TurmaDaoImpl().getById(new Long(value));
			}
			
			return retorno;
		} catch (Exception e) {
			e.printStackTrace();
			return null;
		} finally {
			manager.close();
		}
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		
		if (value instanceof Turma) {
			Turma retorno = (Turma) value;
			
			try {
				return retorno.getId().toString();
			} catch (Exception e) {
				return null;
			}
		} else {
			return null;
		}
	}

}
