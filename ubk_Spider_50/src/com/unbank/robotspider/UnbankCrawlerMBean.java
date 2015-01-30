package com.unbank.robotspider;


public interface UnbankCrawlerMBean {
	
	public int getWebSiteInfoQueueSize();
	public int getNewsListQueueSize();
	public int getNewsPageQueueSize();
	public int getArticleInfoQueueSize();
	
}
