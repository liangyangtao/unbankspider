package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class JrjTitleListFilter extends BaseTitleListFilter {
	private String domain[] = new String[] { "bank.jrj.com.cn",
			"stock.jrj.com.cn", "finance.jrj.com.cn", "auto.jrj.com.cn",
			"opinion.jrj.com.cn", "fund.jrj.com.cn", "forex.jrj.com.cn",
			"itfinance.jrj.com.cn", "trust.jrj.com.cn", "bankpro.jrj.com.cn",
			"house.jrj.com.cn" };

	public JrjTitleListFilter() {
		for (int i = 0; i < domain.length; i++) {
			TitleListFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Elements getPossibleListElement(Document document) {
		Elements elements = document.body().select("a");
		return elements;
	}

}
