package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class Com163 extends BaseFilter {

	private String domain[] = new String[] { "money.163.com", "tech.163.com" };

	public Com163() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Elements elements = document.select("div#endText");
		Element first_element = elements.first();
		for (Element element : elements) {
			if (element.toString().equals(first_element.toString())) {
				continue;
			} else {
				first_element.append(element.toString());
			}

		}
		remove163LinkNode(first_element);
		String cssQuerys[] = new String[] { "div.ep-source", "div.gg200x300" };
		for (String string : cssQuerys) {
			removeNoNeedElement(first_element, string);
		}
		return first_element;

	}

	private void remove163LinkNode(Element element) {
		Element bElement = element.select("b").first();
		if (bElement == null) {
			return;
		}
		Element pElement = bElement.parent();
		Elements elements = pElement.siblingElements();
		int i = pElement.elementSiblingIndex();
		boolean isRemove = true;
		for (int j = i; j < elements.size(); j++) {
			Element element2 = elements.get(j);
			if (element2.tagName().equals("p")) {
				Element element3 = element2.children().first();
				if (element3 == null) {
					if (element2.text().trim().isEmpty()) {
						continue;
					} else {
						isRemove = false;
						break;
					}
				}
				if (element3.tagName().equals("a")
						|| element3.tagName().equals("b")) {
				} else {
					isRemove = false;
					break;
				}

			}
		}
		if (isRemove) {
			for (int j = i; j < elements.size(); j++) {
				elements.get(j).remove();
			}
			bElement.remove();
		}
	}

	@Override
	public void formatElements(Element maxTextElement) {
		super.formatElements(maxTextElement);
	}

	@Override
	public void saveImage(Element maxTextElement, String url) {

		Elements elements = maxTextElement.select("img");
		for (Element element : elements) {
			Attributes attributes = element.attributes();
			for (Attribute attribute : attributes) {
				if (attribute.getKey().equals("alt")) {
					element.removeAttr(attribute.getKey());
					break;
				}

			}
		}

		super.saveImage(maxTextElement, url);

	}

	@Override
	public String replaceStockCode(String content) {
		return super.replaceStockCode(content);
	}

	@Override
	public String replaceSpechars(String content) {
		String str = super.replaceSpechars(content);
		str = str.replace("(微博</v>)", "");
		str = str.replace("(www.stcn.com)", "");

		String[] spechars = new String[] { "重点推荐", "延伸阅读", "相关报道", "商业专栏",
				"证券要闻", "行业动态", "公司新闻", "相关新闻", "往期回顾", "今日消息", "机构策略",
				"【网易网友限时独享】", "专题：", "(原标题" };
		for (String string : spechars) {
			int index = str.indexOf(string);
			if (index > 0) {
				str = str.substring(0, index);
			}
		}
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
