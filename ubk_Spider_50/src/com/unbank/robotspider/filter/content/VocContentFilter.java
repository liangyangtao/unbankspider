package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class VocContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(VocContentFilter.class);

	private String domain[] = new String[] { "www.voc.com.cn",
			"hnrbjt.voc.com.cn", "m.voc.com.cn", "app.voc.com.cn",
			"www.big5.voc.com.cn", "passport.voc.com.cn", "news.voc.com.cn",
			"opinion.voc.com.cn", "hunan.voc.com.cn", "society.voc.com.cn",
			"history.voc.com.cn", "cj.voc.com.cn", "315.voc.com.cn",
			"cxpd.voc.com.cn", "bbs.voc.com.cn", "blog.voc.com.cn",
			"js.voc.com.cn", "yule.voc.com.cn", "ts.voc.com.cn",
			"finance.voc.com.cn", "xyzl.voc.com.cn", "hsyt.voc.com.cn",
			"tv.voc.com.cn", "pic.voc.com.cn", "book.voc.com.cn",
			"gy.voc.com.cn", "town.voc.com.cn", "xjz.voc.com.cn",
			"health.voc.com.cn", "dsj.voc.com.cn", "365.voc.com.cn",
			"internet.voc.com.cn", "car.voc.com.cn", "qp.voc.com.cn",
			"hnrb.voc.com.cn", "sxdsb.voc.com.cn", "epaper.voc.com.cn",
			"hszz.voc.com.cn", "xrk.voc.com.cn", "fxh.voc.com.cn",
			"cd.voc.com.cn", "xt.voc.com.cn", "hy.voc.com.cn", "sy.voc.com.cn",
			"ld.voc.com.cn", "zz.voc.com.cn", "yy.voc.com.cn", "cz.voc.com.cn",
			"yi.voc.com.cn", "wenming.voc.com.cn", "zt.voc.com.cn",
			"dianying.voc.com.cn", "wxhn.voc.com.cn",
			"article.blog.voc.com.cn", "guanliyuan.blog.voc.com.cn",
			"longzhoufu.blog.voc.com.cn", "linlu.blog.voc.com.cn",
			"hlj1947.blog.voc.com.cn", "wangxubo1988.blog.voc.com.cn",
			"ux.voc.com.cn", "100km.voc.com.cn", "f.voc.com.cn",
			"ganxi.voc.com.cn", "sh.voc.com.cn", "123.voc.com.cn",
			"yxzn.voc.com.cn", "hospital.voc.com.cn", "abc.voc.com.cn",
			"mingyi.voc.com.cn", "120cs.voc.com.cn", "120hn.voc.com.cn",
			"179.voc.com.cn", "120jsk.voc.com.cn", "120gb.voc.com.cn",
			"163.voc.com.cn", "yl.voc.com.cn", "zhaoshang.voc.com.cn",
			"yg.voc.com.cn", "gb.voc.com.cn", "edu.voc.com.cn",
			"hr.voc.com.cn", "wpa.qq.com" };

	public VocContentFilter() {
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
