package com.normativeanimal.social.business.twitter;

import java.util.List;
import java.util.stream.Collectors;

import com.normativeanimal.social.domain.PostContainer;
import com.normativeanimal.social.domain.RetrievalCoordinator;
import com.normativeanimal.social.domain.Tweet;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

public class TweetRetrievalCoordinator implements RetrievalCoordinator<Tweet> {
	private static final String CONSUMER_KEY = System.getenv().get("twitter.consumer.key");
	private static final String CONSUMER_SECRET = System.getenv().get("twitter.consumer.secret");
	private static final AccessToken ACCESS_TOKEN = new AccessToken(System.getenv().get("twitter.token"),
	        System.getenv().get("twitter.token.secret"));

	private static final TweetRetriever RETRIEVER = new TweetRetriever();
	private static final TweetConverter CONVERTER = new TweetConverter();

	private final Twitter twitter;

	public TweetRetrievalCoordinator() {
		this.twitter = TwitterFactory.getSingleton();

		this.twitter.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		final AccessToken accessToken = ACCESS_TOKEN;
		this.twitter.setOAuthAccessToken(accessToken);
	}

	@Override
	public List<PostContainer<Tweet>> retrieve(String hashtag) {
		return RETRIEVER.apply(this.twitter, hashtag)
		        .map(CONVERTER)
		        .collect(Collectors.toList());
	}
}
