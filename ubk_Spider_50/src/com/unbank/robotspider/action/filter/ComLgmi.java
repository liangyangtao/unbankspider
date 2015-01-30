package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComLgmi extends BaseFilter {
	private String domain[] = new String[] { "www.lgmi.com",
			"gangcai.lgmi.com", "luliao.lgmi.com", "tegang.lgmi.com",
			"gangjiegou.lgmi.com", "youse.lgmi.com", "gangchang.lgmi.com",
			"kucun.lgmi.com", "tongji.lgmi.com", "qihuo.lgmi.com",
			"info.lgmi.com", "guoji.lgmi.com", "shidian.lgmi.com",
			"meeting.lgmi.com", "renwu.lgmi.com", "sousuo.lgmi.com",
			"jiancai.lgmi.com", "guancai.lgmi.com", "xingcai.lgmi.com",
			"bancai.lgmi.com", "tudu.lgmi.com", "sh.lgmi.com",
			"henan.lgmi.com", "shandong.lgmi.com", "zhishu.lgmi.com",
			"gangtie2008.lgmi.com", "member.lgmi.com", "app.lgmi.com",
			"kh.lgmi.com", "b.qq.com", "web.lgmi.com", "price.lgmi.com" };

	public ComLgmi() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select(".t3").first();
		if (element == null) {
			element = document.select(".hei14").first();
		}
		String cssQuerys[] = new String[] { "script" };
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
