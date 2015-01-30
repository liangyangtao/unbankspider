package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class EastmoneyContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(EastmoneyContentFilter.class);

	private String domain[] = new String[] { "renwu.eastmoney.com",
			"jijinba.eastmoney.com", "mingjia.eastmoney.com",
			"trust.eastmoney.com", "life.eastmoney.com", "www.eastmoney.com",
			"fundact.eastmoney.com", "caifumima.eastmoney.com",
			"auto.eastmoney.com", "edu.eastmoney.com", "iguba.eastmoney.com",
			"data.eastmoney.com", "corp.eastmoney.com", "stock.eastmoney.com",
			"jigou.eastmoney.com", "xianhuo.eastmoney.com", "hk.eastmoney.com",
			"media.eastmoney.com", "biz.eastmoney.com", "js1.eastmoney.com",
			"video.eastmoney.com", "blog.eastmoney.com", "guba.eastmoney.com",
			"global.eastmoney.com", "money.eastmoney.com", "t.eastmoney.com",
			"bond.eastmoney.com", "roll.eastmoney.com", "so.eastmoney.com",
			"quote.eastmoney.com", "bbs.eastmoney.com", "js5.eastmoney.com",
			"fund.eastmoney.com", "forex.eastmoney.com",
			"futures.eastmoney.com", "gold.eastmoney.com",
			"topic.eastmoney.com", "bank.eastmoney.com", "cp.eastmoney.com",
			"qiche.eastmoney.com", "insurance.eastmoney.com",
			"kuaixun.eastmoney.com", "finance.eastmoney.com",
			"enterprise.eastmoney.com" };

	public EastmoneyContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}
	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#ContentBody");
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
		String cssQuerys[] = new String[] { ".EM_imgBaList_box", ".reading" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
