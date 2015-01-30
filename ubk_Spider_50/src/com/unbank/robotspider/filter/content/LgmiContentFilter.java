package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class LgmiContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(LgmiContentFilter.class);

	private String domain[] = new String[] { "gangcai.lgmi.com",
			"kucun.lgmi.com", "shidian.lgmi.com", "renwu.lgmi.com",
			"sousuo.lgmi.com", "www.lgmi.com", "guoji.lgmi.com",
			"luliao.lgmi.com", "meeting.lgmi.com", "zhishu.lgmi.com",
			"info.lgmi.com", "qihuo.lgmi.com", "tegang.lgmi.com",
			"gangjiegou.lgmi.com", "member.lgmi.com", "youse.lgmi.com",
			"sh.lgmi.com", "tongji.lgmi.com", "gangchang.lgmi.com" };

	public LgmiContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {

		String cssQuerys[] = new String[] { ".hei14", ".t3" };
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
		// String cssQuerys[] = new String[] { "#hzh", "#nextpage", "#pages" };
		// for (String cssQuery : cssQuerys) {
		// removeNoNeedElement(contentElement, cssQuery);
		// }
	}

}
