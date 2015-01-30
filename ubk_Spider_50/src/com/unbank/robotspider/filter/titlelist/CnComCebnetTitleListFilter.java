package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnComCebnetTitleListFilter extends BaseTitleListFilter {
	private String[] domains={"hy.cebnet.com.cn","yj.cebnet.com.cn"};

	public CnComCebnetTitleListFilter() {
		for(String domain:domains){
			TitleListFilterLocator.getInstance().register(domain, this);
		}
	}

	@Override
	public Elements getPossibleListElement(Document document) {
		return document.select("ul.list14GrayC>li>a");
	}

}
