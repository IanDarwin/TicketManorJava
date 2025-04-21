package com.ticketmanor.model.jackson;

import java.io.IOException;
import java.time.LocalDateTime;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.core.JsonToken;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.JsonDeserializer;

public class MyLocalDateTimeDeserializer extends JsonDeserializer<LocalDateTime>{

	@Override
	public LocalDateTime deserialize(JsonParser parser,
			DeserializationContext ctx) 
					throws IOException, JsonProcessingException {
		JsonToken currentToken;
		currentToken = parser.getCurrentToken();
		if (currentToken == JsonToken.VALUE_STRING) {
			String dateTimeAsString = parser.getText().trim();
			return LocalDateTime.from(
				MyLocalDateTimeSerializer.DATE_FORMATTER.parse(dateTimeAsString));
		}
		else {
			throw new IllegalStateException("Could not deserialize this mess: " + currentToken);
		}
	}
}
