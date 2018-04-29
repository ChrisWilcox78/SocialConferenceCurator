package com.normativeanimal.social.persistence;

import java.util.function.Function;

import com.normativeanimal.social.domain.PostContainer;
import com.normativeanimal.social.domain.Tweet;
import com.normativeanimal.social.persistence.bean.TweetBean;

public class TweetToBeanConverter implements Function<PostContainer<Tweet>, TweetBean> {

	@Override
	public TweetBean apply(final PostContainer<Tweet> tweetContainer) {
		final Tweet tweet = tweetContainer.getPost();
		return new TweetBean.Builder()
		        .withId(tweet.getId())
		        .withProcessingStatus(tweetContainer.getProcessingStatus())
		        .withCreatedDate(tweet.getCreatedDate())
		        .withScreenName(tweet.getScreenName())
				.withUserImage(tweet.getUserImage())
		        .withText(tweet.getText())
		        .withCountry(tweet.getCountry())
		        .build();
	}
}
