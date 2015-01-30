package com.unbank.robotspider.action.filter;

import java.util.regex.Pattern;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComXinhuanet extends BaseFilter {
	private String domain[] = new String[] { "www.bj.xinhuanet.com",
			"www.xinhuanet.com", "news.xinhuanet.com", "www.sd.xinhuanet.com" };

	public ComXinhuanet() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = null;
		String domainQuerys[] = new String[] { "div#contentblock", "#content",
				".p1" };
		for (String string : domainQuerys) {
			element = document.select(string).first();
			if (element != null) {
				break;
			}
		}

		String cssQuerys[] = new String[] { "#div_page_roll1", "#div_currpage",
				"script" };
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

		String str = super.replaceStockCode(content);
		String regex = "\\(\\d{1,4}\\.\\d{1,2}，(-|/+)\\d{1,2}\\.\\d{1,2}，(-|/+)\\d{1,2}\\.\\d{1,2}%\\)";
		Pattern pattern = Pattern.compile(regex);
		String s = pattern.matcher(str).replaceAll("");
		s = s.replaceAll(
				"\\(\\d{0,8}</v>股吧</v></v></v>,</v>行情</v></v></v>,</v>资讯</v></v></v>,</v>主力买卖</v></v></v>\\)",
				"");
		s = s.replaceAll("(市值重估)", "");
		s = s.replaceAll("(代码:\\d{0,8})", "");
		return s;
	}

	@Override
	public String replaceSpechars(String content) {
		return super.replaceSpechars(content);
	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
