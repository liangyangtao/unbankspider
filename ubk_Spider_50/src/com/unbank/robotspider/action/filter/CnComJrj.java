package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;
import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Service
public class CnComJrj extends BaseFilter {
	private String domain[] = new String[] { "bank.jrj.com.cn",
			"stock.jrj.com.cn", "finance.jrj.com.cn", "auto.jrj.com.cn",
			"opinion.jrj.com.cn", "fund.jrj.com.cn", "forex.jrj.com.cn",
			"itfinance.jrj.com.cn" ,"trust.jrj.com.cn","bankpro.jrj.com.cn","house.jrj.com.cn"};

	public CnComJrj() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		if (document.select(".pnf").size() == 1) {
			url = url.split(".shtml")[0] + "-c.shtml";
			document = new JsoupNetFetcher().fetchText(url);
		}

		Element element = document.select(".textmain").first();
		String cssQuerys[] = new String[] { ".textimg", ".jj_more_new" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
		if (element == null) {
			return null;
		}
		Elements noneedElements = element.getElementsByAttributeValue("style",
				"DISPLAY: none");
		for (Element element2 : noneedElements) {
			element2.remove();
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
		String[] noNeeds = new String[] { "\\(([^\\(]*)?资讯([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?爱基([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?净值([^\\(|\\)]*)?\\)" };
		for (String string : noNeeds) {
			str = str.replaceAll(string, "");
		}

		return str;
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
