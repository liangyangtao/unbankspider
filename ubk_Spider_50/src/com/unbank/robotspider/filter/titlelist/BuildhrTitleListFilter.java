package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class BuildhrTitleListFilter extends BaseTitleListFilter {
	private String domain = "news.buildhr.com";

	public BuildhrTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
        Element element =document.select(".morenews").first();
        Elements elements = element.select("h2");
        for (Element element2 : elements) {
			element2.remove();
		}
        
		return element.select("a");
	}

}
