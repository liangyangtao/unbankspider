package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComXinhua08 extends BaseFilter {
	private String domain[] = new String[] { "www.xinhua08.com",
			"news.xinhua08.com", "world.xinhua08.com", "dc.xinhua08.com",
			"rmb.xinhua08.com", "forex.xinhua08.com", "futures.xinhua08.com",
			"stock.xinhua08.com", "company.xinhua08.com", "bond.xinhua08.com",
			"bank.xinhua08.com", "opinion.xinhua08.com", "space.xinhua08.com",
			"life.xinhua08.com", "video.xinhua08.com", "photo.xinhua08.com",
			"jucai.xinhua08.com", "dict.xinhua08.com", "app.xinhua08.com",
			"my.xinhua08.com", "cfc.xinhua08.com" };

	public ComXinhua08() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select("#ctrlfscont").first();
		String cssQuerys[] = new String[] { ".alogo", "#moreread", "#2423line",
				"span[style=font-size: 12px;]", "img.chartimg",
				"#space_content" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
		String textQuerys[] = new String[] { "更多资讯请查看", "文章只反映作者本人观点" };
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
		String str = super.replaceStockCode(content);
		return str;

	}

	@Override
	public String replaceSpechars(String content) {
		String str = super.replaceSpechars(content);
		String replaceChars[] = new String[] { "(CNFIN.COM / XINHUA08.COM)--",
				"(CNFIN.COM/XINHUA08.COM)--", "(完)", };
		for (String string : replaceChars) {
			str = str.replace(string, "");
		}
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
