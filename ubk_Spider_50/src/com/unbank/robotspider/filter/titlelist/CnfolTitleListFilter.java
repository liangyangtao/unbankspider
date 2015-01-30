package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class CnfolTitleListFilter extends BaseTitleListFilter {
	private String domain[] = new String[] { "help.cnfol.com",
			"tuiguang.cnfol.com", "p.cnfol.com", "3g.cnfol.com",
			"passport.cnfol.com", "www.cnfol.com", "news.cnfol.com",
			"review.cnfol.com", "stock.cnfol.com", "data.cnfol.com",
			"yjbg.stock.cnfol.com", "sc.stock.cnfol.com",
			"gegu.stock.cnfol.com", "zldx2.stock.cnfol.com",
			"quotes.cnfol.com", "zb.video.cnfol.com", "hkstock.cnfol.com",
			"zggng.stock.cnfol.com", "gold.cnfol.com", "silver.cnfol.com",
			"xg.stock.cnfol.com", "cyb.stock.cnfol.com",
			"sbsc.stock.cnfol.com", "futures.cnfol.com", "xianhuo.cnfol.com",
			"gzqh.cnfol.com", "fund.cnfol.com", "trust.cnfol.com",
			"forex.cnfol.com", "bond.cnfol.com", "insurance.cnfol.com",
			"bank.cnfol.com", "money.cnfol.com", "btc.cnfol.com",
			"data.common.cnfol.com", "haixi.cnfol.com", "caipiao.cnfol.com",
			"house.cnfol.com", "special.cnfol.com", "gmxy.stock.cnfol.com",
			"auto.cnfol.com", "data.auto.cnfol.com", "blog.cnfol.com",
			"g.cnfol.com", "roadshow.cnfol.com", "bbs.cnfol.com",
			"win.cnfol.com", "video.cnfol.com", "person.cnfol.com",
			"invest.cnfol.com", "wm.cnfol.com", "xueyuan.cnfol.com",
			"vip.g.cnfol.com", "fxs.cnfol.com", "cs.cnfol.com",
			"znz.cnfol.com", "zq.stock.cnfol.com", "jraq.cnfol.com",
			"weixin.cnfol.com", "funds365.cnfol.com", "hy.stock.cnfol.com",
			"yc.stock.cnfol.com", "live.stock.cnfol.com",
			"live.hkstock.cnfol.com", "shell.cnfol.com", "fisco.cnfol.com",
			"qs.stock.cnfol.com", "wm2.cnfol.com", "collection.cnfol.com" };

	public CnfolTitleListFilter() {
		for (int i = 0; i < domain.length; i++) {
			TitleListFilterLocator.getInstance().register(domain[i], this);
		}

	}

	@Override
	public Elements getPossibleListElement(Document document) {
		String cssQuerys[] = new String[] {"div.Fl",".BdPiL"};
		Elements elements = null;
		for (String string : cssQuerys) {
			elements = getPossibleListElement(document, string);
			if(elements !=null){
				break;
			}
			
		}
		return elements;
	}
}
