package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;

public interface TitleFilter {
	
	public String getTitle(Document document);
	
	public String  getTitle(Document document, String Alternativetitle);
	
	
}
