package com.normativeanimal.social.domain;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;

import java.util.Date;

public class Tweet implements SocialMediaPost {
	private Date createdDate;
	private String screenName;
	private String userImage;
	private String text;
	private String country;
	private Long id;

	private Tweet() {
	}

	public Long getId() {
		return this.id;
	}

	public Date getCreatedDate() {
		return this.createdDate;
	}

	public String getScreenName() {
		return this.screenName;
	}

	public String getUserImage() {
		return this.userImage;
	}

	public String getText() {
		return this.text;
	}

	public String getCountry() {
		return this.country;
	}

	public static class Builder {
		Tweet underConstruction;

		public Builder() {
			this.underConstruction = new Tweet();
		}

		public Builder withId(final Long id) {
			this.underConstruction.id = id;
			return this;
		}

		public Builder withCreatedDate(final Date createdDate) {
			this.underConstruction.createdDate = createdDate;
			return this;
		}

		public Builder withScreenName(final String screenName) {
			this.underConstruction.screenName = screenName;
			return this;
		}

		public Builder withText(final String text) {
			this.underConstruction.text = text;
			return this;
		}

		public Builder withCountry(final String country) {
			this.underConstruction.country = country;
			return this;
		}

		public Builder withUserImage(final String userImage) {
			this.underConstruction.userImage = userImage;
			return this;
		}
		public Tweet build() {
			if (!allNotNull(this.underConstruction.getCreatedDate(), this.underConstruction.getScreenName(),
			        this.underConstruction.getText(), this.underConstruction.getId())) {
				throw new IllegalStateException("A tweet needs (at least) a creation date, screen name, and text.");
			}
			return this.underConstruction;
		}

	}
}
