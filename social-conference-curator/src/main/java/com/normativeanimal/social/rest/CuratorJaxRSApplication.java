package com.normativeanimal.social.rest;

import org.glassfish.jersey.jackson.JacksonFeature;
import org.glassfish.jersey.server.ResourceConfig;

import com.normativeanimal.social.rest.resources.TweetResource;

public class CuratorJaxRSApplication extends ResourceConfig {

	public CuratorJaxRSApplication() {
		packages(TweetResource.class.getPackage().getName());
		register(JacksonFeature.class);
		register(new CorsFilter());
	}

}
