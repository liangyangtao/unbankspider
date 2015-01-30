package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class IbicnContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(IbicnContentFilter.class);
	String[] domain = new String[] { "www.gjg.ibicn.com", "www.pm.ibicn.com",
			"www.yjcy.ibicn.com", "www.chinazxsq.ibicn.com",
			"www.gcjg.ibicn.com", "www.ptci.ibicn.com",
			"www.srhw.ibicn.com", "www.flgc.ibicn.com",
			"www.xfcy.ibicn.com", "www.twpzx.ibicn.com",
			"www.yqzl.ibicn.com", "www.mtmi.ibicn.com",
			"www.xdmy.ibicn.com", "www.jxhgcy.ibicn.com",
			"www.hgscl.ibicn.com", "www.gpcy.ibicn.com",
			"www.glzz.ibicn.com", "www.yswhcy.ibicn.com",
			"www.bpqcy.ibicn.com", "www.dlyh.ibicn.com",
			"www.dzsw.ibicn.com", "www.dxdl.ibicn.com",
			"www.mtjgyxx.ibicn.com", "www.ddccy.ibicn.com",
			"www.mjh.ibicn.com", "www.shjx.ibicn.com", "www.ccio.ibicn.com",
			"www.ymcy.ibicn.com", "www.jtaqss.ibicn.com",
			"www.fmcy.ibicn.com", "www.water.ibicn.com",
			"www.llwl.ibicn.com", "www.fdcy.ibicn.com",
			"www.jzjxcy.ibicn.com", "www.jcyq.ibicn.com",
			"www.dzkcsb.ibicn.com", "www.mcy.ibicn.com",
			"www.ffgc.ibicn.com", "www.ztcy.ibicn.com",
			"www.tyncy.ibicn.com", "www.ylrqzx.ibicn.com",
			"www.zw.ibicn.com", "www.dycy.ibicn.com", "www.gases.ibicn.com",
			"www.ljcy.ibicn.com", "www.stny.ibicn.com",
			"www.spaqyjc.ibicn.com", "www.hgxclw.ibicn.com",
			"www.cppmp.ibicn.com", "www.dzzx.ibicn.com",
			"www.rhy.ibicn.com", "www.dtcy.ibicn.com", "www.gczl.ibicn.com",
			"www.zc.ibicn.com", "www.ygjj.ibicn.com", "www.gyms.ibicn.com",
			"www.trq.ibicn.com", "www.tlgdjs.ibicn.com",
			"www.swkjcy.ibicn.com", "www.hqzx.ibicn.com",
			"www.dxstr.ibicn.com", "www.hdzx2010.ibicn.com",
			"www.bzjcyw.ibicn.com", "www.yllhcy.ibicn.com",
			"www.mtglzx.ibicn.com", "www.byqcy.ibicn.com",
			"www.jtsy.ibicn.com", "www.jsbx.ibicn.com",
			"www.yysb.ibicn.com", "www.lt.ibicn.com",
			"www.znckcy.ibicn.com", "www.diaosuo360.ibicn.com",
			"www.bb.ibicn.com", "www.szksaq.ibicn.com",
			"www.cycy.ibicn.com", "www.fhuagong.ibicn.com",
			"www.landscapingdm.ibicn.com", "www.gyjn.ibicn.com",
			"www.yszx.ibicn.com", "www.jnjcy.ibicn.com", "www.jn.ibicn.com",
			"www.meat.ibicn.com", "www.jgkj.ibicn.com",
			"www.mtgyl.ibicn.com", "www.wlwcy.ibicn.com",
			"www.tlcy.ibicn.com", "www.ppc.ibicn.com", "www.gdcy.ibicn.com",
			"www.tcae2010.ibicn.com", "www.cgii.ibicn.com",
			"www.ykzy.ibicn.com", "www.szsq.ibicn.com",
			"www.weet.ibicn.com", "www.syzc.ibicn.com",
			"www.jzzl.ibicn.com", "www.ljfd.ibicn.com",
			"www.wscy.ibicn.com", "www.dtjt.ibicn.com",
			"www.gcjx.ibicn.com", "www.gsls.ibicn.com",
			"www.xclzx.ibicn.com", "www.mhgcy.ibicn.com",
			"www.jccy.ibicn.com", "www.mjcy.ibicn.com",
			"www.jdyp.ibicn.com", "www.sysh.ibicn.com",
			"www.hfcy.ibicn.com", "www.mtcy.ibicn.com", "www.zzz.ibicn.com",
			"www.xnyqc.ibicn.com", "www.mtjsw.ibicn.com",
			"www.bankpi.ibicn.com", "www.gkmts.ibicn.com",
			"www.hrshebei.ibicn.com", "www.wtwhcy.ibicn.com",
			"www.znjtzx.ibicn.com", "www.hjjc.ibicn.com",
			"www.fhcl.ibicn.com", "www.gyjqr.ibicn.com",
			"www.dljs.ibicn.com", "www.gjz.ibicn.com", "www.psin.ibicn.com",
			"www.ylsb.ibicn.com", "www.fnzy.ibicn.com",
			"www.dfcn.ibicn.com", "www.yqcg.ibicn.com",
			"www.jjzy.ibicn.com", "www.cnpcgc.ibicn.com",
			"www.xjcy.ibicn.com", "www.jzdqcy.ibicn.com",
			"www.zkjsysb.ibicn.com", "www.afcy.ibicn.com",
			"www.jazclw.ibicn.com", "www.hbcy.ibicn.com",
			"www.pgcy.ibicn.com", "www.sng.ibicn.com", "www.ylkj.ibicn.com",
			"www.bridge.ibicn.com", "www.led.ibicn.com",
			"www.zzcy.ibicn.com", "www.slsd.ibicn.com",
			"www.gyqx.ibicn.com", "www.hjcy.ibicn.com",
			"www.lycy.ibicn.com", "www.zgtzsc.ibicn.com",
			"www.jycl.ibicn.com", "www.ksjxcy.ibicn.com",
			"www.cy.ibicn.com", "www.zrgfz.ibicn.com",
			"www.rclgyl.ibicn.com", "www.lyjx.ibicn.com",
			"www.yyjzgc.ibicn.com", "www.dlhb.ibicn.com",
			"www.szyy.ibicn.com", "www.ccywl.ibicn.com",
			"www.zdhyqyb.ibicn.com", "www.cpi.ibicn.com",
			"www.ddqj.ibicn.com", "www.sytjj.ibicn.com",
			"www.jzsgzb.ibicn.com", "www.ebe.ibicn.com",
			"www.zjsq.ibicn.com", "www.ibicn.com", "www.twjpzn.ibicn.com",
			"www.gycy.ibicn.com", "www.lzhchina.ibicn.com",
			"www.tcsbcy.ibicn.com", "www.yrsjsq.ibicn.com",
			"www.dccy.ibicn.com", "www.gdgc.ibicn.com",
			"www.djcy.ibicn.com", "www.ppeae.ibicn.com",
			"www.dljcjk.ibicn.com", "www.nfracn.ibicn.com",
			"www.qpsj.ibicn.com" };

	public IbicnContentFilter() {

		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#z-nr");
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
		String cssQuerys[] = new String[] { "#stockSelectBox" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
