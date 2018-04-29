/*
 * Copyright (c) Orchestral Developments Ltd and the Orion Health group of companies (2001 - 2018).
 *
 * This document is copyright. Except for the purpose of fair reviewing, no part
 * of this publication may be reproduced or transmitted in any form or by any
 * means, electronic or mechanical, including photocopying, recording, or any
 * information storage and retrieval system, without permission in writing from
 * the publisher. Infringers of copyright render themselves liable for
 * prosecution.
 */
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

