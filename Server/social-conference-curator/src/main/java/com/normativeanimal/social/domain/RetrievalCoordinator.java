package com.normativeanimal.social.domain;

import java.util.List;

public interface RetrievalCoordinator<P extends SocialMediaPost> {

	public List<PostContainer<P>> retrieve(String hashtag);
}
