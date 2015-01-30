package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class MsnContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(MsnContentFilter.class);

	private String domain[] = new String[] { "money.msn.com.cn",
			"weather.msn.com.cn", "soufun.msn.com.cn",
			"englishtown.msn.com.cn", "baby.msn.com.cn",
			"fundapp.money.msn.com.cn", "funddata.money.msn.com.cn",
			"it.msn.com.cn", "fashion.msn.com.cn", "wp.msn.com.cn",
			"ijie.msn.com.cn", "photo.msn.com.cn", "digital.msn.com.cn",
			"now.msn.com.cn", "privacy2.msn.com", "auto.msn.com.cn",
			"xinzhi.msn.com.cn", "win8.msn.com.cn", "health.msn.com.cn",
			"bbs.msn.com.cn", "cn.msn.com", "info.msn.com.cn",
			"ent.msn.com.cn", "luxury.msn.com.cn" };

	public MsnContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = null;
		String cssQuerys[] = new String[] { "#rt_p1", ".nry_zw", ".endText",
				"#p_content" };
		for (int i = 0; i < cssQuerys.length; i++) {
			element = assignPossibleElement(document, cssQuerys[i]);
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
