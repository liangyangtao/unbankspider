package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CcidnetContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(CcidnetContentFilter.class);

	private String domain[] = new String[] { "project.ccidnet.com",
			"expert.ccidnet.com", "jd.ccidnet.com", "iot.ccidnet.com",
			"smb.ccidnet.com", "eco.ccidnet.com", "cloud.ccidnet.com",
			"news.ccidnet.com", "bbs.ccidnet.com", "smartcity.ccidnet.com",
			"blog.ccidnet.com", "product.ccidnet.com", "pbxs.ccidnet.com",
			"3s.ccidnet.com", "register.ccidnet.com", "mi.ccidnet.com",
			"education.ccidnet.com", "finance.ccidnet.com", "cio.ccidnet.com",
			"nssp.ccidnet.com", "bdt.ccidnet.com", "cul.ccidnet.com",
			"miit.ccidnet.com", "info.ccidnet.com", "tech.ccidnet.com",
			"auto.ccidnet.com", "internet.ccidnet.com", "www.ccidnet.com",
			"bigdata.ccidnet.com", "industry.ccidnet.com",
			"xiazai.ccidnet.com", "wlw.ccidnet.com", "comm.ccidnet.com",
			"service.ccidnet.com" };

	public CcidnetContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, ".temp");
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
		// String cssQuerys[] = new String[] { "#embed_hzh_div", ".ifengLogo",
		// ".txtLink" };
		// for (String cssQuery : cssQuerys) {
		// removeNoNeedElement(contentElement, cssQuery);
		// }
	}

}
