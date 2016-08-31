package com.normativeanimal.social.rest.resources;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.normativeanimal.social.rest.CuratorJaxRSApplication;

import twitter4j.Query;
import twitter4j.Twitter;
import twitter4j.TwitterException;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

/**
 * The basic entry point to the curation ReST application. I might switch this
 * to a list later on.
 *
 * @author chris
 *
 */
@Path("/")
@Singleton
public class CurationEntryPoint {

	@GET
	@Path("list/")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRawList() throws TwitterException {
		final Twitter twitter = TwitterFactory.getSingleton();

		twitter.setOAuthConsumer(CuratorJaxRSApplication.CONSUMER_KEY, CuratorJaxRSApplication.CONSUMER_SECRET);
		final AccessToken accessToken = CuratorJaxRSApplication.ACCESS_TOKEN;
		twitter.setOAuthAccessToken(accessToken);

		return Response.ok(twitter.search(new Query("#fancy")).getTweets()).build();
	}
}
