package com.kharkiv.diploma.controller.form;

import java.io.IOException;
import java.util.Date;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;
import org.springframework.beans.factory.annotation.Configurable;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

@Configurable
public class DateDeserializer extends JsonDeserializer<Date> {
	
    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM/yyyy");

	@Override
	public Date deserialize(JsonParser jsonparser, DeserializationContext ctxt) throws IOException, JsonProcessingException {
		String date = jsonparser.getText();
		return formatter.parseDateTime(date).toDate();
	}
 

}