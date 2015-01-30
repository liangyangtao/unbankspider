package com.unbank.robotspider.filter.content;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

@Service
public class YuanlinContentFilter extends ContentBaseFilter {
	public Logger logger = Logger.getLogger(YuanlinContentFilter.class);

	private String[] domain = new String[] { "ahhlyl.yuanlin.com",
			"jx.yuanlin.com", "zt.yuanlin.com", "hb.yuanlin.com",
			"l342438651.yuanlin.com", "xlcs5257.yuanlin.com",
			"yuanlinshi.yuanlin.com", "zhibao.yuanlin.com", "yy.yuanlin.com",
			"13336115650.yuanlin.com", "job.yuanlin.com", "heb.yuanlin.com",
			"help.yuanlin.com", "fang3391231.yuanlin.com", "sd.yuanlin.com",
			"wuyuemp.yuanlin.com", "fj.yuanlin.com", "linhuyl.yuanlin.com",
			"mmbj.yuanlin.com", "garden189.yuanlin.com",
			"ling8940516.yuanlin.com", "wxj5123.yuanlin.com",
			"yangyuqi.yuanlin.com", "jslyhm.yuanlin.com", "www.yuanlin.com",
			"w123456y.yuanlin.com", "gx.yuanlin.com", "138800597.yuanlin.com",
			"yincuilan.yuanlin.com", "djyyqyyc.yuanlin.com",
			"tsduowei.yuanlin.com", "gd.yuanlin.com", "jhhmyyc.yuanlin.com",
			"jdhuamu.yuanlin.com", "js.yuanlin.com", "xingwang.yuanlin.com",
			"hnsshm.yuanlin.com", "cyy1223.yuanlin.com",
			"wangxianjun.yuanlin.com", "xiandai2.yuanlin.com",
			"www.ztb.yuanlin.com", "pzshsyxmpc.yuanlin.com",
			"lantian.yuanlin.com", "hz.yuanlin.com",
			"xinyuanyuanlin88.yuanlin.com", "shuijyy.yuanlin.com",
			"hejinqiang1967.yuanlin.com", "hn.yuanlin.com",
			"Search.yuanlin.com", "lyycmm.yuanlin.com", "ah.yuanlin.com",
			"wenhongmiaomu.yuanlin.com", "caokui8697.yuanlin.com",
			"shanshanmm.yuanlin.com", "jsnfmp.yuanlin.com",
			"design.yuanlin.com", "sr.yuanlin.com", "anjimiaomu.yuanlin.com",
			"bjmmcs.yuanlin.com", "hexiemiaomu.yuanlin.com",
			"jxtwgyl.yuanlin.com", "hnxxztyl.yuanlin.com",
			"shenjie.yuanlin.com", "hzymyy.yuanlin.com",
			"outdoorwpc.yuanlin.com", "jingguan.yuanlin.com",
			"chen832.yuanlin.com", "wljinxiu.yuanlin.com",
			"xinyuan.yuanlin.com", "branch.yuanlin.com", "zj.yuanlin.com",
			"hongrui.yuanlin.com", "jyzx.yuanlin.com", "wenjie189.yuanlin.com",
			"bbs.yuanlin.com", "amychen.yuanlin.com", "gc.yuanlin.com",
			"jmm7575.yuanlin.com", "sc.yuanlin.com", "hyj5188.yuanlin.com",
			"expo.yuanlin.com", "yuelinmiaopu.yuanlin.com",
			"zjycyl.yuanlin.com", "longjunmiaopu.yuanlin.com",
			"rules.yuanlin.com", "sh.yuanlin.com", "jjlxmp.yuanlin.com",
			"ylec.yuanlin.com", "sdhxyx.yuanlin.com",
			"chenjing7647993.yuanlin.com", "yuexiang.yuanlin.com",
			"pyzymm.yuanlin.com", "yaochangyu.yuanlin.com", "d1.yuanlin.com",
			"lijianjun123.yuanlin.com", "huadammye.yuanlin.com",
			"ym1688.yuanlin.com", "ztszyl.yuanlin.com", "sangang.yuanlin.com",
			"laianshunshanlqc.yuanlin.com", "fxqhyi.yuanlin.com",
			"zhongmao.yuanlin.com", "zjajxy.yuanlin.com",
			"cqxdnyzhkf.yuanlin.com", "cdjayl.yuanlin.com", "gj.yuanlin.com",
			"honghong4188.yuanlin.com", "wsyl123456.yuanlin.com",
			"liu201101.yuanlin.com", "syshengda.yuanlin.com",
			"gulaoban.yuanlin.com", "qq313690824.yuanlin.com",
			"zjlylyzmyxgs.yuanlin.com", "yutiancy.yuanlin.com",
			"nlf168.yuanlin.com", "njyfmpc.yuanlin.com", "shuoxin.yuanlin.com",
			"qingdaoyusen.yuanlin.com", "hen.yuanlin.com", "news.yuanlin.com",
			"gs.yuanlin.com", "liuxiang95223.yuanlin.com",
			"xsj5188.yuanlin.com", "kf.yuanlin.com" };

	public YuanlinContentFilter() {
		for (int i = 0; i < domain.length; i++) {
			ContentFilterLocator.getInstance().register(domain[i], this);
		}

	}

	public Element getContentNode(Document document) {
		Element element = assignPossibleElement(document, "#ArticleCnt");
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
		String cssQuerys[] = new String[] { "span[style=color: #0000ff]",
				"span[style=color:#0000ff]" };
		for (String cssQuery : cssQuerys) {
			removeNoNeedElement(contentElement, cssQuery);
		}
	}

}
