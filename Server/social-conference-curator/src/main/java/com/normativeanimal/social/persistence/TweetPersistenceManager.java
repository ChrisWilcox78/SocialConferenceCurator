package com.normativeanimal.social.persistence;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

import com.normativeanimal.social.domain.PostContainer;
import com.normativeanimal.social.domain.Tweet;

public class TweetPersistenceManager {

	private static final TweetToBeanConverter CONVERT_TO_BEAN = new TweetToBeanConverter();
	private static final BeanToTweetConverter CONVERT_FROM_BEAN = new BeanToTweetConverter();

	private final DatabaseConnectionManager dbConnectionManager;

	public TweetPersistenceManager() {
		this.dbConnectionManager = new DatabaseConnectionManager();
	}

	public void persist(List<PostContainer<Tweet>> tweets) {
		tweets.stream().map(CONVERT_TO_BEAN).forEach(bean -> this.dbConnectionManager.persist(bean));
	}

	public List<PostContainer<Tweet>> fetchAll() {
		return this.dbConnectionManager.fetchAll().stream().map(CONVERT_FROM_BEAN).collect(Collectors.toList());
	}

	public Optional<PostContainer<Tweet>> getById(Long id) {
		return this.dbConnectionManager.getById(id).map(CONVERT_FROM_BEAN);
	}
}
