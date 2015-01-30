package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.springframework.stereotype.Service;

@Service
public class Com24k99Title extends TitleBaseFilter {

	
	private String domain1 = "www.24k99.com";
	
	public Com24k99Title(){
		TitleFilterLocator.getInstance().register(domain1, this);
	}
	
	@Override
	public String getTitle(Document document, String Alternativetitle) {
		return document.select("div.yyw_news0508_main_box_left_div_title").first().text();
	}
	
}
