package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class HexunTitleListFilter extends BaseTitleListFilter {
	private String domain[] = new String[] { "3g.hexun.com", "stock.hexun.com",
			"integration.hexun.com", "zhibiao.hexun.com", "px.hexun.com",
			"licaike.hexun.com", "c.hexun.com", "news.hexun.com",
			"cfsd.hexun.com", "yuqing.hexun.com", "quote.hexun.com",
			"mac.hexun.com", "datainfo.hexun.com", "data.bank.hexun.com",
			"www.hexun.com.tw", "app.hexun.com", "mymoney.hexun.com",
			"t.hexun.com", "post.blog.hexun.com", "utility.tool.hexun.com",
			"i.hexun.com", "reg.hexun.com", "www.hexun.com", "funds.hexun.com",
			"stockdata.stock.hexun.com", "opinion.hexun.com", "tv.hexun.com",
			"blog.hexun.com", "bbs.hexun.com", "guba.hexun.com",
			"datainfo.stock.hexun.com", "yanbao.stock.hexun.com",
			"money.hexun.com", "hk.stock.hexun.com", "wizard.stock.hexun.com",
			"tg.hexun.com", "jingzhi.funds.hexun.com", "dai.licaike.hexun.com",
			"trust.hexun.com", 
			"futures.hexun.com", "qizhi.hexun.com", "xianhuo.hexun.com",
			 "iof.hexun.com", "insurance.hexun.com",
			"pension.hexun.com", "bond.hexun.com", "tax.hexun.com",
			"tech.hexun.com", "renwu.hexun.com", "bschool.hexun.com",
			"shoucang.hexun.com", "auto.hexun.com", "travel.hexun.com",
			"book.hexun.com" };

	public HexunTitleListFilter() {
		for (int i = 0; i < domain.length; i++) {
			TitleListFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Elements getPossibleListElement(Document document) {
		//div.temp01
		return getPossibleListElement(document, "div.listNews");
	}

}
