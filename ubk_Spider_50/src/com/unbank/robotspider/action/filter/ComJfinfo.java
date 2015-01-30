package com.unbank.robotspider.action.filter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComJfinfo extends BaseFilter {
	private String domain[] = new String[] { "www.jfinfo.com",
			"stock.jfinfo.com", "research.jfinfo.com", "money.jfinfo.com",
			"product.jfinfo.com", "school.jfinfo.com", "qianyue.jfinfo.com",
			"pay.jfinfo.com", "portfolio.jfinfo.com", "hq.jfinfo.com",
			"download.jfinfo.com", "finance.jfinfo.com", "news.jfinfo.com",
			"opp.jfinfo.com", "depth.jfinfo.com", "topnews.jfinfo.com",
			"fortune.jfinfo.com" };

	public ComJfinfo() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select("div.article_content").first();
		if (element == null) {
			element = document.select("div.articleCont").first();
		}
		String[] cssQuerys = new String[] { ".abstract", "table",
				"p[style=text-align: center;] > span[style=color: #ff0000;]",
				"p[style=TEXT-ALIGN: center] > span[style=color: #ff0000;]",
				"span", ".win8Box", ".Useful", "p.name", "p.article_summary",
				"p.top_wordsLink",
				"img[src=http://img.pamss.net/20140620/660X165-01.jpg]" };
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
		str = str
				.replace(
						"<strong>(</strong></v><strong>丰华财经</strong></v></v><strong>：</strong></v></v><strong>www.jfinfo.com</strong></v></v></v><strong>每天为股民精选一只优质股票，输入手机号码免费领取。</strong><strong>)</strong></v>",
						"");
		str = str.replace("返回丰华财经首页", "");

		String[] spechars = new String[] { "【相关阅读】", "【推荐阅读】", "【图解牛股】" };
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
		if (content == null) {
			return null;
		}
		Document document2 = Jsoup.parse(content);

		Elements elements = document2.select("p");
		for (Element element : elements) {

			if (element.children().size() == 0) {
				String temp = element.text().trim();
				temp = temp.replace("　", "");
				element.text(temp.trim());
			}
		}
		content = document2.body().toString();
		content = content.replace("\n", "");
		content = content.replace("\r", "");

		content = content.replace("<body>", "");
		content = content.replace("</body>", "");
		content = content.replace("　", "");
		content = "<div style='text-align:left;'> " + content + "</div>";
		return content;
	}

}
