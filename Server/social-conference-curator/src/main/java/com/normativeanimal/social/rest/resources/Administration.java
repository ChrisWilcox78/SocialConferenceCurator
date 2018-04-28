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
package com.normativeanimal.social.rest.resources;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.inject.Singleton;
import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.normativeanimal.social.domain.InterestRegistration;

@Path("/administration/")
@Singleton
public class Administration {

	// TODO persist these and pull them back as needed
	private static final Map<String, String> INTEREST_REGISTRATIONS = new ConcurrentHashMap<>();

	@POST
	@Path("registration")
	@Consumes(MediaType.APPLICATION_JSON)
	public Response registerInterest(final InterestRegistration registration) {

		INTEREST_REGISTRATIONS.put(registration.getSourceName(), registration.getSourceUrl());

		return Response.ok().build();
	}
}

