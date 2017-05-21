package com.normativeanimal.social.persistence.bean;

import static org.apache.commons.lang3.ObjectUtils.allNotNull;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

import com.normativeanimal.social.domain.PostContainer.ProcessingStatus;

@Entity
@Table(name = "TWEET")
public class TweetBean {
	private Long id;
	private ProcessingStatus processingStatus;
	private Date createdDate;
	private String screenName;
	private String text;
	private String country;

	@Id
	public Long getId() {
		return this.id;
	}

	@Enumerated(EnumType.STRING)
	public ProcessingStatus getProcessingStatus() {
		return this.processingStatus;
	}

	@Temporal(TemporalType.TIMESTAMP)
	@Column(name = "CREATED_DATE")
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
		private final TweetBean underConstruction;

		public Builder() {
			this.underConstruction = new TweetBean();
		}

		public Builder withId(Long id) {
			this.underConstruction.id = id;
			return this;
		}

		public Builder withProcessingStatus(ProcessingStatus processingStatus) {
			this.underConstruction.processingStatus = processingStatus;
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

		public TweetBean build() {
			if (!allNotNull(this.underConstruction.getCreatedDate(), this.underConstruction.getScreenName(),
			        this.underConstruction.getText(), this.underConstruction.getId())) {
				throw new IllegalStateException("A tweet needs (at least) a creation date, screen name, and text.");
			}
			return this.underConstruction;
		}
	}

	public void setId(Long id) {
		this.id = id;
	}

	public void setProcessingStatus(ProcessingStatus processingStatus) {
		this.processingStatus = processingStatus;
	}

	public void setCreatedDate(Date createdDate) {
		this.createdDate = createdDate;
	}

	public void setScreenName(String screenName) {
		this.screenName = screenName;
	}

	public void setText(String text) {
		this.text = text;
	}

	public void setCountry(String country) {
		this.country = country;
	}
}
