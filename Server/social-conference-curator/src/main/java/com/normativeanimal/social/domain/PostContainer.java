package com.normativeanimal.social.domain;

import static com.normativeanimal.social.domain.PostContainer.ProcessingStatus.UNPROCESSED;

public class PostContainer<P extends SocialMediaPost> {
	public static enum ProcessingStatus {
		UNPROCESSED, IGNORED, MARKED_FOR_DISPLAY, MARKED_FOR_PRESENTER
	}

	private final ProcessingStatus processingStatus;
	private final P post;

	public PostContainer(P post) {
		this.processingStatus = UNPROCESSED;
		this.post = post;
	}

	public PostContainer(P post, ProcessingStatus processingStatus) {
		this.processingStatus = processingStatus;
		this.post = post;
	}

	public ProcessingStatus getProcessingStatus() {
		return this.processingStatus;
	}

	public P getPost() {
		return this.post;
	}
}
