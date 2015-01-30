package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class JrjContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(JrjContentFilter.class);

	private String domain[] = new String[] { "blog.jrj.com.cn",
			"summary.jrj.com.cn", "pic.jrj.com.cn", "www.jrj.com",
			"www.jrj.com.cn", "gold.jrj.com.cn", "zhibo.jrj.com.cn",
			"usstock.jrj.com.cn", "tools.ecp888.jrj.com.cn",
			"emoney.jrj.com.cn", "fund.jrj.com.cn", "hk.jrj.com.cn",
			"trust.jrj.com.cn", "money.jrj.com.cn", "finance.jrj.com.cn",
			"same.jrj.com.cn", "bbs.jrj.com.cn", "bank.jrj.com.cn",
			"futures.jrj.com.cn", "house.jrj.com.cn", "v.jrj.com.cn",
			"www.m.jrj.com.cn", "www.jrj.com.cn", "biz.jrj.com.cn",
			"forex.jrj.com.cn", "i.jrj.com.cn", "stock.jrj.com.cn",
			"insurance.jrj.com.cn", "auto.jrj.com.cn", "istock.jrj.com.cn" };

	public JrjContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}
	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, ".textmain");
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
		String cssQuerys[] = new String[] { ".textimg",".jj_more_new" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
