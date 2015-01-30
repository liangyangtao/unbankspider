package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class StockstarContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(StockstarContentFilter.class);

	private String domain[] = new String[] { "hk.stockstar.com",
			"download.stockstar.com", "futures.stockstar.com",
			"www.stockstar.com", "same.stockstar.com", "if.stockstar.com",
			"pub.stockstar.com", "my.stockstar.com", "money.stockstar.com",
			"stock.stockstar.com", "resource.stockstar.com",
			"index.quote.stockstar.com", "fund.stockstar.com",
			"bschool.stockstar.com", "quote.stockstar.com", "v.stockstar.com",
			"test.stockstar.com", "member.stockstar.com", "b.stockstar.com",
			"special.stockstar.com", "finance.stockstar.com",
			"forex.stockstar.com", "bond.stockstar.com", "news.stockstar.com",
			"insurance.stockstar.com", "focus.stockstar.com",
			"life.stockstar.com", "survey.stockstar.com", "lux.stockstar.com",
			"school.stockstar.com", "pic.stockstar.com", "bank.stockstar.com",
			"stock.quote.stockstar.com", "auto.stockstar.com",
			"if.quote.stockstar.com", "house.stockstar.com",
			"live.stockstar.com", "gold.stockstar.com", "info.stockstar.com",
			"kaihu.stockstar.com", "store.stockstar.com", "bar.stockstar.com" };

	public StockstarContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		String cssQuerys[] = new String[] { ".content", "#container-article" };
		Element element = null;
		for (String string : cssQuerys) {
			element = assignPossibleElement(document, string);
			if (element != null) {
				break;
			}
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
		String cssQuerys[] = new String[] { "#content_div_300_250" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
