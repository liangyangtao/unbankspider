package com.unbank.test.content;

public class Website
{
	private String url;
	private int id;
	public String getUrl()
	{
		return url;
	}
	public void setUrl(String url)
	{
		this.url = url;
	}
	public int getId()
	{
		return id;
	}
	public void setId(int id)
	{
		this.id = id;
	}
	@Override
	public String toString()
	{
		return "SourceWebsite [url=" + url + ", id=" + id + "]";
	}
	
	
}
