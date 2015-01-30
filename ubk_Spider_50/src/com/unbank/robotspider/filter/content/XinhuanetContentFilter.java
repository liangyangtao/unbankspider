package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class XinhuanetContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(XinhuanetContentFilter.class);

	private String domain[] = new String[] { "www.gd.xinhuanet.com",
			"chinesejournalist.xinhuanet.com", "wj.js.xinhuanet.com",
			"www.cypd.xinhuanet.com", "wx.xinhuanet.com",
			"www.hq.xinhuanet.com", "www.ha.xinhuanet.com",
			"jjckb.xinhuanet.com", "news.xinhuanet.com", "www.xinhuanet.com",
			"globe.xinhuanet.com", "wza.xinhuanet.com", "www.gx.xinhuanet.com",
			"www.nx.xinhuanet.com", "www.jx.xinhuanet.com",
			"www.fj.xinhuanet.com", "www.cq.xinhuanet.com",
			"www.gz.xinhuanet.com", "www.nmg.xinhuanet.com",
			"zgws.xinhuanet.com", "www.sc.xinhuanet.com",
			"cnstock.xinhuanet.com", "japan.xinhuanet.com",
			"www.ln.xinhuanet.com", "www.sd.xinhuanet.com",
			"nis.xinhuanet.com", "imgs.xinhuanet.com", "www.xj.xinhuanet.com",
			"www.he.xinhuanet.com", "csj.xinhuanet.com",
			"www.gs.xinhuanet.com", "www.sh.xinhuanet.com",
			"spanish.xinhuanet.com", "my.xinhuanet.com",
			"www.qh.xinhuanet.com", "he.xinhuanet.com", "french.xinhuanet.com",
			"www.yn.xinhuanet.com", "lw.xinhuanet.com", "www.hn.xinhuanet.com",
			"herald.xinhuanet.com", "zjg.js.xinhuanet.com",
			"www.hb.xinhuanet.com", "midchina.xinhuanet.com",
			"www.bj.xinhuanet.com", "www.zj.xinhuanet.com",
			"www.hlj.xinhuanet.com", "jp.xinhuanet.com",
			"www.ah.xinhuanet.com", "press.xinhuanet.com", "sz.xinhuanet.com",
			"www.tj.xinhuanet.com", "www.sx.xinhuanet.com", "bt.xinhuanet.com",
			"sg.xinhuanet.com", "www.jl.xinhuanet.com", "www.sn.xinhuanet.com",
			"www.js.xinhuanet.com", "mrdx.xinhuanet.com" };

	public XinhuanetContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		String cssQuerys[] = new String[] { "#contentblock", "#content",
				"#zhenwen" };
		Element element = null;
		for (String string : cssQuerys) {
			element = assignPossibleElement(document, string);
			if (element != null) {
				break;
			}
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
		String cssQuerys[] = new String[] { "#div_page_roll1", "#div_currpage" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
