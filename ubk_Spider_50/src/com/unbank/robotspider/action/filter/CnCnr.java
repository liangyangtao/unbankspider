package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class CnCnr extends BaseFilter {
	private String domain[] = new String[] { "www.cnr.cn", "news.cnr.cn",
			"finance.cnr.cn", "auto.cnr.cn", "ent.cnr.cn", "pic.cnr.cn",
			"tv.cnr.cn", "tech.cnr.cn", "military.cnr.cn", "travel.cnr.cn",
			"sports.cnr.cn", "health.cnr.cn", "gongyi.cnr.cn", "aod.cnr.cn",
			"edu.cnr.cn", "bbs.cnr.cn", "lady.cnr.cn", "life.cnr.cn",
			"country.cnr.cn", "roll.cnr.cn", "china.cnr.cn",
			"musicradio.cnr.cn", "old.cnr.cn", "fm1018.cnr.cn",
			"hxradio.cnr.cn", "photo.cnr.cn", "cbu.cnr.cn", "cneb.cnr.cn",
			"ygzq.cnr.cn", "diaocha.cnr.cn", "gb.cnr.cn" };

	public CnCnr() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select(".sanji_left").first();
		if (element == null) {
			element = document.select(".TRS_Editor").first();
		}

		String cssQuerys[] = new String[] {
				"img[oldsrc=W020140911342885204238.jpg]",
				"div.blank10",
				"div.f14",
				".blank5",
				"img[style=border-left-width: 0px; border-right-width: 0px; border-bottom-width: 0px; border-top-width: 0px]" };
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
