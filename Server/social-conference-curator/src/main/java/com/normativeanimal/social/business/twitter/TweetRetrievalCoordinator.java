package com.normativeanimal.social.business.twitter;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.normativeanimal.social.business.RetrievalCoordinator;
import com.normativeanimal.social.domain.PostContainer;
import com.normativeanimal.social.domain.Tweet;
import com.normativeanimal.social.persistence.TweetPersistenceManager;

import twitter4j.Twitter;
import twitter4j.TwitterFactory;
import twitter4j.auth.AccessToken;

// TODO move the twitter connection stuff to its own class
public class TweetRetrievalCoordinator implements RetrievalCoordinator<Tweet> {
	private static final String CONSUMER_KEY = System.getenv().get("twitter.consumer.key");
	private static final String CONSUMER_SECRET = System.getenv().get("twitter.consumer.secret");
	private static final AccessToken ACCESS_TOKEN = new AccessToken(System.getenv().get("twitter.token"),
	        System.getenv().get("twitter.token.secret"));

	private static final TweetRetriever RETRIEVER = new TweetRetriever();
	private static final StatusToTweetConverter CONVERTER = new StatusToTweetConverter();
	private static final Twitter TWITTER;

	static {
		TWITTER = TwitterFactory.getSingleton();

		TWITTER.setOAuthConsumer(CONSUMER_KEY, CONSUMER_SECRET);
		final AccessToken accessToken = ACCESS_TOKEN;
		TWITTER.setOAuthAccessToken(accessToken);
	}

	private final TweetPersistenceManager persistenceManager;

	public TweetRetrievalCoordinator() {
		this.persistenceManager = new TweetPersistenceManager();
	}

	@Override
	public List<PostContainer<Tweet>> retrieve(String hashtag) {
		final List<PostContainer<Tweet>> tweets = RETRIEVER.apply(TWITTER, hashtag)
		        .map(CONVERTER)
		        .collect(Collectors.toList());
		this.persistenceManager.persist(tweets);
		return this.persistenceManager.fetchAll();
	}

	@Override
	public Optional<PostContainer<Tweet>> getById(Long id) {
		return this.persistenceManager.getById(id);
	}
}
