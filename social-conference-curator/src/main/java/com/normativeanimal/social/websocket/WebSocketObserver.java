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

import java.io.IOException;
import java.util.Observable;
import java.util.Observer;

import javax.websocket.EncodeException;
import javax.websocket.Session;

/**
 * Listens for updates and pushes them out over a websocket.
 */
public class WebSocketObserver implements Observer {

	private final Session session;

	public WebSocketObserver(final Session session) {
		this.session = session;
	}

	@Override
	public void update(final Observable o, final Object arg) {

		try {
			this.session.getBasicRemote().sendObject(arg);
		} catch (IOException | EncodeException e) {
			System.err.println(e.getMessage());
			// TODO handle this failure appropriately
		}
	}
}
