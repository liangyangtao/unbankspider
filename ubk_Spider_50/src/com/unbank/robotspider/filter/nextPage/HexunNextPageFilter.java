package com.unbank.robotspider.filter.nextPage;

import java.util.TreeMap;

import org.apache.commons.lang3.StringUtils;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class HexunNextPageFilter extends NextPageBaseFilter {

	private String[] domain = new String[] { "shoucang.hexun.com",
			"gold.hexun.com", "lizichi.blog.hexun.com",
			"stockdata.stock.hexun.com", "data.house.hexun.com",
			"bbs.hexun.com", "peikesu.blog.hexun.com", "3g.hexun.com",
			"data.trust.hexun.com", "futures.hexun.com", "www.hexun.com.tw",
			"auto.hexun.com", "datainfo.stock.hexun.com", "money.hexun.com",
			"tech.hexun.com", "vip.px.hexun.com", "edu.tv.hexun.com",
			"tv.hexun.com", "img.hexun.com", "funds.hexun.com",
			"house.hexun.com", "suzhou.house.hexun.com", "f.blog.hexun.com",
			"quote.futures.hexun.com", "renwu.hexun.com", "mac.hexun.com",
			"fujian.hexun.com", "iof.hexun.com", "qhcl.hexun.com",
			"integration.hexun.com", "hunan.hexun.com", "data.bank.hexun.com",
			"sichuan.hexun.com", "wizard.stock.hexun.com",
			"hkquote.stock.hexun.com", "blog.hexun.com", "cvs.hexun.com",
			"app.hexun.com", "stock.hexun.com", "quote.forex.hexun.com",
			"t.hexun.com", "c.hexun.com", "jiangsu.hexun.com",
			"travel.hexun.com", "25006776.blog.hexun.com", "wenda.hexun.com",
			"lcal.money.hexun.com", "vol.stock.hexun.com", "bank.hexun.com",
			"pe.hexun.com", "chat.hexun.com", "pension.hexun.com",
			"opinion.hexun.com", "softserver1.stock.hexun.com",
			"yuqing.hexun.com", "insurance.money.hexun.com", "jucai.hexun.com",
			"data.book.hexun.com", "kd.hexun.com", "tax.hexun.com",
			"mchenyun.blog.hexun.com", "m.hexun.com", "quote.hexun.com",
			"www.hexun.com", "calendar.hexun.com", "digi.hexun.com",
			"gzqh.hexun.com", "roll.hexun.com", "fangzhouzi.blog.hexun.com",
			"focus.funds.hexun.com", "media.hexun.com", "guba.hexun.com",
			"corp.hexun.com", "news.hexun.com", "data.futures.hexun.com",
			"insurance.hexun.com", "pxpt.hexun.com", "licaike.hexun.com",
			"px.hexun.com", "hk.stock.hexun.com", "book.hexun.com",
			"henan.hexun.com", "jingzhi.funds.hexun.com",
			"315.stock.hexun.com", "xianhuo.hexun.com", "forex.hexun.com",
			"blogeditor.blog.hexun.com", "bond.hexun.com",
			"yanbao.stock.hexun.com", "bond.money.hexun.com",
			"paiming.funds.hexun.com", "bschool.hexun.com",
			"zhibiao.hexun.com", "cfsd.hexun.com", "trust.hexun.com",
			"qizhi.hexun.com", "msn.gold.hexun.com" };

	public HexunNextPageFilter() {
		for (int i = 0; i < domain.length; i++) {
			NextPageFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Integer extractMaxPageNum(Document document) {
		Elements scriptElements = document.body().select("script");
		String countPageString = null;
		for (Element element : scriptElements) {
			if (element.toString().contains("hxPage.maxPage")) {
				countPageString = StringUtils.substringBetween(
						element.toString(), "hxPage.maxPage =",
						";document.getElementById");

			}
		}
		countPageString = StringUtils.strip(countPageString);
		if (StringUtils.isNumeric(countPageString)) {
			return Integer.parseInt(countPageString);
		}
		return 0;

	}

	@Override
	public TreeMap<Integer, String> extractNextPageUrlMap(Document document,
			String baseUrl) {
		// http://bank.hexun.com/2014-07-02/166226636.html
		int maxPage = extractMaxPageNum(document);
		String temp[] = baseUrl.split(".html");
		String preUrl = temp[0];
		TreeMap<Integer, String> nextPageUrlMap = new TreeMap<Integer, String>();
		for (int i = 1; i < maxPage; i++) {
			nextPageUrlMap.put(i, preUrl + "_" + i + ".html");
		}
		return nextPageUrlMap;
	}

}
