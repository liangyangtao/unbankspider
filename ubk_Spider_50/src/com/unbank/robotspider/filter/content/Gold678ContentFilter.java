package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class Gold678ContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(Gold678ContentFilter.class);

	private String domain = "www.gold678.com";

	public Gold678ContentFilter() {
		ContentFilterLocator.getInstance().register(domain, this);
	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#nr");
		if (element == null) {
			return null;
		}
		return element;
	}

	public void removeNoNeedElementsByText(Element contentElement) {
		String textQuerys[] = new String[] { "【在线答疑】", "【汇通讲堂】" };
		for (String textQuery : textQuerys) {
			removeNoNeedTextElement(contentElement, textQuery);
		}
	}

	public void removeNoNeedElementsByCssQuery(Element contentElement) {
		String cssQuerys[] = new String[] { ".nr" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
