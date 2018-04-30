package com.normativeanimal.social;

import javax.ws.rs.client.Client;
import javax.ws.rs.client.ClientBuilder;
import javax.ws.rs.client.WebTarget;
import javax.ws.rs.sse.SseEventSource;

import org.junit.Test;

public class EventSourceTest {

	@Test
	// not really a test, just a way of hitting the sse api and getting a response
	public void basicTest() throws InterruptedException {
		// basic sse testy stuff stolen form the Jersey docs
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

