package com.normativeanimal.social.business.twitter;

import java.util.function.BiFunction;
import java.util.stream.Stream;

import twitter4j.Query;
import twitter4j.Status;
import twitter4j.Twitter;
import twitter4j.TwitterException;

public class TweetRetriever implements BiFunction<Twitter, String, Stream<Status>> {

	@Override
	public Stream<Status> apply(Twitter twitter, String hashtag) {
		try {
			return twitter.search(new Query("#" + hashtag)).getTweets().stream();
		} catch (final TwitterException e) {
			throw new RuntimeException("Unable to retrive tweets from Twitter", e);
		}
	}

}
