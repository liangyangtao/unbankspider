package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CnfolContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(CnfolContentFilter.class);

	private String domain[] = new String[] { "haixi.cnfol.com",
			"xianhuo.cnfol.com", "collection.cnfol.com", "fund.cnfol.com",
			"auto.cnfol.com", "btc.cnfol.com", "fxs.cnfol.com",
			"yc.stock.cnfol.com", "win.cnfol.com", "person.cnfol.com",
			"money.cnfol.com", "sc.stock.cnfol.com", "xg.stock.cnfol.com",
			"blog.cnfol.com", "data.cnfol.com", "video.cnfol.com",
			"new.blog.cnfol.com", "gzqh.cnfol.com", "data.common.cnfol.com",
			"roadshow.cnfol.com", "yjbg.stock.cnfol.com", "review.cnfol.com",
			"zldx2.stock.cnfol.com", "zq.stock.cnfol.com",
			"cyb.stock.cnfol.com", "p.cnfol.com", "insurance.cnfol.com",
			"data.auto.cnfol.com", "funds365.cnfol.com", "xueyuan.cnfol.com",
			"zb.video.cnfol.com", "cs.cnfol.com", "www.cnfol.com",
			"quotes.cnfol.com", "zggng.stock.cnfol.com", "special.cnfol.com",
			"caipiao.cnfol.com", "tuiguang.cnfol.com", "news.cnfol.com",
			"qs.stock.cnfol.com", "bond.cnfol.com", "trust.cnfol.com",
			"passport.cnfol.com", "bank.cnfol.com", "bbs.cnfol.com",
			"forex.cnfol.com", "jraqtest.cnfol.com", "fisco.cnfol.com",
			"live.hkstock.cnfol.com", "invest.cnfol.com", "help.cnfol.com",
			"jraq.cnfol.com", "futures.cnfol.com", "shell.cnfol.com",
			"wm.cnfol.com", "silver.cnfol.com", "gmxy.stock.cnfol.com",
			"hy.stock.cnfol.com", "gold.cnfol.com", "weixin.cnfol.com",
			"wm2.cnfol.com", "sbsc.stock.cnfol.com", "vip.g.cnfol.com",
			"hkstock.cnfol.com", "stock.cnfol.com", "gegu.stock.cnfol.com",
			"g.cnfol.com", "house.cnfol.com", "3g.cnfol.com",
			"live.stock.cnfol.com" };

	public CnfolContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		String cssQuerys[] = new String[] { ".ArticleCont", "#Content",
				"#__content" };
		Element element = null;
		for (String string : cssQuerys) {
			element = assignPossibleElement(document, string);
			if (element == null) {
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
		String cssQuerys[] = new String[] { "#editor_baidu", "#page" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
