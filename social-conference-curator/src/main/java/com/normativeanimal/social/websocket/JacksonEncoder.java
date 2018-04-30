package com.normativeanimal.social.websocket;

import javax.websocket.EncodeException;
import javax.websocket.Encoder;
import javax.websocket.EndpointConfig;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JacksonEncoder<T> implements Encoder.Text<T> {

	@Override
	public void init(final EndpointConfig config) {
		// do nothing
	}


	@Override
	public void destroy() {
		// do nothing

	}

	@Override
	public String encode(final T object) throws EncodeException {
		final ObjectMapper om = new ObjectMapper();
		try {
			return om.writeValueAsString(object);
		} catch (final JsonProcessingException e) {
			throw new EncodeException(object, e.getMessage());
		}
	}

}

