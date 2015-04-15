package br.com.anderson.academico.converter;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;

import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.convert.Converter;
import javax.faces.convert.FacesConverter;

@FacesConverter("HoraConverter")
public class HoraConverter implements Converter {

	@Override
	public Object getAsObject(FacesContext context, UIComponent component,
			String value) {
		Calendar retorno = Calendar.getInstance();
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");

		if (value.equals("")) {
			return null;
		}

		try {
			retorno.setTime(fmt.parse(value));
			return retorno;
		} catch (ParseException e) {
			e.printStackTrace();
		}

		return null;
	}

	@Override
	public String getAsString(FacesContext context, UIComponent component,
			Object value) {
		Calendar data = (Calendar) value;
		SimpleDateFormat fmt = new SimpleDateFormat("HH:mm");

		try {
			String retorno = fmt.format(data.getTime());

			return retorno;
		} catch (Exception e) {
			return null;
		}
	}

}
