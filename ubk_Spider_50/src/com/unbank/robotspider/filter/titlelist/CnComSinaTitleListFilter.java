package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnComSinaTitleListFilter extends BaseTitleListFilter {
	private String domain[] = new String[] { "roll.finance.sina.com.cn" };

	public CnComSinaTitleListFilter() {
		for (int i = 0; i < domain.length; i++) {
			TitleListFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Elements getPossibleListElement(Document document) {
		//return getPossibleListElement(document, "h4"); //此处只能选择出一个元素
		return document.select("div.listBlk > ul > li> a");
	}

}
