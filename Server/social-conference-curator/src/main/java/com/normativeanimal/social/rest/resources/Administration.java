package com.normativeanimal.social.rest.resources;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

@Path("/administration/")
@Singleton
public class Administration {

	private static final TweetPoller TWEET_POLLER = new TweetPoller();

	static {
		new Thread(TWEET_POLLER).start();
	}

	@GET
	@Path("register")
	@Produces(MediaType.SERVER_SENT_EVENTS)
	public void getServerSentEvents(@Context final SseEventSink eventSink, @Context final Sse sse) {
		final TweetObserver tweetObserver = new TweetObserver(eventSink, sse);
		TWEET_POLLER.addObserver(tweetObserver);
	}
}

