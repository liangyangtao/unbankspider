package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class XinminContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(XinminContentFilter.class);

	private String domain[] = new String[] { "auto.xinmin.cn", "ent.xinmin.cn",
			"tag.xinmin.cn", "opinion.xinmin.cn", "image.xinmin.cn",
			"mobile.xinmin.cn", "op.xinmin.cn", "biz.xinmin.cn",
			"house.xinmin.cn", "shanghai.xinmin.cn", "sports.xinmin.cn",
			"ish.xinmin.cn", "smepc.xinmin.cn", "jiangtang.xinmin.cn",
			"health.xinmin.cn", "t.xinmin.cn", "xmzk.xinmin.cn",
			"hongkouweekly.xinmin.cn", "fashion.xinmin.cn",
			"shtelecom.xinmin.cn", "www.xinmin.cn", "fayuan.xinmin.cn",
			"xmwb.xinmin.cn", "news.xinmin.cn", "tech.xinmin.cn",
			"tousu.xinmin.cn", "sh.xinmin.cn" };

	public XinminContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "div#MP_article");
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

		String cssQuerys[] = new String[] { "#content_wx_arrleft",
				"#content_wx_arrright", "#contentTuiguang_box", };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
