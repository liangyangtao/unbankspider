package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnComSlsdgcTitleListFilter extends BaseTitleListFilter {
	private String domain = "www.slsdgc.com.cn";

	public CnComSlsdgcTitleListFilter() {
		TitleListFilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select(" ul.text_list.text_list_f14 > li > a");
	}

}
