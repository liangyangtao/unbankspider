package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Component
public class CnCe extends BaseFilter {
	private String domain[] = new String[] { "wap.ce.cn", "big5.ce.cn",
			"en.ce.cn", "kr.ce.cn", "de.ce.cn", "ru.ce.cn", "es.ce.cn",
			"fr.ce.cn", "ja.ce.cn", "ar.ce.cn", "finance.ce.cn", 
			"auto.ce.cn", "nongye.ce.cn", "hj.ce.cn", "intl.ce.cn",
			"district.ce.cn", "city.ce.cn", "cen.ce.cn", "bbs.ce.cn",
			"fangtan.ce.cn", "blog.ce.cn", "views.ce.cn", "fz.ce.cn",
			"baby.ce.cn", "expo.ce.cn", "tech.ce.cn", "life.ce.cn",
			"edu.ce.cn", "ent.ce.cn", "health.ce.cn", "shuhua.ce.cn",
			"travel.ce.cn", "fashion.ce.cn", "foodsafety.ce.cn", "hsj.ce.cn",
			"kfq.ce.cn", "sme.ce.cn", "12365.ce.cn", "luxury.ce.cn",
			"cv.ce.cn", "zhujian.ce.cn", "cz.ce.cn", "yuqing.ce.cn",
			"gongyi.ce.cn", "sx.ce.cn", "he.ce.cn", "sn.ce.cn", "fj.ce.cn",
			"hi.ce.cn", "hlj.ce.cn", "tj.ce.cn", "sz.ce.cn", "cq.ce.cn",
			"qh.ce.cn", "sd.ce.cn", "sc.ce.cn", "xj.ce.cn", "bt.ce.cn",
			"hb.ce.cn", "hn.ce.cn", "jx.ce.cn", "sh.ce.cn", "js.ce.cn",
			"gd.ce.cn", "gx.ce.cn", "ah.ce.cn", "xz.ce.cn", "net.ce.cn",
			"europe.ce.cn", "book.ce.cn", "paper.ce.cn", "data.ce.cn"};

	public CnCe() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("div.TRS_Editor").first();
		String textQuerys[] = new String[] { "原标题", "相关:" };
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
		str = str.replace("【推荐阅读】", "");
		if (str.contains("此稿件为延展阅读内容")) {
			int index = str.indexOf("此稿件为延展阅读内容");
			str = str.substring(0, index);
		}
		return str;
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
