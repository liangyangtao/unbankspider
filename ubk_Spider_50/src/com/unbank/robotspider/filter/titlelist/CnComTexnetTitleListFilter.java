package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnComTexnetTitleListFilter extends BaseTitleListFilter {
	private String domain = "info.texnet.com.cn";

	public CnComTexnetTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("div.middlisting>dt>a");
	}

}
