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
package com.normativeanimal.social;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

import org.junit.Test;

public class WebSocketTest {

	@Test
	// not really a test, just a way of hitting the websocket api and getting a response
	public void basicTest() throws InterruptedException {
		// basic socket testy stuff stolen form the Jersey docs
		final Client client = ClientBuilder.newBuilder().build();
		final WebTarget target = client.target("http://localhost:9998/administration/register");
		final SseEventSource sseEventSource = SseEventSource.target(target).build();
		sseEventSource.register((event) -> System.out.println(event.getName() + "; "
				+ event.readData(String.class)));
		sseEventSource.open();

		Thread.sleep(5000);
		sseEventSource.close();
	}
}

