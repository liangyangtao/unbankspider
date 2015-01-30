package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComIqilu extends BaseFilter {
	private String domain[] = new String[] { "bbs.iqilu.com", "www.iqilu.com",
			"news.iqilu.com", "yx.iqilu.com", "paike.iqilu.com",
			"minsheng.iqilu.com", "v.iqilu.com", "caijing.iqilu.com",
			"i.iqilu.com", "ent.iqilu.com", "sports.iqilu.com",
			"pinglun.iqilu.com", "sh.qihoo.com", "jinan.iqilu.com",
			"qingdao.iqilu.com", "zibo.iqilu.com", "zaozhuang.iqilu.com",
			"dongying.iqilu.com", "yantai.iqilu.com", "weifang.iqilu.com",
			"jining.iqilu.com", "taian.iqilu.com", "weihai.iqilu.com",
			"rizhao.iqilu.com", "laiwu.iqilu.com", "linyi.iqilu.com",
			"dezhou.iqilu.com", "liaocheng.iqilu.com", "binzhou.iqilu.com",
			"heze.iqilu.com" };

	public ComIqilu() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#context").first();

		String cssQuerys[] = new String[] { "script",
				"img[src=http://img5.iqilu.com/c/u/2014/0917/1410919780368.jpg]" };
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
		String textQuerys[] = new String[] {
				"中国光大银行信用卡官方微信二维码：",
				"欢迎拨打齐鲁网财经频道、鲁商频道新闻热线0531-81694991，发送邮件至iqilucaijing@163.com，或登录齐鲁网官方微博(@齐鲁网)提供新闻线索。" };
		for (String string : textQuerys) {
			str = str.replace(string, "");
		}
		

		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
