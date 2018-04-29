package com.normativeanimal.social.persistence;

import java.util.function.Function;

import com.normativeanimal.social.domain.PostContainer;
import com.normativeanimal.social.domain.Tweet;
import com.normativeanimal.social.persistence.bean.TweetBean;

public class BeanToTweetConverter implements Function<TweetBean, PostContainer<Tweet>> {

	@Override
	public PostContainer<Tweet> apply(final TweetBean bean) {
		final Tweet tweet = new Tweet.Builder()
		        .withId(bean.getId())
		        .withCreatedDate(bean.getCreatedDate())
		        .withScreenName(bean.getScreenName())
				.withUserImage(bean.getUserImage())
		        .withText(bean.getText())
		        .withCountry(bean.getCountry())
		        .build();
		return new PostContainer<Tweet>(tweet, bean.getProcessingStatus());
	}

}
