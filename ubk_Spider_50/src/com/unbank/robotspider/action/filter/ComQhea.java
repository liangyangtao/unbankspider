package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComQhea extends BaseFilter {
	private String domain[] = new String[] { "www.qhea.com" };

	public ComQhea() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select(".main_left_cont").first();
		String cssQuerys[] = new String[] { "script",
				"span[style=display:none]", "h1", ".news_laiyuan", "#bdshare",
				".pageLink", "#saypl", ".zhaiyao", ".bshare-custom",
				".news_cont_ad_r" };
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
		str = str.replaceAll("【家电资讯网-技术法规家电新闻 \\d{4}-\\d{2}-\\d{2}】", "");
		str = str
				.replace(
						"<p>想了解更多关于墨西哥能源部采纳热水器新安全及标识标准的信息，请随时关注家电资讯网http://www.qhea.com/</p>",
						"");
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
