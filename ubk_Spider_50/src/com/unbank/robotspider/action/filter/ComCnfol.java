package com.unbank.robotspider.action.filter;

import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComCnfol extends BaseFilter {

	private String domain[] = new String[] { "help.cnfol.com",
			"tuiguang.cnfol.com", "p.cnfol.com", "3g.cnfol.com",
			"passport.cnfol.com", "www.cnfol.com", "news.cnfol.com",
			"stock.cnfol.com", "quotes.cnfol.com", "data.cnfol.com",
			"fund.cnfol.com", "forex.cnfol.com", "futures.cnfol.com",
			"xianhuo.cnfol.com", "gzqh.cnfol.com", "hkstock.cnfol.com",
			"money.cnfol.com", "collection.cnfol.com", "insurance.cnfol.com",
			"bank.cnfol.com", "bond.cnfol.com", "auto.cnfol.com",
			"house.cnfol.com", "video.cnfol.com", "roadshow.cnfol.com",
			"blog.cnfol.com", "bbs.cnfol.com", "g.cnfol.com",
			"zb.video.cnfol.com", "mall.cnfol.com", "jraq.cnfol.com",
			"wm2.cnfol.com", "caipiao.cnfol.com", "sc.stock.cnfol.com",
			"so.cnfol.com", "vip.g.cnfol.com", "fxs.cnfol.com",
			"special.cnfol.com", "gold.cnfol.com" };

	public ComCnfol() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select("div#Content").first();
		if (element == null) {
			element = document.select("div#__content").first();
		}

		String cssQuerys[] = new String[] { "span#editor_baidu", "select",
				"span[style=color:navy]",
				"img[src=http://c.cnfolimg.com/20141011/9/14302609682428767437.gif]" };
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
		Elements elements = maxTextElement.select("img");
		for (Element element : elements) {
			if (element.attr("src").endsWith(".gif")
					|| element.attr("src").endsWith(".png")) {
				element.remove();
			}
		}
		super.saveImage(maxTextElement, url);
	}

	@Override
	public String replaceStockCode(String content) {

		String str = super.replaceStockCode(content);
		String regex = "\\(\\d{1,4}\\.\\d{1,2}，(-|/+)\\d{1,2}\\.\\d{1,2}，(-|/+)\\d{1,2}\\.\\d{1,2}%\\)";
		Pattern pattern = Pattern.compile(regex);
		String s = pattern.matcher(str).replaceAll("");
		s = s.replaceAll("(代码:\\d{0,8})", "");
		return s;
	}

	@Override
	public String replaceSpechars(String content) {

		String str = super.replaceSpechars(content);
		String replaceQuerys[] = new String[] { "微信号：simujpd", "微信号：simuppw",
				"扫描关注私募精品店，为私募排排网客户提供专属服务平台，致力于为高净值客户精选对冲基金，优选私募精品！" };
		for (String string : replaceQuerys) {
			str = str.replace(string, "");
		}

		String[] spechars = new String[] { "(本文作者介绍" };
		for (String string : spechars) {
			int index = str.indexOf(string);
			if (index > 0) {
				str = str.substring(0, index);
			}
		}
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
