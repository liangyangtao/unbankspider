package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class Com21cbhTitleListFilter extends BaseTitleListFilter {
	private String[] domains = {"money.21cbh.com"};

	public Com21cbhTitleListFilter() {
		for(String domain:domains){
			TitleListFilterLocator.getInstance().register(domain, this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}
		Elements possibleListElements = bodyElement.select("a.titles");
		return possibleListElements;
	}

}
