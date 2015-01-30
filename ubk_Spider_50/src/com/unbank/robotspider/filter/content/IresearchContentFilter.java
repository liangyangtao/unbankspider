package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class IresearchContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(IresearchContentFilter.class);

	private String domain[] = new String[] { "game.iresearch.cn",
			"m.iresearch.cn", "web2.iresearch.cn", "data.iresearch.cn",
			"news.iresearch.cn", "start.iresearch.cn", "jp.iresearch.com.cn",
			"center.iresearch.cn", "media.iresearch.cn", "events.iresearch.cn",
			"ec.iresearch.cn", "wireless.iresearch.cn", "column.iresearch.cn",
			"a.iresearch.cn", "www.iresearch.cn", "peixun.iresearch.cn",
			"iryc.iresearch.com.cn", "report.iresearch.cn",
			"www.iresearch.com.cn" };

	public IresearchContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		String cssQuerys[] = new String[] { ".content_Article", "#con_div" };
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
		String cssQuerys[] = new String[] { ".review", "#nextpage", "#pages" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
