package com.unbank.test.content;

public class SourceWebsite
{
	String id;
	String url;
	public String getId()
	{
		return id;
	}
	public void setId(String id)
	{
		this.id = id;
	}
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public SourceWebsite(String id, String url)
	{
		super();
		this.id = id;
		this.url = url;
	}
	public SourceWebsite()
	{
	}
	
}
