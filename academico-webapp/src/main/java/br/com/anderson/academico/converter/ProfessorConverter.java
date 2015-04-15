package br.com.anderson.academico.converter;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;
import javax.persistence.EntityManager;

import br.com.anderson.academico.dao.impl.ProfessorDaoImpl;
import br.com.anderson.academico.modelo.Professor;
import br.com.anderson.academico.util.JpaUtil;

@FacesConverter(forClass=Professor.class)
public class ProfessorConverter implements Converter{

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Professor retorno = null;
		EntityManager manager = JpaUtil.getEntityManager();
		
		try {
			if (value != null && !"".equals(value)) {
				retorno = new ProfessorDaoImpl().getById(new Long(value));
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
		
		if (value instanceof Professor) {
			Professor retorno = (Professor) value;
			
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
