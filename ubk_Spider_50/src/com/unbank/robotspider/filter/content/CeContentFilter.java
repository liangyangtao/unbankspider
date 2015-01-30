package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CeContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(CeContentFilter.class);

	private String domain[] = new String[] { "ent.ce.cn", "foodsafety.ce.cn",
			"edu.ce.cn", "12365.ce.cn", "yq.ce.cn", "europe.ce.cn",
			"wap.ce.cn", "hj.ce.cn", "fashion.ce.cn", "bt.ce.cn", "cz.ce.cn",
			"js.ce.cn", "sn.ce.cn", "blog.ce.cn", "ah.ce.cn", "health.ce.cn",
			"district.ce.cn", "ar.ce.cn", "cv.ce.cn", "finance.ce.cn",
			"cq.ce.cn", "sme.ce.cn", "sd.ce.cn", "gx.ce.cn", "book.ce.cn",
			"ja.ce.cn", "luxury.ce.cn", "zs.ce.cn", "kfq.ce.cn", "qh.ce.cn",
			"hlj.ce.cn", "kr.ce.cn", "art.ce.cn", "de.ce.cn", "hi.ce.cn",
			"xz.ce.cn", "baby.ce.cn", "xj.ce.cn", "en.ce.cn", "fj.ce.cn",
			"cen.ce.cn", "expo.ce.cn", "views.ce.cn", "fr.ce.cn", "www.ce.cn",
			"life.ce.cn", "hsj.ce.cn", "zhujian.ce.cn", "hb.ce.cn",
			"net.ce.cn", "intl.ce.cn", "es.ce.cn", "travel.ce.cn", "sc.ce.cn",
			"sh.ce.cn", "gongyi.ce.cn", "shuhua.ce.cn", "auto.ce.cn",
			"gd.ce.cn", "tj.ce.cn", "fz.ce.cn", "nongye.ce.cn", "paper.ce.cn",
			"fangtan.ce.cn", "city.ce.cn", "sz.ce.cn", "he.ce.cn",
			"big5.ce.cn", "bbs.ce.cn", "sx.ce.cn", "hn.ce.cn", "ru.ce.cn",
			"data.ce.cn", "jx.ce.cn", "ha.ce.cn" };

	public CeContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#articleText");
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
		// String cssQuerys[] = new String[] { "#hzh", "#nextpage", "#pages" };
		// for (String cssQuery : cssQuerys) {
		// removeNoNeedElement(contentElement, cssQuery);
		// }
	}

}
