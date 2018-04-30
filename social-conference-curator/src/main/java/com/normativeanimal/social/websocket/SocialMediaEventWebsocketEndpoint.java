package com.normativeanimal.social.websocket;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.websocket.OnClose;
import javax.websocket.OnError;
import javax.websocket.OnOpen;
import javax.websocket.Session;
import javax.websocket.server.ServerEndpoint;

import com.normativeanimal.social.business.TweetPoller;

@ServerEndpoint(value = "/social-media-socket/register", encoders = { JacksonEncoder.class })
public class SocialMediaEventWebsocketEndpoint {

	private static Map<String, WebSocketObserver> SOCKET_MAP = new ConcurrentHashMap<>();

	private static TweetPoller POLLER = new TweetPoller();

	static {
		new Thread(POLLER).start();
	}

	@OnOpen
	public void open(final Session session) {
		final WebSocketObserver webSocketObserver = new WebSocketObserver(session);
		SOCKET_MAP.put(session.getId(), webSocketObserver);
		POLLER.addObserver(webSocketObserver);
	}

	@OnClose
	public void close(final Session session) {
		cleanUpObsoleteConnection(session);
	}

	@OnError
	public void error(final Session session, @SuppressWarnings("unused") final Throwable error) {
		cleanUpObsoleteConnection(session);
	}

	private void cleanUpObsoleteConnection(final Session session) {
		final WebSocketObserver obsoleteObserver = SOCKET_MAP.remove(session.getId());
		if (obsoleteObserver != null) {
			POLLER.deleteObserver(obsoleteObserver);
		}
	}
}

