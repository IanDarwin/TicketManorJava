package com.ticketmanor.ui.jsf;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAccessor;

import jakarta.faces.component.UIComponent;
import jakarta.faces.context.FacesContext;
import jakarta.faces.convert.Converter;
import jakarta.faces.convert.ConverterException;
import jakarta.faces.convert.FacesConverter;

/**
 * @FacesConverter(forClass=LocalDateTime.class)
 */
@FacesConverter(forClass=LocalDateTime.class)
public class LocalDateTimeJsfConverter implements Converter {

	final String pattern = "MMMM d, yyyy h:mm a";
	final DateTimeFormatter formatter = DateTimeFormatter.ofPattern(pattern);

	@Override
	public String getAsString(FacesContext context, UIComponent component, Object value) {
		String dateTime;
		try {
			dateTime = formatter.format((LocalDateTime) value);
		} catch (Exception e){
			throw  new ConverterException(e);
		}
		return dateTime;
	}
	
	@Override
	public Object getAsObject(FacesContext context, UIComponent component, String value) {
		LocalDateTime localDateTime = null;
		try {
			final TemporalAccessor parsed = formatter.parse(value);
			localDateTime = LocalDateTime.from(parsed);
		} catch (Exception e){
			throw new ConverterException(e.toString(), e);
		}
		return localDateTime;
	}
}
