package com.normativeanimal.social.rest.resources;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

import com.normativeanimal.social.business.TweetPoller;

@Path("/administration/")
@Singleton
public class Administration {

	private static TweetPoller POLLER = new TweetPoller();

	static {
		new Thread(POLLER).start();
	}

	@GET
	@Path("register")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void getServerSentEvents(@Context final SseEventSink eventSink, @Context final Sse sse) {
		final EventSourceObserver tweetObserver = new EventSourceObserver(eventSink, sse);
		POLLER.addObserver(tweetObserver);
	}
}

