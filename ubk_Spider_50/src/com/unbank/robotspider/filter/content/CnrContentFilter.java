package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CnrContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(CnrContentFilter.class);

	private String domain[] = new String[] { "fm1018.cnr.cn", "cbu.cnr.cn",
			"audio.cnr.cn", "www.cnr.cn", "gongyi.cnr.cn", "life.cnr.cn",
			"old.cnr.cn", "tv.cnr.cn", "tech.cnr.cn", "roll.cnr.cn",
			"sports.cnr.cn", "musicradio.cnr.cn", "photo.cnr.cn", "ent.cnr.cn",
			"pic.cnr.cn", "ygzq.cnr.cn", "cneb.cnr.cn", "travel.cnr.cn",
			"diaocha.cnr.cn", "mil.cnr.cn", "cnrhost.cnr.cn", "lady.cnr.cn",
			"china.cnr.cn", "hxradio.cnr.cn", "auto.cnr.cn", "finance.cnr.cn",
			"news.cnr.cn", "health.cnr.cn", "country.cnr.cn", "edu.cnr.cn",
			"aod.cnr.cn", "bbs.cnr.cn" };

	public CnrContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		String cssQuerys[] = new String[] { ".sanji_left", ".sanji_left_text_3" };
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
		String cssQuerys[] = new String[] { "div.blank10", "div.f14",
				"div.blank5", ".f12_005A", ".more", "#ckepop", "object" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
