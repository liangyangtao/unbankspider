package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class CnstockContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(CnstockContentFilter.class);

	private String domain[] = new String[] { "ggjd.cnstock.com",
			"bjzhaoxiao.blog.cnstock.com", "t.cnstock.com",
			"xinpi.cnstock.com", "lixiaojia.blog.cnstock.com",
			"blog.cnstock.com", "zhuanti16.blog.cnstock.com",
			"caozhongming.blog.cnstock.com", "stock.cnstock.com",
			"jiangren.blog.cnstock.com", "mall.cnstock.com",
			"onlinebiz.cnstock.com", "yjbg.cnstock.com", "v.cnstock.com",
			"renzhiqiangb.blog.cnstock.com", "roadshow.cnstock.com",
			"www.cnstock.com", "blogzt.cnstock.com", "irm.cnstock.com",
			"caifu.cnstock.com", "fund.cnstock.com", "jrz.cnstock.com",
			"3g.cnstock.com", "yixianrong.blog.cnstock.com",
			"zhuanti10.blog.cnstock.com", "company.cnstock.com",
			"gjzx.cnstock.com", "paper.cnstock.com",
			"pihaizhou.blog.cnstock.com", "news.cnstock.com",
			"data.cnstock.com" };

	public CnstockContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}
	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#qmt_content_div");
		if (element == null) {
			return null;
		}
		return element;
	}

	public void removeNoNeedElementsByText(Element contentElement) {
		// String textQuerys[] = new String[] { "" };
		// for (String textQuery : textQuerys) {
		// removeNoNeedTextElement(contentElement, textQuery);
		// }
	}

	public void removeNoNeedElementsByCssQuery(Element contentElement) {
		String cssQuerys[] = new String[] { "#output_hangqing_div",
				"#hid_totalPage", "#hid_docId", "div" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
