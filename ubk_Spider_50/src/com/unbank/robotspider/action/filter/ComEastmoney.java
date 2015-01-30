package com.unbank.robotspider.action.filter;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComEastmoney extends BaseFilter {
	private String[] domains = { "quote.eastmoney.com", "fund.eastmoney.com",
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

	// http://fund.eastmoney.com/news/1623,20140929429281770.html
	// 推荐阅读
	public ComEastmoney() {
		for (int i = 0; i < domains.length; i++) {
			FilterLocator.getInstance().register(domains[i], this);
		}
	}

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {

		Element element = document.select("div#ContentBody").first();
		if (element == null) {
			element = document.select(".content > pre").first();
		}
		String cssQuerys[] = new String[] { "div.reading", "div.c_review",
				"div.EM_imgBaList_box" };
		for (String string : cssQuerys) {
			removeNoNeedElement(element, string);
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
		content = content.replace("点击查看大图", "");
		content = content.replaceAll("\\d{0,2}月\\d{0,2}日各大行机构名家实时汇评", "");
		return super.replaceStockCode(content);
	}

	@Override
	public String replaceSpechars(String content) {
		String str = super.replaceSpechars(content);
		str = str.replace("【点击查看全文】", "");
		String[] spechars = new String[] { "相关阅读", "推荐阅读" };
		for (String string : spechars) {
			int index = str.indexOf(string);
			if (index > 0) {
				str = str.substring(0, index);
			}
		}
		return str;
	}

	@Override
	public String addTag(String content) {
		if (content == null) {
			return null;
		}

		if (content.length() < 100) {
			return null;
		}
		Document document2 = Jsoup.parse(content);
		/*
		 * Elements elements = document2.select("p"); for (Element element :
		 * elements) {
		 * 
		 * if (element.children().size() == 0) { String temp =
		 * element.text().trim(); temp = temp.replace("　", "");
		 * element.text(temp.trim()); } }
		 */

		// 将未被P标签包裹的文字用
		wrapPToTextNodes(document2.body().select("div").first());

		content = document2.body().toString();
		content = content.replace("\n", "");
		content = content.replace("\r", "");

		content = content.replace("<body>", "");
		content = content.replace("</body>", "");
		// content = content.replace("　", "&nbsp;");
		content = "<div style='text-align:left;'> " + content + "</div>";
		return content;
	}

	public void removeAdvertiseText(Element maxTextElement) {
		Elements elements = maxTextElement.select("strong");
		for (Element element : elements) {
			String advertiseTexts[] = new String[] { "查看", "||", "相关评论",
					"相关专题", "重点推荐", "延伸阅读", "相关报道", "商业专栏", "证券要闻", "行业动态",
					"公司新闻", "相关新闻", "往期回顾", "今日消息", "机构策略", "原标题", "更多详情",
					"免责声明", "版权声明", "相关链接", "机构研究", "个股点评", "行业新闻", "公司动态" };
			for (String string : advertiseTexts) {
				removeNoNeedTextElement(element, string);
			}
		}

	}

	public String removeStockCode(String content) {
		String stockCodes[] = new String[] {
				"<!--.[^-]*(?=-->)-->",
				"(?is)<!--.*?-->",
				"\\(\\d{6},\\)",
				"\\(\\d{1,6}[\\.|,|，|；|;|-][ |(&nbsp;)]{0,10}\\w{2}\\)",
				"\\(\\d{1,6}\\.\\w{2,4}[，|,|;|；][\\u4e00-\\u9fa5]{2,10}\\)",
				"\\(\\d{1,6}\\.\\w{2,4}/\\d{1,6}\\.\\w{2,4}\\)",
				"\\(\\d{1,6}\\.\\w{2,4};\\d{1,6}\\.\\w{2,4}\\)",
				"\\(\\d{1,6}\\.\\w{2,4}；\\d{1,6}\\.\\w{2,4}\\)",
				"\\(\\d{1,6}\\.\\w{2,4}、\\d{1,6}\\.\\w{2,4}\\)",
				"\\(\\d{1,6}\\.\\w{2,4},\\d{1,6}\\.\\w{2,4}\\)",
				"\\(\\d{1,6}\\.\\w{2,4}，\\d{1,6}\\.\\w{2,4}\\)",
				"\\(\\d{1,6}\\.\\w{2,4},-{0,1}\\d{1,2}\\.\\d{1,4},-{0,1}\\d{1,2}\\.\\d{1,2}%\\)",
				"\\(\\d{1,6}\\.\\w{2,4}, -{0,1}\\d{1,2}\\.\\d{1,4}, -{0,1}\\d{1,2}\\.\\d{1,2}%\\)",
				"\\(\\d{1,6}\\.\\w{2,4},(&nbsp;){0,10}-{0,1}\\d{1,2}\\.\\d{1,4},(&nbsp;){0,10}-{0,1}\\d{1,2}\\.\\d{1,2}%\\)",
				"\\(\\d{1,6}\\.\\w{2,4}，-{0,1}\\d{1,2}\\.\\d{1,4}，-{0,1}\\d{1,2}\\.\\d{1,2}%\\)",
				"\\(\\d{1,6}\\.\\w{2,4}， -{0,1}\\d{1,2}\\.\\d{1,4}， -{0,1}\\d{1,2}\\.\\d{1,2}%\\)",
				"\\(\\d{1,6}\\.\\w{2,4}，(&nbsp;){0,10}-{0,1}\\d{1,2}\\.\\d{1,4}，(&nbsp;){0,10}-{0,1}\\d{1,2}\\.\\d{1,2}%\\)",
				"\\(\\d{1,6}\\.\\w{1,4},-{0,1}\\d{1,6}\\.\\w{2,4},-{0,1}\\d{1,2}\\.\\d{1,2}%,实时行情\\)",
				"\\(\\d{1,6}\\.\\w{1,4}，-{0,1}\\d{1,6}\\.\\w{2,4}，-{0,1}\\d{1,2}\\.\\d{1,2}%，实时行情\\)",
				// "\\d{1,6}[\\.|,|，|；|;|-]{0,1}[ |(&nbsp;)]{0,10}\\w{2,4}[\\.|,|，|；|;]{0,1}[ |(&nbsp;)]{0,10}",
				"\\[-{0,1}\\d{1,4}\\.\\d{1,2}% 资金 研报\\]",
				"\\[-{0,1}\\d{1,4}\\.\\d{1,2}%[(&nbsp;)| |	| ]{0,10}资金 研报\\]",
				"摘自\\w{2,5}网", "【[\\u4e00-\\u9fa5]{2,6}网】",
				"\\(([^\\(]*)?\\d{5,6}([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?微博([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?基金吧([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?股吧([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?代码([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?记者([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?编辑([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?作者([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?点击([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?访问([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?www\\.([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?http://([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?来源([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?标题([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?微信([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?收盘价([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?客户端([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?交易所([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?行情([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?评论([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?声明([^\\(|\\)]*)?\\)",
				"\\(([^\\(]*)?版权([^\\(|\\)]*)?\\)",

		};
		for (String string : stockCodes) {
			content = content.replaceAll(string, "");
		}
		return content;
	}
}
