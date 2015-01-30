package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class Jqka10ContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(Jqka10ContentFilter.class);

	private String domain[] = new String[] { "moni.10jqka.com.cn",
			"mobile.10jqka.com.cn", "field.10jqka.com.cn",
			"tools.fund.10jqka.com.cn", "trust.10jqka.com.cn",
			"stockpage.10jqka.com.cn", "sp.10jqka.com.cn",
			"bond.10jqka.com.cn", "basic.10jqka.com.cn", "q.10jqka.com.cn",
			"vote.10jqka.com.cn", "master.10jqka.com.cn", "fund.10jqka.com.cn",
			"school.10jqka.com.cn", "i.10jqka.com.cn", "fe.10jqka.com.cn",
			"yuanchuang.10jqka.com.cn", "t.10jqka.com.cn", "pic.10jqka.com.cn",
			"www.10jqka.com.cn", "activity.10jqka.com.cn",
			"focus.10jqka.com.cn", "blog.10jqka.com.cn", "sd.10jqka.com.cn",
			"kaihu.10jqka.com.cn", "ace.10jqka.com.cn",
			"goodsfu.10jqka.com.cn", "job.10jqka.com.cn",
			"download.10jqka.com.cn", "data.10jqka.com.cn",
			"lcjz.10jqka.com.cn", "futures.10jqka.com.cn",
			"news.10jqka.com.cn", "doctor.10jqka.com.cn", "vis.10jqka.com.cn",
			"stock.10jqka.com.cn", "ask.10jqka.com.cn",
			"research.10jqka.com.cn", "pass.10jqka.com.cn",
			"search.10jqka.com.cn", "invest.10jqka.com.cn" };

	public Jqka10ContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, ".art_main");
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
		String cssQuerys[] = new String[] { "#arctTailMark", "#back_web" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
