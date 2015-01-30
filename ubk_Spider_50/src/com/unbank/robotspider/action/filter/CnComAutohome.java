package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnComAutohome extends BaseFilter {
	private String domain[] = new String[] { "www.autohome.com.cn",
			"car.autohome.com.cn", "csh.autohome.com.cn", "v.autohome.com.cn",
			"shuoke.autohome.com.cn", "mall.autohome.com.cn",
			"buy.autohome.com.cn", "dealer.autohome.com.cn",
			"zhidao.autohome.com.cn", "club.autohome.com.cn",
			"k.autohome.com.cn", "diandong.autohome.com.cn" };

	public CnComAutohome() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#articleContent").first();
		String cssQuerys[] = new String[] { "script",
				"span[style=display:none]" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
		String textQuerys[] = new String[] { "原标题:" };
		for (String string : textQuerys) {
			removeNoNeedTextElement(element, string);
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
