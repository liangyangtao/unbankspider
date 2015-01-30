package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class ChinanewsContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(ChinanewsContentFilter.class);

	private String domain[] = new String[] { "www.hlj.chinanews.com",
			"ad.chinanews.com.cn", "www.gs.chinanews.com",
			"www.shx.chinanews.com", "www.wx.chinanews.com",
			"wza.chinanews.com", "hr.chinanews.com", "bbs.chinanews.com",
			"www.gz.chinanews.com", "stock.chinanews.com",
			"www.heb.chinanews.com", "sports.chinanews.com",
			"www.fj.chinanews.com", "world.chinanews.com",
			"www.sd.chinanews.com", "www.qd.chinanews.com",
			"www.yn.chinanews.com", "www.tc.chinanews.com",
			"game.chinanews.com", "www.ha.chinanews.com",
			"www.bj.chinanews.com", "house.chinanews.com",
			"www.gd.chinanews.com", "www.nb.chinanews.com",
			"edu.chinanews.com", "ent.chinanews.com", "www.hn.chinanews.com",
			"health.chinanews.com", "www.jx.chinanews.com", "t.chinanews.com",
			"www.zj.chinanews.com", "zxnk.chinanews.com",
			"www.sh.chinanews.com", "www.hi.chinanews.com",
			"www.sc.chinanews.com", "big5.chinanews.com",
			"www.fz.chinanews.com", "www.gdmm.chinanews.com",
			"cjzx.chinanews.com", "fortune.chinanews.com",
			"www.zz.chinanews.com", "www.xj.chinanews.com",
			"wine.chinanews.com", "auto.chinanews.com", "www.nd.chinanews.com",
			"feedback.chinanews.com", "cul.chinanews.com", "www.chinanews.com",
			"www.jl.chinanews.com", "www.nmg.chinanews.com",
			"www.gdzj.chinanews.com", "it.chinanews.com",
			"www.lz.chinanews.com", "life.chinanews.com",
			"www.gx.chinanews.com", "news.chinanews.com",
			"www.sx.chinanews.com", "mil.chinanews.com",
			"interview.chinanews.com", "www.qz.chinanews.com",
			"www.hb.chinanews.com", "www.gdst.chinanews.com",
			"www.cz.chinanews.com", "www.qh.chinanews.com",
			"www.ln.chinanews.com", "channel.chinanews.com",
			"photo.chinanews.com", "m.chinanews.com", "energy.chinanews.com",
			"www.ah.chinanews.com", "www.cq.chinanews.com",
			"www.js.chinanews.com", "finance.chinanews.com",
			"www.bt.chinanews.com" };

	public ChinanewsContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, ".left_zw");
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
		String cssQuerys[] = new String[] { "table", "#function_code_page" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
