package com.unbank.robotspider.filter.titlelist;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

@Service
public class Hc360TitleListFilter extends BaseTitleListFilter {
	private String[] domains = { "app.hc360.com", "sso.hc360.com",
			"b2b.hc360.com", "my.b2b.hc360.com", "www.hc360.com",
			"www.app.hc360.com", "hfb.hc360.com", "jr.hc360.com",
			"info.b2b.hc360.com", "www.top10.hc360.com", "www.xt.hc360.com",
			"www.chat.hc360.com", "www.im.hc360.com", "v.hc360.com",
			"www.kf.hc360.com", "info.seller.hc360.com", "kf.hc360.com",
			"info.jj.hc360.com", "www.finance.hc360.com", "51office.hc360.com",
			"y.hc360.com", "cu.hc360.com", "mro.hc360.com", "buy.hc360.com",
			"www.fair.hc360.com", "bbs.hc360.com", "market.hc360.com",
			"mall.hc360.com", "info.chat.hc360.com", "info.y.hc360.com",
			"beilaite.b2b.hc360.com", "info.chem.hc360.com", "hot.hc360.com",
			"pin.hc360.com", "credit.hc360.com", "help.hc360.com",
			"www.seller.hc360.com", "info.ceo.hc360.com", "www.biz.hc360.com",
			"02280.hc360.com", "info.biz.hc360.com", "kc.xt.hc360.com",
			"info.pcrm.hc360.com", "info.plas.hc360.com", "www.chem.hc360.com",
			"z.hc360.com", "www.pcrm.hc360.com", "www.coatings.hc360.com",
			"www.energy.hc360.com", "www.pf.hc360.com", "info.xt.hc360.com",
			"anyang.hc360.com", "www.y.hc360.com", "www.steel.hc360.com",
			"www.metal.hc360.com", "info.bm.hc360.com",
			"info.rubber.hc360.com", "www.plas.hc360.com",
			"www.rubber.hc360.com", "info.cm.hc360.com", "info.it.hc360.com",
			"info.machine.hc360.com", "s.hc360.com", "www.textile.hc360.com",
			"www.fl.hc360.com", "www.shaxian.hc360.com",
			"www.leather.hc360.com", "info.textile.hc360.com",
			"www.pharmacy.hc360.com", "www.printing.hc360.com",
			"www.food.hc360.com", "www.baozhuang.hc360.com",
			"www.screen.hc360.com", "info.pharmacy.hc360.com",
			"info.gift.hc360.com", "info.food.hc360.com", "anping.hc360.com",
			"shijiazhuang.hc360.com", "yongnian.hc360.com",
			"info.cyd.hc360.com", "www.wujin.hc360.com", "www.jgj.hc360.com",
			"www.th.hc360.com", "info.wujin.hc360.com",
			"litexima.b2b.hc360.com", "info.clean.hc360.com",
			"nfxp83126080.b2b.hc360.com", "haoxing.b2b.hc360.com",
			"www.machine.hc360.com", "www.instrument.hc360.com",
			"www.cm.hc360.com", "www.clean.hc360.com", "mall.clean.hc360.com",
			"www.cmp.hc360.com", "jingxian.hc360.com", "www.bm.hc360.com",
			"www.jcdd.hc360.com", "www.zs.hc360.com", "www.jz.hc360.com",
			"shkftcn.b2b.hc360.com", "jhwkzl.b2b.hc360.com",
			"www.hvacr.hc360.com", "www.newair.hc360.com",
			"www.cool.hc360.com", "www.jjcn.hc360.com", "www.dn.hc360.com",
			"www.hp.hc360.com", "www.solar.hc360.com", "www.water.hc360.com",
			"www.js.hc360.com", "info.water.hc360.com", "info.jjcn.hc360.com",
			"diretgps.b2b.hc360.com", "yffilter.b2b.hc360.com",
			"www.auto-a.hc360.com", "www.carec.hc360.com",
			"www.qipei.hc360.com", "www.auto-m.hc360.com",
			"info.auto-a.hc360.com", "bjndkj.b2b.hc360.com",
			"www.ec.hc360.com", "www.electric.hc360.com", "www.led.hc360.com",
			"info.ec.hc360.com", "szansite.b2b.hc360.com",
			"cctvgov.b2b.hc360.com", "www.secu.hc360.com",
			"www.fire.hc360.com", "www.ledp.hc360.com", "www.audio.hc360.com",
			"info.fire.hc360.com", "info.secu.hc360.com", "shantou.hc360.com",
			"www.cloth.hc360.com", "info.cloth.hc360.com",
			"www.shoes.hc360.com", "mall.hxshoes.hc360.com",
			"www.gift.hc360.com", "www.jewelry.hc360.com", "shunde.hc360.com",
			"bimeidq.b2b.hc360.com", "zjsanfer.b2b.hc360.com",
			"entive.b2b.hc360.com", "jindidq.b2b.hc360.com",
			"hjyldq.b2b.hc360.com", "www.broadcast.hc360.com",
			"info.broadcast.hc360.com", "www.tele.hc360.com",
			"www.homea.hc360.com", "www.jdpj.hc360.com", "www.it.hc360.com",
			"www.xjd.hc360.com", "www.edu.hc360.com", "mall.homea.hc360.com",
			"www.ehome.hc360.com", "info.homea.hc360.com",
			"info.edu.hc360.com", "hbbdqyf.b2b.hc360.com",
			"xsdz666.b2b.hc360.com", "www.jj.hc360.com", "www.bgjj.hc360.com",
			"www.jiashi.hc360.com", "yangzhouhuixin.b2b.hc360.com",
			"canasin1020.b2b.hc360.com", "www.hotel.hc360.com",
			"www.kitchen.hc360.com", "info.hotel.hc360.com",
			"info.kitchen.hc360.com", "www.tz.hc360.com", "www.toys.hc360.com",
			"chenmaozi.b2b.hc360.com", "yongxingtd.b2b.hc360.com",
			"www.beauty.hc360.com", "www.agri.hc360.com", "www.feed.hc360.com",
			"info.tea.hc360.com", "www.cc.hc360.com", "www.office.hc360.com",
			"fjhcjckmy.b2b.hc360.com", "007fzkggyp.b2b.hc360.com",
			"bjninedeer.b2b.hc360.com", "pency0204.b2b.hc360.com",
			"www.service.hc360.com", "www.traffic.hc360.com",
			"www.hunjia.hc360.com", "www.a.hc360.com", "info.top10.hc360.com",
			"info.hvacr.hc360.com", "info.hp.hc360.com",
			"info.qipei.hc360.com", "info.printing.hc360.com",
			"info.screen.hc360.com", "www.ceo.hc360.com", "shehui.hc360.com",
			"www.city.hc360.com", "www.mt.hc360.com", "cn.hc360.com",
			"www.zibo.hc360.com", "www.taizhou.hc360.com",
			"www.xuzhou.hc360.com", "www.zhengzhou.hc360.com",
			"www.zhenjiang.hc360.com", "www.hengshui.hc360.com",
			"www.changzhou.hc360.com", "www.anyang.hc360.com",
			"www.jiangxi.hc360.com", "www.anqing.hc360.com",
			"www.tianjin.hc360.com", "www.weihai.hc360.com",
			"www.jinan.hc360.com", "www.dongguan.hc360.com",
			"www.dezhou.hc360.com", "www.suzhou.hc360.com",
			"www.dalian.hc360.com", "www.nanjing.hc360.com", "un.hc360.com",
			"info.auto-m.hc360.com", "info.carec.hc360.com",
			"info.coatings.hc360.com", "info.pf.hc360.com",
			"info.electric.hc360.com", "info.shoes.hc360.com",
			"info.ehome.hc360.com", "info.xjd.hc360.com", "xt.hc360.com",
			"buy.b2b.hc360.com", "hcpay.hc360.com", "paysecurity.hc360.com",
			"m.hc360.com", "top.hc360.com", "hcgroup.hc360.com",
			"agent.hc360.com", "job.hc360.com", "info.hc360.com" };

	public Hc360TitleListFilter() {
		for (int i = 0; i < domains.length; i++) {
			TitleListFilterLocator.getInstance().register(domains[i], this);
		}

	}

	@Override
	public Elements getPossibleListElement(Document document) {
		Elements elements = document.body().select("a");
		for (Element element : elements) {
			if (element.text().trim().equals("[评]")) {
				element.remove();
			}
		}
		elements = document.select("a");
		return elements;
	}

}
