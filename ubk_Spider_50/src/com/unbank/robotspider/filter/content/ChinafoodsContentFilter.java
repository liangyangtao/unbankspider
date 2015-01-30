package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class ChinafoodsContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(ChinafoodsContentFilter.class);

	private String domain[] = new String[] { "safe.chinafoods.com.cn",
			"www.chinafoods.com.cn", "biz.chinafoods.com.cn",
			"ms.chinafoods.com.cn", "bbs.chinafoods.com.cn",
			"news.chinafoods.com.cn", "wuliu.chinafoods.com.cn",
			"market.chinafoods.com.cn", "zt.chinafoods.com.cn" };

	public ChinafoodsContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		String cssQuerys[] = new String[] { ".cpxx3", ".main_newes",
				".m_l_news" };
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
		String cssQuerys[] = new String[] { ".baidu_ad", ".m_l_news_t",
				".m_l_news_j", ".daodu", ".m_l_list_page" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
