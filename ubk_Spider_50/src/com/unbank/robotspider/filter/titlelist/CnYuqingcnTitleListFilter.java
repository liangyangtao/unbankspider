package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnYuqingcnTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.yuqingcn.cn";

	public CnYuqingcnTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("div.area_left > ul > li > a");
	}

}
