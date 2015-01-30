package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class YouthContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(YouthContentFilter.class);

	private String domain[] = new String[] { "news.youth.cn", "d.youth.cn",
			"sports.youth.cn", "v.youth.cn", "pinglun.youth.cn", "ad.youth.cn",
			"career.youth.cn", "health.youth.cn", "qiyuan.youth.cn",
			"mil.youth.cn", "youxi.youth.cn", "style.youth.cn",
			"auto.youth.cn", "www.youth.cn", "bbs.youth.cn", "qclz.youth.cn",
			"school.youth.cn", "picture.youth.cn", "finance.youth.cn",
			"fun.youth.cn" };

	public YouthContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#container");
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
		String cssQuerys[] = new String[] { "iframe" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
