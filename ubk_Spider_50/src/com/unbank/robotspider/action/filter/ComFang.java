package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComFang extends BaseFilter {
	private String domain[] = new String[] { "www.fang.com", "esf.fang.com",
			"zu.fang.com", "home.fang.com", "bbs.fang.com", "fdc.fang.com",
			"land.fang.com", "8.fang.com", "client.3g.fang.com",
			"help.fang.com", "wap.fang.com", "bj.fang.com", "dg.fang.com",
			"hn.fang.com", "nn.fang.com", "sy.fang.com", "cd.fang.com",
			"dl.fang.com", "hf.fang.com", "qd.fang.com", "tj.fang.com",
			"cq.fang.com", "fz.fang.com", "jn.fang.com", "sh.fang.com",
			"wuhan.fang.com", "cs.fang.com", "gz.fang.com", "nanjing.fang.com",
			"sz.fang.com", "wuxi.fang.com", "changchun.fang.com",
			"gy.fang.com", "nc.fang.com", "suzhou.fang.com", "xian.fang.com",
			"cz.fang.com", "hz.fang.com", "nb.fang.com", "sjz.fang.com",
			"zz.fang.com" };

	public ComFang() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#news_body").first();

		String cssQuerys[] = new String[] { "table" ,"div"};
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
