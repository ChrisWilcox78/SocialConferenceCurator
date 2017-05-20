package com.normativeanimal.social.rest.resources;

import javax.inject.Singleton;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.normativeanimal.social.business.twitter.TweetRetrievalCoordinator;

import twitter4j.TwitterException;

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

	TweetRetrievalCoordinator tweetRetrievalCoordinator = new TweetRetrievalCoordinator();

	@GET
	@Path("list")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRawList(@QueryParam("hashtag") String hashtag) throws TwitterException {
		return Response.ok(this.tweetRetrievalCoordinator.retrieve(hashtag)).build();
	}
}
