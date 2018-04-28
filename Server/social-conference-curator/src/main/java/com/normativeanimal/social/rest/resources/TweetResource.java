package com.normativeanimal.social.rest.resources;

import java.util.Optional;

import javax.inject.Singleton;
import javax.validation.constraints.NotNull;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import com.normativeanimal.social.business.RetrievalCoordinator;
import com.normativeanimal.social.business.twitter.TweetRetrievalCoordinator;
import com.normativeanimal.social.domain.PostContainer;
import com.normativeanimal.social.domain.Tweet;

@Path("/tweet/")
@Singleton
public class TweetResource {

	RetrievalCoordinator<Tweet> tweetRetrievalCoordinator = new TweetRetrievalCoordinator();

	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRawList(@QueryParam("hashtag") final String hashtag) {
		return Response.ok(this.tweetRetrievalCoordinator.retrieve(hashtag)).build();
	}

	@GET
	@Path("/tweet/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getRawList(@NotNull @PathParam("id") final Long id) {
		final Optional<PostContainer<Tweet>> optionalTweet = this.tweetRetrievalCoordinator.getById(id);
		return optionalTweet.isPresent() ? Response.ok(optionalTweet.get()).build()
		        : Response.status(Status.NOT_FOUND).build();
	}
}
