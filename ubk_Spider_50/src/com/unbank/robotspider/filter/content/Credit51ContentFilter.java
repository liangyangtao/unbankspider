package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class Credit51ContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(Credit51ContentFilter.class);

	private String domain[] = new String[] { "hd.51credit.com",
			"jn.51credit.com", "lz.51credit.com", "sso.51credit.com",
			"ccb.51credit.com", "pingce.51credit.com", "longyan.51credit.com",
			"licai.51credit.com", "spd.51credit.com", "wangdian.51credit.com",
			"cc.51credit.com", "bj.51credit.com", "hxb.51credit.com",
			"daikuan.51credit.com", "xn.51credit.com", "sjz.51credit.com",
			"bos.51credit.com", "tj.51credit.com", "bocomm.51credit.com",
			"news.51credit.com", "banks.51credit.com", "gz.51credit.com",
			"nc.51credit.com", "kaku.51credit.com", "wlmq.51credit.com",
			"cgb.51credit.com", "youhui.51credit.com", "ox.51credit.com",
			"cib.51credit.com", "wh.51credit.com", "xyk.51credit.com",
			"cmb.51credit.com", "citic.51credit.com", "cmbc.51credit.com",
			"p.51credit.com", "tl.51credit.com", "hz.51credit.com",
			"suzhou.51credit.com", "icbc.51credit.com", "www.51credit.com",
			"loancity.51credit.com", "xinyong.51credit.com",
			"zhuanti.51credit.com", "zh.51credit.com", "sh.51credit.com",
			"detail.51credit.com", "boc.51credit.com", "loan.51credit.com",
			"sz.51credit.com", "ceb.51credit.com", "abc.51credit.com",
			"chedai.51credit.com", "bbs.51credit.com", "jifen.51credit.com",
			"nj.51credit.com", "pingan.51credit.com", "shantou.51credit.com",
			"yhj.51credit.com" };

	public Credit51ContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		String cssQuerys[] = new String[] { ".yhj_conbox", ".con_content" };
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
