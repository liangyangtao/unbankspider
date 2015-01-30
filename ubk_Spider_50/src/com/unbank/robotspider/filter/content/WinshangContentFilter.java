package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class WinshangContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(WinshangContentFilter.class);

	private String domain[] = new String[] { "bbs.winshang.com",
			"news.winshang.com", "cq.winshang.com", "user.winshang.com",
			"www.winshang.com", "sh.winshang.com", "gz.winshang.com",
			"down.winshang.com", "biz.winshang.com", "job.winshang.com" };

	public WinshangContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#newsdiv");
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
		String cssQuerys[] = new String[] { ".newsad" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
