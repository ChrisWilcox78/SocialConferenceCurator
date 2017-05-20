package com.normativeanimal.social.business.twitter;

import java.util.function.Function;

import com.normativeanimal.social.domain.Tweet;

import twitter4j.Status;

public class TweetConverter implements Function<Status, Tweet> {

	@Override
	public Tweet apply(Status status) {
		return new Tweet.Builder()
		        .withText(status.getText())
		        .withCountry(status.getPlace() != null ? status.getPlace().getCountry() : null)
		        .withCreatedDate(status.getCreatedAt())
		        .withScreenName(status.getUser().getScreenName())
		        .build();
	}

}
