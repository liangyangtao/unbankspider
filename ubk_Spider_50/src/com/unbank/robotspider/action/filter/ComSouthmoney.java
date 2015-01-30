package com.unbank.robotspider.action.filter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComSouthmoney extends BaseFilter {
	private String domain = "www.southmoney.com";

	public ComSouthmoney() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select(".articleCon").first();
		String cssQuerys[] = new String[] { "#articleText > div:nth-child(1)",
				".det_read", "#page_self", ".multipage", "script",
				"#BAIDU_DUP_wrapper_u1571191_0", ".articleFoot" };
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
		str = str.replace("(南方财富网理财频道)", "");
		str = str.replace("南方财富网微信号：<strong>southmoney</strong>", "");
		return str;
	}

	@Override
	public String addTag(String content) {
		if (content == null) {
			return null;
		}
		Document document2 = Jsoup.parse(content);

		Elements elements = document2.select("p");
		for (Element element : elements) {

			if (element.children().size() == 0) {
				String temp = element.text().trim();
				temp = temp.replace("　", "");
				element.text(temp.trim());
			}
		}
		content = document2.body().toString();
		content = content.replace("\n", "");
		content = content.replace("\r", "");

		content = content.replace("<body>", "");
		content = content.replace("</body>", "");
		content = content.replace("　", "");
		content = "<div style='text-align:left;'> " + content + "</div>";
		return content;
	}

}
