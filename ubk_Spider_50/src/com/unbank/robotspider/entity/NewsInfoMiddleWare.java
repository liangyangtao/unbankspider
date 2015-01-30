package com.unbank.robotspider.entity;

import java.util.Date;

public class NewsInfoMiddleWare {
	private Integer crawlId;

	private Integer websiteId;

	private String crawlTitle;

	private String crawlBrief;

	private Integer crawlViews;

	private String webName;

	private String url;

	private Byte fileIndex;

	private Date newsTime;

	private Date crawlTime;

	private Byte task;

	private String text;

	public Integer getCrawlId() {
		return crawlId;
	}

	public void setCrawlId(Integer crawlId) {
		this.crawlId = crawlId;
	}

	public Integer getWebsiteId() {
		return websiteId;
	}

	public void setWebsiteId(Integer websiteId) {
		this.websiteId = websiteId;
	}

	public String getCrawlTitle() {
		return crawlTitle;
	}

	public void setCrawlTitle(String crawlTitle) {
		this.crawlTitle = crawlTitle;
	}

	public String getCrawlBrief() {
		return crawlBrief;
	}

	public void setCrawlBrief(String crawlBrief) {
		this.crawlBrief = crawlBrief;
	}

	public Integer getCrawlViews() {
		return crawlViews;
	}

	public void setCrawlViews(Integer crawlViews) {
		this.crawlViews = crawlViews;
	}

	public String getWebName() {
		return webName;
	}

	public void setWebName(String webName) {
		this.webName = webName;
	}

	public String getUrl() {
		return url;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public Byte getFileIndex() {
		return fileIndex;
	}

	public void setFileIndex(Byte fileIndex) {
		this.fileIndex = fileIndex;
	}

	public Date getNewsTime() {
		return newsTime;
	}

	public void setNewsTime(Date newsTime) {
		this.newsTime = newsTime;
	}

	public Date getCrawlTime() {
		return crawlTime;
	}

	public void setCrawlTime(Date crawlTime) {
		this.crawlTime = crawlTime;
	}

	public Byte getTask() {
		return task;
	}

	public void setTask(Byte task) {
		this.task = task;
	}

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

}