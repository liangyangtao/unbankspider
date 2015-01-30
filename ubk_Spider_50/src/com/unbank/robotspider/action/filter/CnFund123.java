package com.unbank.robotspider.action.filter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnFund123 extends BaseFilter {
	private String domain[] = new String[] { "tools.fund123.cn",
			"account.fund123.cn", "trade.fund123.cn", "user.fund123.cn",
			"my.fund123.cn", "stock.fund123.cn", "msg.fund123.cn",
			"about.fund123.cn", "www.fund123.cn", "market.fund123.cn",
			"news.fund123.cn", "gs.fund123.cn", "fund.fund123.cn",
			"dingtou.fund123.cn", "fx.fund123.cn", "bbs.fund123.cn",
			"blog.fund123.cn", "ly.fund123.cn", "fc.fund123.cn",
			"action.fund123.cn", "pay.fund123.cn", "trust.fund123.cn",
			"default.fund123.cn", "122.224.75.236", "shipan.fund123.cn" };

	public CnFund123() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#main_article").first();
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
		str = str.replace("[全文阅读]", "");
		str = str.replace("通过键盘前后键←→可实现翻页阅读", "");

		List<String> list = new ArrayList<String>();
		list.add("文章关键词");
		for (String s : list) {
			int index = str.indexOf(s);
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
