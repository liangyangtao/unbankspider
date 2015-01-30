package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComForexHexun extends BaseTitleListFilter {
	private String domain[] = new String[] { "forex.hexun.com"};

	public ComForexHexun() {
		for (int i = 0; i < domain.length; i++) {
			TitleListFilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		
		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}
		Elements possibleListElements = bodyElement.select("div#zx1615>ul>li>a");
		
		return possibleListElements;
	}

}
