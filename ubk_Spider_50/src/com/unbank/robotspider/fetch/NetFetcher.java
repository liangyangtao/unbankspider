package com.unbank.robotspider.fetch;

public interface NetFetcher extends Fetcher
{
	
	 public boolean verifyURL(String url);
	 
	 public String encodeURL(String url);
}
