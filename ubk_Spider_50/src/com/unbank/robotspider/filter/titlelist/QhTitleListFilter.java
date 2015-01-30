package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class QhTitleListFilter extends BaseTitleListFilter {
	private String[] domains = { "www.qh.gov.cn" };

	public QhTitleListFilter() {
		for (String domain : domains) {
			TitleListFilterLocator.getInstance().register(domain, this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		Elements elements = getPossibleListElement(document, ".pl10");
		return elements;
	}

}
