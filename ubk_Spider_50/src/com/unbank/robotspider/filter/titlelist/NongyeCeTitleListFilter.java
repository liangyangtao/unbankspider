package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class NongyeCeTitleListFilter extends BaseTitleListFilter {
	private String domain = "nongye.ce.cn";

	public NongyeCeTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}
		Elements possibleListElements = bodyElement.select("div.sec_left")
				.first().select("a");
		return possibleListElements;
	}

}
