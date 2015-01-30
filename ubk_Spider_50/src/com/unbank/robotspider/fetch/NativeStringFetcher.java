package com.unbank.robotspider.fetch;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;


public class NativeStringFetcher implements NativeFetcher
{

	@Override
	public Document fetchText(String htmlString)
	{
		return Jsoup.parse(htmlString);
	}

	@Override
	public String fetchImage(String imageSrc, ImageFilter imageFilter)
	{
		// TODO Auto-generated method stub
		return null;
	}

}
