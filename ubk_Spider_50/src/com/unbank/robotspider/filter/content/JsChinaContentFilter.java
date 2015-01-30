package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class JsChinaContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(JsChinaContentFilter.class);

	private String domain[] = new String[] { "house.jschina.com.cn",
			"zfrx.jschina.com.cn", "m.jschina.com.cn", "jsnews.jschina.com.cn",
			"yuqing.jschina.com.cn", "english.jschina.com.cn",
			"health.jschina.com.cn", "life.jschina.com.cn",
			"tour.jschina.com.cn", "www.jschina.com.cn", "bl.jschina.com.cn",
			"ent.jschina.com.cn", "photo.jschina.com.cn", "wm.jschina.com.cn",
			"cul.jschina.com.cn", "weibo.jschina.com.cn",
			"news.jschina.com.cn", "review.jschina.com.cn", "v.jschina.com.cn",
			"xhrb.jschina.com.cn", "tv.jschina.com.cn",
			"economy.jschina.com.cn" };

	public JsChinaContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}
	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "div#content");
		if (element == null) {
			return null;
		}
		return element;
	}

	public void removeNoNeedElementsByText(Element contentElement) {
		String textQuerys[] = new String[] { "原标题", "稿源", "作者" };
		for (String textQuery : textQuerys) {
			removeNoNeedTextElement(contentElement, textQuery);
		}
	}

	public void removeNoNeedElementsByCssQuery(Element contentElement) {
		String cssQuerys[] = new String[] { "div#news_more_page_div_id" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
