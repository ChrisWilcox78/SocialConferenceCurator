package com.normativeanimal.social.rest.resources;

import java.util.List;
import java.util.Observable;
import java.util.Observer;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.sse.OutboundSseEvent;
import javax.ws.rs.sse.Sse;
import javax.ws.rs.sse.SseEventSink;

/**
 * Listens for updates and pushes them out as server sent events.
 */
class EventSourceObserver implements Observer {

	private final SseEventSink eventSink;
	private final Sse sse;

	public EventSourceObserver(final SseEventSink eventSink, final Sse sse) {
		this.eventSink = eventSink;
		this.sse = sse;
	}

	@Override
	public void update(final Observable o, final Object arg) {
		if (this.eventSink.isClosed()) {
			o.deleteObserver(this);
		} else {
			final OutboundSseEvent event = this.sse.newEventBuilder()
					.mediaType(MediaType.APPLICATION_JSON_TYPE)
					.data(List.class, arg)
					.build();
			this.eventSink.send(event);
		}
	}
}
