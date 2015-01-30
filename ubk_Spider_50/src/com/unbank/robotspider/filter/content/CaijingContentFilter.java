package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CaijingContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(CaijingContentFilter.class);

	private String domain[] = new String[] { "column.caijing.com.cn",
			"english.caijing.com.cn", "life.caijing.com.cn",
			"roll.caijing.com.cn", "corp.caijing.com.cn",
			"magazine.caijing.com.cn", "photos.caijing.com.cn",
			"estate.caijing.com.cn", "t.caijing.com.cn", "auto.caijing.com.cn",
			"www.caijing.com.cn", "politics.caijing.com.cn",
			"blog.caijing.com.cn", "industry.caijing.com.cn",
			"overseas.caijing.com.cn", "tech.caijing.com.cn",
			"comments.caijing.com.cn", "finance.caijing.com.cn",
			"app.caijing.com.cn", "economy.caijing.com.cn",
			"stock.caijing.com.cn" };

	public CaijingContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#the_content");
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
		String cssQuerys[] = new String[] { ".ar_writer", ".ar_keywords",
				"div.clear" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
