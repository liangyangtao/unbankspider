package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class Hx591ContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(Hx591ContentFilter.class);

	private String domain[] = new String[] { "www.591hx.com",
			"stock.591hx.com", "finance.591hx.com", "money.591hx.com",
			"fs.591hx.com", "quote.591hx.com", "stock.591hx.com",
			"stock.591hx.com", "stock.591hx.com", "stock.591hx.com",
			"stock.591hx.com", "funds.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "fxb.591hx.com", "stock.591hx.com",
			"club.591hx.com", "myhx.591hx.com", "App.591hx.com",
			"jcw.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "opinion.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "opinion.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"www.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "money.591hx.com", "money.591hx.com",
			"finance.591hx.com", "stock.591hx.com", "money.591hx.com",
			"money.591hx.com", "fs.591hx.com", "stock.591hx.com",
			"fund.591hx.com", "fund.591hx.com", "stock.591hx.com",
			"stock.591hx.com", "stock.591hx.com", "www.591hx.com",
			"stock.591hx.com", "ggpc.591hx.com", "fund.591hx.com",
			"money.591hx.com", "stock.591hx.com", "stock.591hx.com",
			"quote.591hx.com", "stock.591hx.com", "stock.591hx.com",
			"stock.591hx.com", "ggpc.591hx.com", "fs.591hx.com",
			"fs.591hx.com", "www.591hx.com", "www.591hx.com", "www.591hx.com",
			"www.591hx.com", "www.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "stock.591hx.com",
			"stock.591hx.com", "stock.591hx.com", "stock.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "money.591hx.com",
			"money.591hx.com", "money.591hx.com", "fund.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "finance.591hx.com",
			"finance.591hx.com", "finance.591hx.com", "www.591hx.com",
			"www.591hx.com", "www.591hx.com", "shop.591hx.com",
			"club.591hx.com", "www.591hx.com", "www.591hx.com",
			"special.591hx.com", "www.591hx.com", "www.591hx.com",
			"stock.591hx.com", "stock.591hx.com", "www.591hx.com",
			"www.591hx.com", "www.591hx.com", "www.591hx.com", "www.591hx.com",
			"www.591hx.com" };

	public Hx591ContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#newsCon");
		if (element == null) {
			return null;
		}
		return element;
	}

	public void removeNoNeedElementsByText(Element contentElement) {
		// String textQuerys[] = new String[] { "" };
		// for (String textQuery : textQuerys) {
		// removeNoNeedTextElement(contentElement, textQuery);
		// }
	}

	public void removeNoNeedElementsByCssQuery(Element contentElement) {
		String cssQuerys[] = new String[] { "span", "div" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
