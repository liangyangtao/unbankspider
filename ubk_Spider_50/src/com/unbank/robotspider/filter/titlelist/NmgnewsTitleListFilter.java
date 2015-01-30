package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Service
public class NmgnewsTitleListFilter extends BaseTitleListFilter {
	private String[] domains = { "inews.nmgnews.com.cn" };

	public NmgnewsTitleListFilter() {
		for (String domain : domains) {
			TitleListFilterLocator.getInstance().register(domain, this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		Elements elements = getPossibleListElement(document, ".f14bla");
		return elements;
	}

}
