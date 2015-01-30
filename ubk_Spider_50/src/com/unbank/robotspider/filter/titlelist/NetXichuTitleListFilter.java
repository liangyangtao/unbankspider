package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class NetXichuTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.xichu.net";

	public NetXichuTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("div.Articlelist-left>ul>li>a");
	}

}
