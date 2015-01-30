package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CebnetContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(CebnetContentFilter.class);

	private String domain[] = new String[] { "mobile.cebnet.com.cn",
			"yh.cebnet.com.cn", "app.cebnet.com.cn", "media.cebnet.com.cn",
			"cebnet.com.cn", "www.cebnet.com.cn", "lc.cebnet.com.cn",
			"aq.cebnet.com.cn", "efinance.cebnet.com.cn",
			"money.cebnet.com.cn", "zhuanlan.cebnet.com.cn",
			"xc.cebnet.com.cn", "zx.cebnet.com.cn", "yj.cebnet.com.cn",
			"bbs.cebnet.com.cn", "hy.cebnet.com.cn" };

	public CebnetContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}
	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#article");
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
