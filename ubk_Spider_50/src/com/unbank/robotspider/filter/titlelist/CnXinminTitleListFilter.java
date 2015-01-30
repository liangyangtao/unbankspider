package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnXinminTitleListFilter extends BaseTitleListFilter {
	private String domain = "biz.xinmin.cn";

	public CnXinminTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("ul.newsList>li>a");
	}

}
