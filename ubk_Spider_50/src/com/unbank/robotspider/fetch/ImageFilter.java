package com.unbank.robotspider.fetch;

public interface ImageFilter
{
	public boolean reject(String url,int width,int height);
}
