package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class CnComBjx extends BaseFilter {
	private String domain[] = new String[] { "ex.bjx.com.cn",
			"dianlan.bjx.com.cn", "shupeidian.bjx.com.cn",
			"dianyuan.bjx.com.cn", "news.bjx.com.cn", "gcjob.bjx.com.cn",
			"dianjian.bjx.com.cn", "fdjob.bjx.com.cn", "nongdian.bjx.com.cn",
			"fd.bjx.com.cn", "kaiguan.bjx.com.cn", "market.bjx.com.cn",
			"guangfu.bjx.com.cn", "hdjob.bjx.com.cn", "hbjob.bjx.com.cn",
			"download.bjx.com.cn", "study.bjx.com.cn", "hedian.bjx.com.cn",
			"huodian.bjx.com.cn", "yibiao.bjx.com.cn", "chuneng.bjx.com.cn",
			"bbs.bjx.com.cn", "bianyaqi.bjx.com.cn", "huanbao.bjx.com.cn",
			"sdjob.bjx.com.cn", "b2b.bjx.com.cn", "www.bjx.com.cn",
			"tech.bjx.com.cn", "shuidian.bjx.com.cn", "gfjob.bjx.com.cn",
			"anfang.bjx.com.cn", "zidonghua.bjx.com.cn", "hr.bjx.com.cn",
			"xinxihua.bjx.com.cn", "map.bjx.com.cn" };

	public CnComBjx() {
		for (int i = 0; i < domain.length; i++) {
			FilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Element element = document.select("#content").first();
		String cssQuerys[] = new String[] { "script",
				"span[style=display:none]" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
		String textQuerys[] = new String[] { "原标题:" };
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
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
