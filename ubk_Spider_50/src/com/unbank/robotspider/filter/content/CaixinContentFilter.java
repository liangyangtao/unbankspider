package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CaixinContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(CaixinContentFilter.class);
	private String domain[] = new String[] { "other.caixin.com",
			"weekly.caixin.com", "promote.caixin.com", "shop.caixin.com",
			"economy.caixin.com", "mobile.caixin.com", "bijiao.caixin.com",
			"i.caixin.com", "user.caixin.com", "culture.caixin.com",
			"opinion.caixin.com", "blog.caixin.com",
			"international.caixin.com", "enjoy.caixin.com",
			"special.caixin.com", "reading.caixin.com", "datanews.caixin.com",
			"china.caixin.com", "conferences.caixin.com",
			"lidawei.blog.caixin.com", "photos.caixin.com",
			"hechengying.blog.caixin.com", "finance.caixin.com",
			"topics.caixin.com", "zhangyin.blog.caixin.com",
			"energy.caixin.com", "service.caixin.com", "cnbc.caixin.com",
			"magazine.caixin.com", "file.caixin.com", "corp.caixin.com",
			"companies.caixin.com", "english.caixin.com", "www.caixin.com",
			"sunyue.blog.caixin.com", "video.caixin.com", "life.caixin.com",
			"xuben.blog.caixin.com", "download.caixin.com",
			"cnreform.caixin.com", "taoduanfang.blog.caixin.com" };

	public CaixinContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}
	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#the_content");
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
		String cssQuerys[] = new String[] { "#conTit", "#subhead", "#pageNext",
				".content-tag", ".moreReport", ".idetor", ".pip",
				".media_pic > dd" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
