package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class Com21cbh extends BaseFilter {

	private String domain[] = new String[] { "money.21cbh.com",
			"finance.21cbh.com", "jingji.21cbh.com" };

	public Com21cbh() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("div#Article").first();

		element.select("a.goindex").remove();

		Elements imgElements = element.getElementsByTag("img");
		if (imgElements.size() > 0) {
			for (Element img : imgElements) {
				if ("width: 600px; height: 183px;".equals(img.attr("style"))) {
					img.remove();
				}
			}
		}

		Elements aElements = element.getElementsByTag("a");
		if (imgElements.size() > 0) {
			for (Element a : aElements) {
				if ("返回21世纪网首页>>".equals(a.text())) {
					a.remove();
				}
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
		
		String[] subStr={"欢迎关注金融频道微信号"};
		for(String  s:subStr){
			if(str.contains(s)){
				str=str.substring(0, str.indexOf(s));
			}
		}
		
		str = str.replace("返回21世纪网首页>>", "");// 返回21世纪网首页>>
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
