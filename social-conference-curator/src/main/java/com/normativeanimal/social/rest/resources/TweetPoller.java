package com.normativeanimal.social.rest.resources;

import java.util.List;
import java.util.Observable;

import com.normativeanimal.social.business.RetrievalCoordinator;
import com.normativeanimal.social.business.twitter.TweetRetrievalCoordinator;
import com.normativeanimal.social.domain.PostContainer;
import com.normativeanimal.social.domain.Tweet;

/**
 * Polls the twitter API and pushes out updated data to observers.
 */
public class TweetPoller extends Observable implements Runnable {
	private static final int POLLING_INTERVAL = 30000;
	private final RetrievalCoordinator<Tweet> tweetRetrievalCoordinator;

	public TweetPoller() {
		this.tweetRetrievalCoordinator = new TweetRetrievalCoordinator();
	}

	@Override
	public void run() {
		while (true) {
			try {
				Thread.sleep(POLLING_INTERVAL);
			} catch (final InterruptedException e) {
			}
			final List<PostContainer<Tweet>> retrievedTweets = this.tweetRetrievalCoordinator.retrieve("test");
			setChanged();
			notifyObservers(retrievedTweets);
		}
	}
}
