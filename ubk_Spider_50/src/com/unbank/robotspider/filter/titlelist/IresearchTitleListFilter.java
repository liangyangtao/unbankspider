package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class IresearchTitleListFilter extends BaseTitleListFilter {
	private String domain = "wireless.iresearch.cn";

	public IresearchTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}
		Elements possibleListElements = bodyElement.select("div.list_tilet > p > a");
		return possibleListElements;
	}

}
