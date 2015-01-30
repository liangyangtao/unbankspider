package com.unbank.robotspider.action.filter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class ComFx678 extends BaseFilter {
	private String domain = "www.fx678.com";

	public ComFx678() {
		FilterLocator.getInstance().register(domain, this);
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("div.text").first();
		element.getElementsByTag("h4").remove();
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
		List<String> list=new ArrayList<String>();
		list.add("【在线答疑】");
		list.add("【定制服务】");
		for(int i=0;i<list.size();i++){
			str=str.replace(list.get(i), "");
		}
		
		return str;

	}

	@Override
	public String addTag(String content) {
		if (content == null) {
			return "";
		}
		Document document2 = Jsoup.parse(content);
		Element s_ele= document2.getElementsByTag("strong").first();
		if(s_ele.text().matches("汇通网\\d{1,2}月\\d{1,2}日讯――")){
			s_ele.remove();
		}
		
		wrapPToTextNodes(document2.body());
		content = document2.body().toString();
		content = content.replace("\n", "");
		content = content.replace("\r", "");
		content = content.replace("<body>", "");
		content = content.replace("</body>", "");
		content = "<div> " + content + "</div>";
		return content;
	}

}
