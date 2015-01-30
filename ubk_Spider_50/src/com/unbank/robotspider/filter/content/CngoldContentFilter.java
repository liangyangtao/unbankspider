package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CngoldContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(CngoldContentFilter.class);

	private String[] domain = new String[] { "ag.cngold.org ",
			"jiage.cngold.org ", "insurance.cngold.org ",
			"finance.cngold.org ", "trader.cngold.org ", "ds.cngold.org ",
			"bank.cngold.org ", "energy.cngold.org ", "usstock.cngold.org ",
			"passport.cngold.org ", "forex.cngold.org ", "stock.cngold.org ",
			"feedback.cngold.org ", "www.cngold.org ", "cang.cngold.org ",
			"jjh.cngold.org ", "my.cngold.org ", "zhubao.cngold.org ",
			"mingjia.cngold.org ", "3g.cngold.org ", "mall.cngold.org ",
			"jigou.cngold.org ", "loan.cngold.org ", "hao.cngold.org ",
			"lux.cngold.org ", "futures.cngold.org ", "kp.cngold.org ",
			"credit.cngold.org ", "dasai.cngold.org" };

	public CngoldContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#zoom");
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
		String cssQuerys[] = new String[] { ".in_content_left_advs" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
