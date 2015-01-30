package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class Com2258TitleListFilter extends BaseTitleListFilter {
	private String domain[] = new String[] { "www.2258.com" };

	public Com2258TitleListFilter() {
		for (int i = 0; i < domain.length; i++) {
			TitleListFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Elements getPossibleListElement(Document document) {
		//return getPossibleListElement(document, "h4"); //此处只能选择出一个元素
		return document.select("h4>a");
	}

}
