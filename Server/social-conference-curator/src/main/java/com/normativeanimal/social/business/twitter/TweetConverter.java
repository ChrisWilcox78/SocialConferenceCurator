package com.normativeanimal.social.business.twitter;

import java.util.function.Function;

import com.normativeanimal.social.domain.PostContainer;
import com.normativeanimal.social.domain.Tweet;

import twitter4j.Status;

public class TweetConverter implements Function<Status, PostContainer<Tweet>> {

	@Override
	public PostContainer<Tweet> apply(Status status) {
		final Tweet transformedTweet = new Tweet.Builder()
		        .withText(status.getText())
		        .withCountry(status.getPlace() != null ? status.getPlace().getCountry() : null)
		        .withCreatedDate(status.getCreatedAt())
		        .withScreenName(status.getUser().getScreenName())
		        .build();

		return new PostContainer<Tweet>(transformedTweet);
	}

}
