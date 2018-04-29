package com.normativeanimal.social.business.twitter;

import java.util.function.Function;
import java.util.stream.Stream;

import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TweetRetriever implements Function<String, Stream<Status>> {

	private final Twitter twitter;

	public TweetRetriever(final Twitter twitter) {
		this.twitter = twitter;
	}

	@Override
	public Stream<Status> apply(final String hashtag) {
		try {
			return this.twitter.search(new Query("#" + hashtag)).getTweets().stream();
		} catch (final TwitterException e) {
			throw new RuntimeException("Unable to retrive tweets from Twitter", e);
		}
	}

}
