package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class SinaContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(SinaContentFilter.class);

	private String domain[] = new String[] { "mail.sina.net",
			"tags.finance.sina.com.cn", "gongyi.sina.com.cn",
			"news.sina.com.cn", "sports.sina.com.cn", "finance.sina.com.cn",
			"games.sina.com.cn", "astro.sina.com.cn",
			"yingxun.ent.sina.com.cn", "vip.stock.finance.sina.com.cn",
			"weather.news.sina.com.cn", "i.finance.sina.com.cn",
			"blog.sina.com.cn", "fashion.sina.com.cn", "health.sina.com.cn",
			"tech.sina.com.cn", "edu.sina.com.cn", "digi.sina.com.cn",
			"help.sina.com.cn", "guba.sina.com.cn", "baby.sina.com.cn",
			"collection.sina.com.cn", "house.sina.com.cn", "book.sina.com.cn",
			"app.sina.com.cn", "ent.sina.com.cn", "data.auto.sina.com.cn",
			"history.sina.com.cn", "eladies.sina.com.cn",
			"city.finance.sina.com.cn", "m.sina.com.cn",
			"emarketing.sina.com.cn", "mail.sina.com.cn", "ka.sina.com.cn",
			"tvguide.ent.sina.com.cn", "auto.sina.com.cn", "english.sina.com",
			"video.sina.com.cn", "sms.sina.com.cn",
			"comment5.news.sina.com.cn", "www.sina.com.cn", "sea.sina.com.cn",
			"travel.sina.com.cn", "slide.finance.sina.com.cn",
			"login.sina.com.cn", "corp.sina.com.cn" };

	public SinaContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}
	}

	public Element getContentNode(Document document) {
		String cssQuerys[] = new String[] { "div#artibody", "#center",
				"#divContent", "#show_txt", "#textCont",
				"#sina_keyword_ad_area2" };
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
		String cssQuerys[] = new String[] { "div#ad_44099",
				".finance_app_zqtg", ".muLink", ".divstockguba",
				".pagebox_div", ".d_impLink", ".Mblk_09" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
