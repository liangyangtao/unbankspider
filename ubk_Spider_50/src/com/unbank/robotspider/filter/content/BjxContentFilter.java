package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class BjxContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(BjxContentFilter.class);

	private String domain[] = new String[] { "ex.bjx.com.cn",
			"dianlan.bjx.com.cn", "shupeidian.bjx.com.cn",
			"dianyuan.bjx.com.cn", "news.bjx.com.cn", "gcjob.bjx.com.cn",
			"dianjian.bjx.com.cn", "fdjob.bjx.com.cn", "nongdian.bjx.com.cn",
			"fd.bjx.com.cn", "kaiguan.bjx.com.cn", "market.bjx.com.cn",
			"guangfu.bjx.com.cn", "hdjob.bjx.com.cn", "hbjob.bjx.com.cn",
			"download.bjx.com.cn", "study.bjx.com.cn", "hedian.bjx.com.cn",
			"huodian.bjx.com.cn", "yibiao.bjx.com.cn", "chuneng.bjx.com.cn",
			"bbs.bjx.com.cn", "bianyaqi.bjx.com.cn", "huanbao.bjx.com.cn",
			"sdjob.bjx.com.cn", "b2b.bjx.com.cn", "www.bjx.com.cn",
			"tech.bjx.com.cn", "shuidian.bjx.com.cn", "gfjob.bjx.com.cn",
			"anfang.bjx.com.cn", "zidonghua.bjx.com.cn", "hr.bjx.com.cn",
			"xinxihua.bjx.com.cn", "map.bjx.com.cn" };

	public BjxContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#content");
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
