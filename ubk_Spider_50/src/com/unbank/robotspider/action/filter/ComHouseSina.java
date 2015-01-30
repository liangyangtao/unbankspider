package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class ComHouseSina extends BaseFilter {
	private String domain = "bj.house.sina.com.cn";

	public ComHouseSina() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		return super.removeNoNeedHtmlElement(url, document, title);
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
		int index = str.indexOf("文/");
		if (index > 0) {
			return str.substring(0, index);
		} else {
			index = str.indexOf("北京商报记者");
			if (index > 0) {
				return str.substring(0, index);
			} else {
				index = str.indexOf("北商报记者");
				if (index > 0) {
					return str.substring(0, index);
				} else {
					return str;
				}
			}

		}

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

	// 得到某元素下孩子节点长度最大的Element
	public Element getMaxTextElement(String url, String title,
			Element parentElement) {
		if (parentElement == null) {
			return null;
		}

		// http://bj.house.sina.com.cn
		// div.zwcontent
		// h2.f24
		// div.tc
		// div#bjbodymatterbox
		// div#show_txt
		// div.zwcontent > div:nth-child(5)
		// div.headlines_news
		// div.adcenter
		// div#t06
		Element temp = parentElement.select("div.zwcontent").first();

		Element removeElement = temp.select("h2.f24").first();
		if (removeElement != null) {
			removeElement.remove();
		}
		Element removeElement1 = temp.select("div.tc").first();
		if (removeElement1 != null) {
			removeElement1.remove();
		}
		Element removeElement2 = temp
				.select("div.zwcontent > div:nth-child(5)").first();
		if (removeElement2 != null) {
			removeElement2.remove();
		}
		Element removeElement3 = temp.select("div.headlines_news").first();
		if (removeElement3 != null) {
			removeElement3.remove();
		}
		Element removeElement4 = temp.select("div.adcenter").first();
		if (removeElement4 != null) {
			removeElement4.remove();
		}
		Element removeElement5 = temp.select("div#t06").first();
		if (removeElement5 != null) {
			removeElement5.remove();
		}
		Element removeElement6 = temp.select("div.mt10").first();

		if (removeElement6 != null) {
			removeElement6.remove();
		}
		Element element = temp.select("p").last();
		Elements elements = element.children();
		for (Element element2 : elements) {
			if (element2.tagName().equals("a")
					&& element2.children().first().tagName().equals("b")) {
				element2.remove();
			}
		}

		return temp;

	}

}
