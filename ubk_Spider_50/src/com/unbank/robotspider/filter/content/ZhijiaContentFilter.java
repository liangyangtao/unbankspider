package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class ZhijiaContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(ZhijiaContentFilter.class);

	private String domain[] = new String[] { "hs.zhijia.com", "cz.zhijia.com",
			"office.zhijia.com", "passport.zhijia.com", "news.zhijia.com",
			"bd.zhijia.com", "zu.zhijia.com", "bbs.zhijia.com",
			"shop.zhijia.com", "www.zhijia.com", "esf.zhijia.com",
			"house.zhijia.com", "wenda.zhijia.com", "hd.zhijia.com",
			"help.zhijia.com", "sjz.zhijia.com" };

	public ZhijiaContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#news_content");
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
		// String cssQuerys[] = new String[] { "#hzh", "#nextpage", "#pages" };
		// for (String cssQuery : cssQuerys) {
		// removeNoNeedElement(contentElement, cssQuery);
		// }
	}

}
