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
package com.normativeanimal.social.domain;

/**
 * The details of how an application that is interest in push notifications.
 */
public class InterestRegistration {

	private String sourceName;
	private String sourceUrl;

	protected InterestRegistration() {
	}

	public InterestRegistration(final String sourceName, final String sourceUrl) {
		this.sourceName = sourceName;
		this.sourceUrl = sourceUrl;
	}

	public void setSourceName(final String sourceName) {
		this.sourceName = sourceName;
	}

	public void setSourceUrl(final String sourceUrl) {
		this.sourceUrl = sourceUrl;
	}

	public String getSourceName() {
		return this.sourceName;
	}

	public String getSourceUrl() {
		return this.sourceUrl;
	}
}

