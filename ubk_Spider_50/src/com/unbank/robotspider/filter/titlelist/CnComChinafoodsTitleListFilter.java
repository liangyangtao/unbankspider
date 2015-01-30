package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnComChinafoodsTitleListFilter extends BaseTitleListFilter {
	private String domain = "news.chinafoods.com.cn";

	public CnComChinafoodsTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select(" div.maines > a");
	}

}
