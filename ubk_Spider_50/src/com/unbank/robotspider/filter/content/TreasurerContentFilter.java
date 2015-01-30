package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.fetch.Fetcher;
import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Service
public class TreasurerContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(TreasurerContentFilter.class);

	private String domain[] = new String[] { "www.treasurer.org.cn",
			"finance.treasurer.org.cn" };

	public TreasurerContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		String baseUrl = document.baseUri();
		String url = baseUrl.split(".html")[0];
		url = url + "-1.html";
		Fetcher fetcher = new JsoupNetFetcher();
		document = fetcher.fetchText(url);
		Element element = assignPossibleElement(document, "#show_contents");
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
		String cssQuerys[] = new String[] { "#show_contents_advesimgs",
				"#im_pages", "#pages" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
