package com.unbank.robotspider.action.filter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComTakungpao extends BaseFilter {
	private String domain[] = new String[] { "news.takungpao.com",
			"finance.takungpao.com" };

	public ComTakungpao() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select(".tpk_text").first();
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
//		logger.info(str);
//		List<String> list = new ArrayList<String>();
//		list.add("【大公报记者");
//		list.add("【<strong>大公报</strong>记者");
//		list.add("【<strong>大公报</strong>实习记者");
//		list.add("发稿：");
//
//		for (int i = 0; i < list.size(); i++) {
//			String s = list.get(i);
//			int index = str.indexOf(s);
//			if (index > 0) {
//				str = str.substring(0, index);
//			}
//		}
//
//		List<String> remStrList = new ArrayList<String>();
//		remStrList.add("【<strong>大公报</strong>讯】");
//		for (int i = 0; i < remStrList.size(); i++) {
//			String s = remStrList.get(i);
//			if (str.contains(s)) {
//				str = str.replace(s, "");
//			}
//		}
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
