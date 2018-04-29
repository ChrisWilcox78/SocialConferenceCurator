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
package com.normativeanimal.social.server;

import java.io.IOException;
import java.net.URI;
import java.util.concurrent.CountDownLatch;

import javax.websocket.DeploymentException;
import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.tyrus.server.Server;

import com.normativeanimal.social.rest.CuratorJaxRSApplication;
import com.normativeanimal.social.websocket.SocialMediaEventWebsocketEndpoint;

public class SocialMediaCuratorServer {

	// just run things in a basic Grizzly server for now - will think about containers later
	public static void main(final String[] args) throws IOException, DeploymentException, InterruptedException {
		setUpRestServer();
		setUpWebsocketServer();
	}

	private static void setUpWebsocketServer() throws DeploymentException, InterruptedException {
		final Server server = new Server("localhost", 9996, "", null, SocialMediaEventWebsocketEndpoint.class);
		server.start();
		new CountDownLatch(1).await();
	}

	private static void setUpRestServer() throws IOException {
		final URI serverBase = UriBuilder.fromUri("http://localhost/").port(9998).build();
		final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(serverBase, new CuratorJaxRSApplication());
		server.start();
	}
}

