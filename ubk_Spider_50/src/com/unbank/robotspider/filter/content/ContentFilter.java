package com.unbank.robotspider.filter.content;

import java.util.List;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.fetch.ImageFilter;
import com.unbank.robotspider.fetch.ImageUrlAndSizeFilter;
import com.unbank.robotspider.fetch.JsoupNetFetcher;
import com.unbank.robotspider.filter.BaseFilter;
import com.unbank.robotspider.tools.AngleConvertion;

@Service
public class ContentFilter extends BaseFilter {
	public Logger logger = Logger.getLogger(ContentFilter.class);

	public String getContent(Document document) {

		Element contentElement = getContentNode(document);
		try {
			if (contentElement == null) {
				return null;
			}
			removeAdvertiseNode(contentElement);
			saveImage(contentElement);
			formatElements(contentElement);
		} catch (Exception e) {
			logger.info("格式化最大节点出错", e);
		}
		String content = contentElement.toString();
		content = new AngleConvertion().angleConvertion(content);
		content = replaceStockCode(content);
		content = replaceSpechars(content);
		content = addTag(content);
		return content;

	}

	public boolean saveImage(Element contentElement) {
		Elements elements = contentElement.select("img");
		for (Element element : elements) {
			String imgSrc = element.absUrl("src");
			if (imgSrc == null || imgSrc.trim().isEmpty()) {
				continue;
			}

			ImageFilter filter = new ImageUrlAndSizeFilter(null, 0, 0);
			String imgUrl = new JsoupNetFetcher().fetchImage(imgSrc, filter);

			if (imgUrl != null && (!imgUrl.trim().isEmpty())) {
				element.attr("src", imgUrl);
				continue;
			} else {
				element.remove();
				return false;
			}
		}
		return false;
	}

	public Element getContentNode(Document document) {
		return document.body();
	}

	// public void saveImage(Element maxTextElement) {
	// Elements elements = maxTextElement.select("img");
	// for (Element element : elements) {
	// String imgSrc = element.absUrl("src");
	// if (imgSrc == null || imgSrc.trim().isEmpty()) {
	// element.remove();
	// continue;
	// }
	// element.attr("src", imgSrc);
	// }
	//
	// }

	public void formatElements(Element contentElement) {
		removeElementAttr(contentElement);
		Elements allElements = contentElement.children();
		for (Element element : allElements) {
			removeWasteNode(element);
			removeElementAttr(element);
			changeNodeName(element);
			if (element != null) {
				formatElements(element);
			}

		}

	}

	public void changeNodeName(Element element) {
		if (element == null) {
			return;
		}
		if (element.tagName().equals("p") || element.tagName().equals("div")) {
			element.tagName("p");
		} else if (element.tagName().equals("b")) {
			element.tagName("strong");
		} else {
			if (element.tagName().equals("br")
					|| element.tagName().equals("img")
					|| element.tagName().equals("table")
					|| element.tagName().equals("th")
					|| element.tagName().equals("tr")
					|| element.tagName().equals("td")
					|| element.tagName().equals("tbody")
					|| element.tagName().equals("strong")
					|| element.tagName().equals("center")) {
			} else {
				element.tagName("v");
			}
		}
	}

	// 移除所有的属性
	public void removeElementAttr(Element element) {
		if (element == null) {
			return;
		}
		Attributes attributes = element.attributes();
		for (Attribute attribute : attributes) {
			if (attribute.getKey().isEmpty()) {
				continue;
			} else if (attribute.getKey().equals("align")
					&& attribute.getValue().equals("center")) {
				element.removeAttr(attribute.getKey());
				element.attr("class", "p_center");
				continue;

			} else if (attribute.getKey().equals("style")
					&& (attribute.getValue().toLowerCase()
							.contains("text-align: center"))) {
				element.removeAttr(attribute.getKey());
				element.attr("class", "p_center");
				continue;
			} else if (attribute.getKey().equals("rowspan")
					|| attribute.getKey().equals("colspan")
					|| attribute.getKey().equals("src")) {
				continue;
			} else {
				element.removeAttr(attribute.getKey());
			}
		}
	}

	public void removeAdvertiseNode(Element contentElement) {
		removeAdvertiseNodes(contentElement);
		removeAdvertiseText(contentElement);
	}

	public void removeWasteNode(Element element) {
		if (element == null) {
			return;
		}
		if (element != null && element.attr("style").contains("display:none;")) {
			element.remove();
		} else if (element.tagName().equals("script")
				|| element.tagName().equals("style")
				|| element.tagName().equals("textarea")
				|| element.tagName().equals("select")) {
			element.remove();
		}
	}

	public void removeAdvertiseText(Element maxTextElement) {
		Elements elements = maxTextElement.select("strong");
		for (Element element : elements) {
			String advertiseTexts[] = new String[] { "查看", "||", "相关评论",
					"相关专题", "重点推荐", "延伸阅读", "推荐阅读", "相关报道", "商业专栏", "证券要闻",
					"行业动态", "公司新闻", "相关新闻", "往期回顾", "今日消息", "机构策略", "原标题",
					"相关阅读", "更多详情", "免责声明", "版权声明", "相关链接", "机构研究", "个股点评",
					"行业新闻", "公司动态" };
			for (String string : advertiseTexts) {
				removeNoNeedTextElement(element, string);
			}
		}

	}

	public void removeAdvertiseLink(Element maxTextElement) {
		if (maxTextElement == null) {
			return;
		}
		Elements elements = maxTextElement.select("a");
		for (Element element : elements) {
			if (element.text().length() > 8) {
				element.remove();
			}
		}
	}

	public void removeAdvertiseNodes(Element contentElement) {
		removeNoNeedElementsByCssQuery(contentElement);
		removeNoNeedElementsByText(contentElement);

	}

	public void removeNoNeedElementsByText(Element contentElement) {
	}

	public void removeNoNeedElementsByCssQuery(Element contentElement) {
	}

	public String replaceStockCode(String content) {

		try {
			content = content.replaceAll(">\\s{0,10}", ">");
			content = content.replaceAll(">\\s{0,10}(&nbsp; ){0,}", ">");
			content = content.replaceAll(">\\s{0,10}(&nbsp;){0,}", ">");
			content = content.replaceAll(">\\s{0,10} {0,}", ">");
			content = content.replaceAll(">\\s{0,10}  {0,}", ">");
			content = content.replaceAll(">\\s{0,10}", ">");
			content = content.replaceAll("\\s{0,10}<", "<");
			content = content.replaceAll("<br>", "</p><p>");
			content = content.replaceAll("<br />", "</p><p>");
			content = content.replaceAll("<br/>", "</p><p>");
			content = content.replace("<v>", "");
			content = content.replace("</v>", "");
			content = content.replaceAll("<p></p>", "");
			content = removeStockCode(content);
			content = content.trim();

		} catch (Exception e) {
			e.printStackTrace();
			return content;
		}
		return content;
	}

	public String removeStockCode(String content) {
		String stockCodes[] = new String[] {

				"<!--.[^-]*(?=-->)-->",
				"(?is)<!--.*?-->",

				"\\(\\d{1,6}[\\.|,|，|；|;|-]{1}[ |(&nbsp;)]{0,10}\\w{2,4}\\)",
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

				"\\[-{0,1}\\d{1,4}\\.\\d{1,2}% 资金 研报\\]",

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

	public String replaceSpechars(String content) {
		content = replaceNoNeedChars(content);
		content = removeNoNeedChars(content);
		return content;
	}

	public String removeNoNeedChars(String content) {
		String[] spechars = new String[] { "免责声明", "版权声明", "【重点推荐", "【延伸阅读",
				"【推荐阅读", "【相关阅读", "【免责声明", "【版权声明", "【更多详情", "【相关专题" };
		for (String string : spechars) {
			int index = content.indexOf(string);
			if (index > 0) {
				content = content.substring(0, index);
			}
		}
		return content;
	}

	public String replaceNoNeedChars(String content) {
		String spechars[] = new String[] { "我有话说", "欢迎发表评论", "发表评论", "收藏本文",
				"微博推荐", "[微博]", "(财苑)", "返回列表", "加入收藏", "打印本页", "打印本稿", "新闻订阅",
				"分享到", "【打印】", "网站论坛", "字体：大 中 小", "字体:大 中 小", "字体：", "字体:",
				"推荐朋友", "关闭窗口", "关闭", "慧聪资讯手机客户端下载", "(市值重估)",
				"[最新消息 价格 户型 点评]", "[简介 最新动态]", "[简介最新动态]", "[最新消息价格户型点评]",
				"(楼盘)", "(点击查看最新人物消息)", "( 详情 图库 团购 点评 ) ",
				"(CNFIN.COM/XINHUA08.COM)--", "(CNFIN.COM / XINHUA08.COM)--",
				"(CNFIN.COM&nbsp;/&nbsp;XINHUA08.COM)--", "(看跌期权)", "(放心保)",
				"(查询信托产品)", "(楼盘资料)", "(<strong>点击进入徐工网上购机页面</strong>)", "@",
				"点击浏览全文", "返回首页", "更多精彩，请登录“人民微博”参与互动", "《》：", "(滚动资讯)", "(完)",
				"(中国电子商务研究中心讯)", "()", "[]", "【】", "【点击查看全文】" };

		for (String string : spechars) {
			content = content.replace(string, "");
		}
		return content;
	}

	public String addTag(String content) {
		if (content == null) {
			return "";
		}
		Document document2 = Jsoup.parse(content);
		wrapPToTextNodes(document2.body());
		content = document2.body().toString();
		content = content.replaceAll("\\n", "");
		content = content.replaceAll("\\r", "");
		content = content.replace("<body>", "");
		content = content.replace("</body>", "");
		content = "<div>" + content + "</div>";
		return content;
	}

	public void wrapPToTextNodes(Element element) {
		if (element != null) {
			wrapTextNode(element);
			Elements childs = element.children();
			for (Element element2 : childs) {
				if (element2.tagName().equals("div")) {
					wrapTextNode(element2);
				}
			}

		}
	}

	public void wrapTextNode(Element element) {
		List<TextNode> textNodes = element.textNodes();
		if (textNodes.size() != 0) {
			for (TextNode tn : textNodes) {
				tn.wrap("<p></p>");
			}
		}
	}
}
