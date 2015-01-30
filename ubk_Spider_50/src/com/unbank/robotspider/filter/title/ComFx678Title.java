package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class ComFx678Title extends TitleBaseFilter {

	
	private String domain1 = "www.fx678.com";
	
	public ComFx678Title(){
		TitleFilterLocator.getInstance().register(domain1, this);
	}
	
	@Override
	public String getTitle(Document document, String Alternativetitle) {
		return document.select("h3").first().text();
	}
	
}
