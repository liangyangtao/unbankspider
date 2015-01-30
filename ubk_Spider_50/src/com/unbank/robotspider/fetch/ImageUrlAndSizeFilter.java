package com.unbank.robotspider.fetch;


public class ImageUrlAndSizeFilter implements ImageFilter
{

	private String url;
	private int width;
	private int height;

	private int MINWIDTH = 50;
	private int MINHEIGHT = 50;

	public ImageUrlAndSizeFilter(String url, int width, int height)
	{
		this.url = url;
		this.width = width;
		this.height = height;
	}

	@Override
	public boolean reject(String url, int width, int height)
	{
		if (url != null && url.equals(this.url))
		{
			return true;
		}

		if ((width * height) < MINWIDTH * MINHEIGHT)
		{
			return true;
		}

		if (width > 0 && height > 0)
		{
			if (width == this.width && height == this.height)
			{
				return true;
			}
		}

		return false;
	}

}
