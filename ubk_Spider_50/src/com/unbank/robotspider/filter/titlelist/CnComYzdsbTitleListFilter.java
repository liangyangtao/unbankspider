package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnComYzdsbTitleListFilter extends BaseTitleListFilter {
	private String domain = "yanzhao.yzdsb.com.cn";

	public CnComYzdsbTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("ul.list>li> a");
	}

}
