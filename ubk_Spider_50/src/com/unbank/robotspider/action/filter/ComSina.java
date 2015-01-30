package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class ComSina extends BaseFilter {
	private String domain[] = new String[] { "www.sina.com.cn",
			"news.sina.com.cn", "sports.sina.com.cn", "ent.sina.com.cn",
			"finance.sina.com.cn", "tech.sina.com.cn", "video.sina.com.cn",
			"blog.sina.com.cn", "book.sina.com.cn", "edu.sina.com.cn",
			"fashion.sina.com.cn", "baby.sina.com.cn", "health.sina.com.cn",
			"astro.sina.com.cn", "collection.sina.com.cn",
			"eladies.sina.com.cn", "auto.sina.com.cn", "house.sina.com.cn",
			"history.sina.com.cn", "games.sina.com.cn", "weather.sina.com.cn",
			"mail.sina.com.cn", "iask.sina.com.cn", "login.sina.com.cn",
			"tags.tech.sina.com.cn", "app.sina.com.cn", "gongyi.sina.com.cn",
			"ka.sina.com.cn", "digi.sina.com.cn", "travel.sina.com.cn",
			"data.auto.sina.com.cn", "yingxun.ent.sina.com.cn",
			"tvguide.ent.sina.com.cn", "city.finance.sina.com.cn",
			"mail.sina.net", "slide.tech.sina.com.cn", "sea.sina.com.cn",
			"comment5.news.sina.com.cn", "corp.sina.com.cn",
			"emarketing.sina.com.cn", "english.sina.com", "help.sina.com.cn" };

	public ComSina() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("div#artibody").first();
		if (element == null) {
			element = document.select(".mwl-content").first();
		}

		String[] cssQuerys = new String[] {
				"div#ad_44099",
				"div#artibody > p > span[style=font-family: KaiTi_GB2312,KaiTi;]",
				"span[style=font-family: KaiTi_GB2312, KaiTi;]", ".page" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
		if (element == null) {
			return null;
		}
		Elements elements = element.getElementsContainingOwnText("推荐阅读：");
		if (elements.size() == 1) {
			Element temp = elements.first();
			if (temp.siblingElements().size() == 0
					&& temp.parent().tagName().equals("p")) {
				temp = temp.parent();

				while (true) {
					Element element2 = temp.nextElementSibling();
					if (element2.children().size() == 1
							&& element2.children().first().tagName()
									.equals("a")) {
						element2.remove();
					} else {
						break;
					}
				}
				temp.remove();
			}

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

		String textQuerys[] = new String[] { "编辑：", "文/" };
		for (String string : textQuerys) {
			int index = str.indexOf(string);
			if (index > 0) {
				return str.substring(0, index);
			}
		}
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
