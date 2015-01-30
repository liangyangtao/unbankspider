package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class ComYcwb extends BaseFilter {
	private String domain[] = new String[] { "www.ycwb.com", "lvyou.ycwb.com",
			"lingnan.ycwb.com", "myjjb.ycwb.com", "sjbdy.ycwb.com",
			"big5.ycwb.com", "rss.ycwb.com", "u.ycwb.com", "news.ycwb.com",
			"ent.ycwb.com", "sports.ycwb.com", "video.ycwb.com", "sp.ycwb.com",
			"bbs.ycwb.com", "yuqing.ycwb.com", "house.ycwb.com",
			"edu.ycwb.com", "money.ycwb.com", "culture.ycwb.com",
			"life.ycwb.com", "health.ycwb.com", "auto.ycwb.com", "3c.ycwb.com",
			"game.ycwb.com", "zt.ycwb.com", "haoren.ycwb.com" };

	public ComYcwb() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("div.main_article").first();
		String cssQuerys[] = new String[] { "div.author", "#displaypagenum",
				"div.space10" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
		return element;
	}

	@Override
	public void formatElements(Element maxTextElement) {
		super.formatElements(maxTextElement);
	}

	@Override
	public void saveImage(Element maxTextElement, String url) {

		super.saveImage(maxTextElement, url);

	}

	@Override
	public String replaceStockCode(String content) {
		return super.replaceStockCode(content);
	}

	@Override
	public String replaceSpechars(String content) {
		String str = super.replaceSpechars(content);
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
