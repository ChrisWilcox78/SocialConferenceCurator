package com.normativeanimal.social.domain;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;

import java.util.Date;

public class Tweet implements SocialMediaPost {
	private Date createdDate;
	private String screenName;
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

		public Builder withId(Long id) {
			this.underConstruction.id = id;
			return this;
		}

		public Builder withCreatedDate(Date createdDate) {
			this.underConstruction.createdDate = createdDate;
			return this;
		}

		public Builder withScreenName(String screenName) {
			this.underConstruction.screenName = screenName;
			return this;
		}

		public Builder withText(String text) {
			this.underConstruction.text = text;
			return this;
		}

		public Builder withCountry(String country) {
			this.underConstruction.country = country;
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
