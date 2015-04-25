package com.kharkiv.diploma.controller.form;

import java.io.IOException;
import java.util.Date;

import org.joda.time.format.DateTimeFormat;
import org.joda.time.format.DateTimeFormatter;

import com.fasterxml.jackson.core.JsonGenerator;
import com.fasterxml.jackson.databind.JsonSerializer;
import com.fasterxml.jackson.databind.SerializerProvider;

public class DateSerializer extends JsonSerializer<Date> {
	 
    private static DateTimeFormatter formatter = DateTimeFormat.forPattern("dd/MM//yyyy");
 
    @Override
    public void serialize (Date value, JsonGenerator gen, SerializerProvider arg2) throws IOException {
        gen.writeString(formatter.print(value.getTime()));
    }
}