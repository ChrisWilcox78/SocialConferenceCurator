package com.normativeanimal.social.business;

import java.util.List;
import java.util.Optional;

import com.normativeanimal.social.domain.PostContainer;
import com.normativeanimal.social.domain.SocialMediaPost;

public interface RetrievalCoordinator<P extends SocialMediaPost> {

	public List<PostContainer<P>> retrieve(String hashtag);

	public Optional<PostContainer<P>> getById(Long id);
}
