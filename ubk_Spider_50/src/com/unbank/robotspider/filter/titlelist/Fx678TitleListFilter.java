package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class Fx678TitleListFilter extends BaseTitleListFilter {
	private String domain = "www.fx678.com";

	public Fx678TitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
        Element element =document.select(".main").first();
        Elements elements = element.select(".list_content01_content");
        for (Element element2 : elements) {
			element2.remove();
		}
        
		return element.select("a");
	}

}
