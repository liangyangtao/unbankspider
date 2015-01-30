package com.unbank.robotspider.filter.title;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class ComEastmoneyTitle extends TitleBaseFilter {

	private String[] domain = { "quote.eastmoney.com", "fund.eastmoney.com",
			"iguba.eastmoney.com", "blog.eastmoney.com", "js5.eastmoney.com",
			"js1.eastmoney.com", "fundact.eastmoney.com", "cp.eastmoney.com",
			"caifumima.eastmoney.com", "www.eastmoney.com",
			"guba.eastmoney.com", "finance.eastmoney.com",
			"topic.eastmoney.com", "stock.eastmoney.com", "data.eastmoney.com",
			"global.eastmoney.com", "hk.eastmoney.com", "forex.eastmoney.com",
			"futures.eastmoney.com", "gold.eastmoney.com",
			"money.eastmoney.com", "bank.eastmoney.com", "bond.eastmoney.com",
			"insurance.eastmoney.com", "trust.eastmoney.com",
			"biz.eastmoney.com", "enterprise.eastmoney.com",
			"renwu.eastmoney.com", "media.eastmoney.com",
			"jijinba.eastmoney.com", "t.eastmoney.com", "bbs.eastmoney.com",
			"jigou.eastmoney.com", "mingjia.eastmoney.com",
			"video.eastmoney.com", "auto.eastmoney.com", "edu.eastmoney.com",
			"life.eastmoney.com", "so.eastmoney.com", "kuaixun.eastmoney.com",
			"same.eastmoney.com", "roll.eastmoney.com", "qiche.eastmoney.com",
			"xianhuo.eastmoney.com", "corp.eastmoney.com" };

	public ComEastmoneyTitle() {
		for (int i = 0; i < domain.length; i++) {
			TitleFilterLocator.getInstance().register(domain[i], this);
		}
	}

	@Override
	public String getTitle(Document document, String Alternativetitle) {
		Elements elements = document.select("h1");
		if (elements.size() == 0) {
			elements = document.select("h4");
		}
		if(elements.size()==0){
			return null;
		}
		
		String title = elements.first().text();

		return title;
	}

}
