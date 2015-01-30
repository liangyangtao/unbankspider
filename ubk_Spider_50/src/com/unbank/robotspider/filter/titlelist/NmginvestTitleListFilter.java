package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Service
public class NmginvestTitleListFilter extends BaseTitleListFilter {
	private String[] domains = { "www.nmginvest.gov.cn" };

	public NmginvestTitleListFilter() {
		for (String domain : domains) {
			TitleListFilterLocator.getInstance().register(domain, this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		String url ="http://www.nmginvest.gov.cn/zcfg_list.aspx?cid=1";
		document = new JsoupNetFetcher().fetchText(url);
		Elements elements = getPossibleListElement(document, "#form1");
		return elements;
	}

}
