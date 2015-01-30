package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class Fund123ContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(Fund123ContentFilter.class);

	private String domain[] = new String[] { "default.fund123.cn",
			"fund.fund123.cn", "stock.fund123.cn", "ly.fund123.cn",
			"msg.fund123.cn", "fx.fund123.cn", "dingtou.fund123.cn",
			"action.fund123.cn", "gs.fund123.cn", "pay.fund123.cn",
			"market.fund123.cn", "blog.fund123.cn", "fc.fund123.cn",
			"www.fund123.cn", "news.fund123.cn", "bbs.fund123.cn",
			"trust.fund123.cn", "account.fund123.cn", "tools.fund123.cn",
			"my.fund123.cn", "trade.fund123.cn", "user.fund123.cn",
			"shipan.fund123.cn", "about.fund123.cn" };

	public Fund123ContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		String cssQuery[] = new String[] { ".wz_cont", "#main_article" };
		Element element = null;
		for (String string : cssQuery) {
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
		// String cssQuerys[] = new String[] { "#hzh", "#nextpage", "#pages" };
		// for (String cssQuery : cssQuerys) {
		// removeNoNeedElement(contentElement, cssQuery);
		// }
	}

}
