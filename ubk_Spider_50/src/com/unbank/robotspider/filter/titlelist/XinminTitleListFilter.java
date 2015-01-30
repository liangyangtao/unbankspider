package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class XinminTitleListFilter extends BaseTitleListFilter {
	private String domain = "news.xinmin.cn";

	public XinminTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {

		return getPossibleListElement(document, "div.channelmiddle");

	}

}
