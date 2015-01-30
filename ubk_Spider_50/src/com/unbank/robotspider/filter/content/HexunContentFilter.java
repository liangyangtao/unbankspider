package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class HexunContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(HexunContentFilter.class);

	private String[] domain = new String[] { "shoucang.hexun.com",
			"gold.hexun.com", "lizichi.blog.hexun.com",
			"stockdata.stock.hexun.com", "data.house.hexun.com",
			"bbs.hexun.com", "peikesu.blog.hexun.com", "3g.hexun.com",
			"data.trust.hexun.com", "futures.hexun.com", "www.hexun.com.tw",
			"auto.hexun.com", "datainfo.stock.hexun.com", "money.hexun.com",
			"tech.hexun.com", "vip.px.hexun.com", "edu.tv.hexun.com",
			"tv.hexun.com", "img.hexun.com", "funds.hexun.com",
			"house.hexun.com", "suzhou.house.hexun.com", "f.blog.hexun.com",
			"quote.futures.hexun.com", "renwu.hexun.com", "mac.hexun.com",
			"fujian.hexun.com", "iof.hexun.com", "qhcl.hexun.com",
			"integration.hexun.com", "hunan.hexun.com", "data.bank.hexun.com",
			"sichuan.hexun.com", "wizard.stock.hexun.com",
			"hkquote.stock.hexun.com", "blog.hexun.com", "cvs.hexun.com",
			"app.hexun.com", "stock.hexun.com", "quote.forex.hexun.com",
			"t.hexun.com", "c.hexun.com", "jiangsu.hexun.com",
			"travel.hexun.com", "25006776.blog.hexun.com", "wenda.hexun.com",
			"lcal.money.hexun.com", "vol.stock.hexun.com", "bank.hexun.com",
			"pe.hexun.com", "chat.hexun.com", "pension.hexun.com",
			"opinion.hexun.com", "softserver1.stock.hexun.com",
			"yuqing.hexun.com", "insurance.money.hexun.com", "jucai.hexun.com",
			"data.book.hexun.com", "kd.hexun.com", "tax.hexun.com",
			"mchenyun.blog.hexun.com", "m.hexun.com", "quote.hexun.com",
			"www.hexun.com", "calendar.hexun.com", "digi.hexun.com",
			"gzqh.hexun.com", "roll.hexun.com", "fangzhouzi.blog.hexun.com",
			"focus.funds.hexun.com", "media.hexun.com", "guba.hexun.com",
			"corp.hexun.com", "news.hexun.com", "data.futures.hexun.com",
			"insurance.hexun.com", "pxpt.hexun.com", "licaike.hexun.com",
			"px.hexun.com", "hk.stock.hexun.com", "book.hexun.com",
			"henan.hexun.com", "jingzhi.funds.hexun.com",
			"315.stock.hexun.com", "xianhuo.hexun.com", "forex.hexun.com",
			"blogeditor.blog.hexun.com", "bond.hexun.com",
			"yanbao.stock.hexun.com", "bond.money.hexun.com",
			"paiming.funds.hexun.com", "bschool.hexun.com",
			"zhibiao.hexun.com", "cfsd.hexun.com", "trust.hexun.com",
			"qizhi.hexun.com", "msn.gold.hexun.com" };

	public HexunContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {

		String cssQuerys[] = new String[] { "#zoom", "div#artibody",
				"#BlogArticleDetail" };
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

		String cssQuerys[] = new String[] {
				"#artibody > font[face=KAITI_GB2312]", "#arctTailMarkCopy",
				"#page2011nav", "link", "#arctTailMark",
				"div[style=text-align:right;font-size:12px]", "div.clear",
				"img[src=http://i4.hexunimg.cn/2014-07-17/166722863.jpg]" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
