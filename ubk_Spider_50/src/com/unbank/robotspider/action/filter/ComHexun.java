package com.unbank.robotspider.action.filter;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComHexun extends BaseFilter {
	String[] domains = { "22931994.blog.hexun.com", "315.stock.hexun.com",
			"3331531.blog.hexun.com", "3g.hexun.com", "app.hexun.com",
			"auto.hexun.com", "bank.hexun.com", "bbs.hexun.com",
			"blog.hexun.com", "bond.hexun.com", "bond.money.hexun.com",
			"book.hexun.com", "bschool.hexun.com", "c.hexun.com",
			"calendar.hexun.com", "cfsd.hexun.com", "chat.hexun.com",
			"corp.hexun.com", "cpb.hexun.com", "cvs.hexun.com",
			"czcls.blog.hexun.com", "dai.licaike.hexun.com", "dasai.hexun.com",
			"data.auto.hexun.com", "data.bank.hexun.com",
			"data.book.hexun.com", "data.digi.hexun.com",
			"data.futures.hexun.com", "data.house.hexun.com",
			"data.trust.hexun.com", "datainfo.hexun.com",
			"datainfo.stock.hexun.com", "digi.hexun.com",
			"ds.futures.hexun.com", "f.blog.hexun.com", "forex.hexun.com",
			"fujian.hexun.com", "funds.hexun.com", "futures.hexun.com",
			"garydens.blog.hexun.com", "gold.hexun.com", "guba.hexun.com",
			"henan.hexun.com", "hk.stock.hexun.com", "house.hexun.com",
			"hunan.hexun.com", "i.hexun.com", "img.hexun.com",
			"insurance.hexun.com", "integration.hexun.com", "iof.hexun.com",
			"jiangsu.hexun.com", "jingzhi.funds.hexun.com",
			"julystock.blog.hexun.com", "lcal.money.hexun.com",
			"lhtc888.blog.hexun.com", "licaike.hexun.com", "mac.hexun.com",
			"mchenyun.blog.hexun.com", "media.hexun.com", "money.hexun.com",
			"msn.gold.hexun.com", "mymoney.hexun.com", "news.hexun.com",
			"opinion.hexun.com", "paiming.funds.hexun.com", "pe.hexun.com",
			"pension.hexun.com", "pic.hexun.com", "post.blog.hexun.com",
			"px.hexun.com", "pxpt.hexun.com", "qizhi.hexun.com",
			"qq473914523.blog.hexun.com", "quote.hexun.com", "reg.hexun.com",
			"renwu.hexun.com", "sc.house.hexun.com", "shoucang.hexun.com",
			"sichuan.hexun.com", "stock.hexun.com",
			"stockdata.stock.hexun.com", "suzhou.house.hexun.com",
			"t.hexun.com", "tax.hexun.com", "tech.hexun.com", "tg.hexun.com",
			"travel.hexun.com", "trust.hexun.com", "tttmoney.blog.hexun.com",
			"tv.hexun.com", "utility.tool.hexun.com", "vol.stock.hexun.com",
			"whbsuwc.blog.hexun.com", "wizard.stock.hexun.com",
			"www.hexun.com", "www.hexun.com.tw", "xa.house.hexun.com",
			"xianhuo.hexun.com", "yanbao.stock.hexun.com", "yuqing.hexun.com",
			"zhangtingbin.blog.hexun.com", "zhibiao.hexun.com" };

	public ComHexun() {
		for (String s : domains) {
			FilterLocator.getInstance().register(s, this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		Elements elements = document.select("div#artibody");
		if (elements.size() == 0) {
			elements = document.select("#zoom");
			// return null;
		}
		Element element = elements.first();
		String cssQuerys[] = new String[] {
				"#artibody > font[face=KAITI_GB2312]", "#arctTailMarkCopy",
				"#page2011nav", "link", "script", "select",
				"div[style=text-align:right;font-size:12px]", "#arctTailMark",
				"div.clear",
				"img[src=http://i4.hexunimg.cn/2014-07-17/166722863.jpg]",
				"img[src=http://img.hexun.com/home/hx.gif]",
				"img[src=http://img.hexun.com/2010/img/tel.gif]" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
		}
		String textQuerys[] = new String[] { "附件下载", "来源" };
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
		str = str.replaceAll("<strong>.*讯：</strong>", "");
		str = str.replaceAll("<strong>.*观点地产新媒体专栏作者</strong>", "");
		str = str.replaceAll("\\(([^\\(]*)?，HK\\d{5}([^\\(|\\)]*)?\\)", "");

		return str;

	}

	@Override
	public String replaceSpechars(String content) {
		String str = super.replaceSpechars(content);
		String[] chars = { "\\(博客,微博\\)", "\\(和讯放心保\\)", "\\(放心保\\)",
				"\\(楼盘\\)", "核心提示：", "本报讯", "导读：", "观点地产网讯：", "长江商报消息", "核心提示" };

		for (String s : chars) {
			str = str.replace(s, "");
		}

		String[] chars2 = { "相关新闻", "事件回顾", "(发稿：", "(审校：", "(注：作者", "相关报道：" };

		for (String s : chars2) {
			if (str.contains(s)) {
				int index = str.indexOf(s);
				str = str.substring(0, index);
			}
		}
		return str;

	}

	@Override
	public String addTag(String content) {
		return super.addTag(content);
	}

}
