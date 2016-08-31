package com.normativeanimal.social.rest;

import java.io.IOException;
import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.glassfish.grizzly.http.server.HttpServer;
import org.glassfish.jersey.grizzly2.httpserver.GrizzlyHttpServerFactory;
import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.normativeanimal.social.rest.resources.CurationEntryPoint;

import twitter4j.auth.AccessToken;

/**
 * TODO - logging
 */
public class CuratorJaxRSApplication extends ResourceConfig {

	public static String CONSUMER_KEY = System.getenv().get("twitter.consumer.key");
	public static String CONSUMER_SECRET = System.getenv().get("twitter.consumer.secret");
	public static AccessToken ACCESS_TOKEN = new AccessToken(System.getenv().get("twitter.token"),
			System.getenv().get("twitter.token.secret"));

	// just run it in a basic server for now - will think about containers later
	public static void main(String[] args) throws IOException {
		final URI serverBase = UriBuilder.fromUri("http://localhost/").port(9998).build();
		final HttpServer server = GrizzlyHttpServerFactory.createHttpServer(serverBase, new CuratorJaxRSApplication());
		server.start();
	}

	public CuratorJaxRSApplication() {
		packages(CurationEntryPoint.class.getPackage().getName());
		register(JacksonFeature.class);
	}

}
