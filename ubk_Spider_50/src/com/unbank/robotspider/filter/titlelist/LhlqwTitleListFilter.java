package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Service
public class LhlqwTitleListFilter extends BaseTitleListFilter {
	private String[] domains = { "www.lhlqw.com" };

	public LhlqwTitleListFilter() {
		for (int i = 0; i < domains.length; i++) {
			TitleListFilterLocator.getInstance().register(domains[i], this);
		}

	}

	@Override
	public Elements getPossibleListElement(Document document) {

		document = new JsoupNetFetcher()
				.fetchText("http://search.news.cn/mb/xinhuanet/search/?styleurl=http://www.sd.xinhuanet.com/style/css_erji.css&nodeid=1122932&nodetype=3");

		return getPossibleListElement(document, "table.hei14");
	}

}
