package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnComCsTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.cs.com.cn";

	public CnComCsTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("div.column-box > ul > li> a");
	}

}
