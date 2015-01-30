package com.unbank.robotspider.action.filter;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Component;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;
import com.unbank.robotspider.fetch.ImageFilter;
import com.unbank.robotspider.fetch.ImageUrlAndSizeFilter;
import com.unbank.robotspider.fetch.JsoupNetFetcher;

@Component
public class ComStockstar extends BaseFilter {
	private String domain[] = new String[] { "my.stockstar.com",
			"www.stockstar.com", "focus.stockstar.com",
			"finance.stockstar.com", "stock.stockstar.com",
			"fund.stockstar.com", "quote.stockstar.com",
			"resource.stockstar.com", "money.stockstar.com",
			"bank.stockstar.com", "forex.stockstar.com",
			"futures.stockstar.com", "bond.stockstar.com",
			"gold.stockstar.com", "if.stockstar.com", "special.stockstar.com",
			"school.stockstar.com", "b.stockstar.com", "live.stockstar.com",
			"same.stockstar.com", "news.stockstar.com", "info.stockstar.com",
			"pic.stockstar.com", "member.stockstar.com" };

	// 此网站 证券频道有 动态的广告图片，无法根本去除
	public ComStockstar() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select("div#container-article").first();
		String cssQuerys[] = new String[] {
				"div.tags",
				"#Page",
				"select",
				"#content_div_300_250",
				"img[src=http://i.ssimg.cn/ssupload/2014/10/17/a19fdf47d5dc4b19a144e05776f0aaf2.jpg]" };

		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}

		String textQuerys[] = new String[] { "更多精彩点评请浏览新浪理财师", "相关专题：" };
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

		Elements elements = maxTextElement.select("img");
		for (Element element : elements) {
			String imgSrc = element.absUrl("src");
			if (imgSrc == null || imgSrc.trim().isEmpty()) {
				continue;
			}
			// http://i.ssimg.cn/ssupload/2014/04/18/8e81f92c9c9b452cbfdd94867d553ab9.jpg
			if (imgSrc
					.equals("http://i.ssimg.cn/ssupload/2014/04/18/8e81f92c9c9b452cbfdd94867d553ab9.jpg")) {
				element.remove();
				continue;
			}
			if (imgSrc
					.equals("http://i.ssimg.cn/ssupload/2014/08/19/01f2fd4ce50b429b8899f3ec0b322f8a.jpg")) {
				element.remove();
				continue;
			}
			ImageFilter filter = new ImageUrlAndSizeFilter(null, 0, 0);
			String imgUrl = new JsoupNetFetcher().fetchImage(imgSrc, filter);
			if (imgUrl != null && (!imgUrl.trim().isEmpty())) {
				element.attr("src", imgUrl);
				element.wrap("<p style='text-align:center;'></p>");
			} else {
				element.remove();
			}
		}
	}

	@Override
	public String replaceStockCode(String content) {
		return super.replaceStockCode(content);
	}

	@Override
	public String replaceSpechars(String content) {
		String str = super.replaceSpechars(content);
		str = str.replace("客户端查看最新行情", "");
		str = str.replace("...", "");
		str = str.replace(">>点击查询更多资金流向", "");
		String temp = StringUtils.substringBetween(str, "===本文导读===",
				"===全文阅读===");

		if (temp != null) {

			str = str.replace(temp, "");
			str = str.replace("===本文导读===", "");
			str = str.replace("===全文阅读===", "");
		}

		return str;
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
