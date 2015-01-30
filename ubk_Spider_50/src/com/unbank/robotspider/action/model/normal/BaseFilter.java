package com.unbank.robotspider.action.model.normal;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.log4j.Logger;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Attribute;
import org.jsoup.nodes.Attributes;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.nodes.TextNode;
import org.jsoup.select.Elements;

import com.unbank.robotspider.fetch.ImageFilter;
import com.unbank.robotspider.fetch.ImageUrlAndSizeFilter;
import com.unbank.robotspider.fetch.JsoupNetFetcher;
import com.unbank.robotspider.util.StringUtil;

public class BaseFilter implements Filter {

	public Logger logger = Logger.getLogger(BaseFilter.class);
	protected List<String> regList = new ArrayList<String>();

	@Override
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title) {
		remove(document.body());
		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}
		if (title.trim().isEmpty()) {
			title = document.title();
		}
		return getMaxTextElement(url, title, bodyElement);
	}

	public void removeNoNeedElement(Element element, String string) {
		if (element == null) {
			return;
		}
		Elements elements = element.select(string);
		for (Element element2 : elements) {
			element2.remove();
		}
	}

	public void removeNoNeedTextElement(Element element, String string) {
		if (element == null) {
			return;
		}
		Elements element2 = element.getElementsContainingOwnText(string);
		for (Element element3 : element2) {
			element3.remove();
		}
	}

	// 清除不必要的内容
	public void remove(Element element) {
		if (element != null) {
			Elements elements = element.children();
			for (Element element2 : elements) {
				removeWaste(element2);
				if (element2 == null) {
					continue;
				} else {
					remove(element2);
				}
			}
		}
	}

	@Override
	public void formatElements(Element maxTextElement) {

		Elements allElements = maxTextElement.children();
		for (Element element : allElements) {

			if (element.tagName().equals("br")) {
				// 如果是br 和 image 就什么也不做
			} else if (!(element.tagName().equals("img") || element.tagName()
					.equals("table"))) {
				// 去除属性
				removeAttr(element);
				// 更换标签
				if (element.tagName().equals("p")
						|| element.tagName().equals("div")) {
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
				removeNoTextNode(element);

			}
			if (element != null) {
				formatElements(element);
			}

		}

	}

	/**
	 * 去除没有文本的元素
	 * 
	 * @param element
	 */
	private void removeNoTextNode(Element element) {
		if (element != null && element.text().length() == 0) {
			Elements elements = element.getAllElements();
			boolean isRemove = true;
			for (Element element2 : elements) {
				if (element2.tagName().equals("img")) {
					isRemove = false;
					break;
				}
			}
			if (isRemove) {
				element.remove();
			}
		}
	}

	@Override
	public void saveImage(Element maxTextElement, String url) {
		Elements elements = maxTextElement.select("img");
		for (Element element : elements) {
			if ("display:none;".equals(element.attr("style"))) {
				element.remove();
				continue;
			}
			String imgSrc = element.absUrl("src");
			if (imgSrc == null || imgSrc.trim().isEmpty()) {
				continue;
			}
			ImageFilter filter = new ImageUrlAndSizeFilter(null, 0, 0);
			String imgUrl = new JsoupNetFetcher().fetchImage(imgSrc, filter);

			if (imgUrl != null && (!imgUrl.trim().isEmpty())) {
				logger.info("网址         " + url + "    图片地址      " + imgSrc
						+ "       新图片网址    " + imgUrl);
				element.attr("src", imgUrl);
				// 去掉除src 的所有属性
				Attributes attributes = element.attributes();
				for (Attribute attribute : attributes) {
					if (attribute.getKey().isEmpty()) {
						continue;
					} else if (attribute.getKey().equals("src")) {
						continue;
					} else {
						element.removeAttr(attribute.getKey());
					}
				}
			} else {
				logger.info("图片下载出错网址         " + url + "    图片地址      "
						+ imgSrc + "       新图片网址      " + imgUrl);
				element.remove();
			}
		}

	}

	/**
	 * 去除股票代码
	 */
	@Override
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
			content = content.replaceAll("<p>(&nbsp;){0,}</p>", "");
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
				"\\(([^\\(]*)?简称([^\\(|\\)]*)?\\)",
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

	@Override
	public String replaceSpechars(String content) {
		content = replaceNoNeedChars(content);
		content = removeNoNeedChars(content);
		return content;
	}

	public String removeNoNeedChars(String content) {

		String[] spechars = new String[] { "【免责声明", "【版权声明", "【重点推荐", "【延伸阅读",
				"【推荐阅读", "【相关阅读", "免责声明", "版权声明", "【更多详情", "【相关专题" };

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
				"微博推荐", "(行情研报)", "[微博]", "(微博)", "(财苑)", "返回列表", "加入收藏",
				"打印本页", "打印本稿", "新闻订阅", "分享到", "【打印】", "网站论坛", "字体：大 中 小",
				"字体:大 中 小", "字体：", "字体:", "推荐朋友", "关闭窗口", "关闭", "慧聪资讯手机客户端下载",
				"(行情，问诊)", "(行情,问诊)", "(博客,微博)", "(微信)", "(市值重估)",
				"[最新消息 价格 户型 点评]", "[简介 最新动态]", "[简介最新动态]", "[最新消息价格户型点评]",
				"(楼盘)", "(点击查看最新人物消息)", "( 详情 图库 团购 点评 ) ",
				"(CNFIN.COM/XINHUA08.COM)--", "(CNFIN.COM / XINHUA08.COM)--",
				"(CNFIN.COM&nbsp;/&nbsp;XINHUA08.COM)--", "(看跌期权)", "(放心保)",
				"(查询信托产品)", "(楼盘资料)", "(<strong>点击进入徐工网上购机页面</strong>)",
				"点击浏览全文", "返回首页", "更多精彩，请登录“人民微博”参与互动", "《》：", "(滚动资讯)", "(完)",
				"(中国电子商务研究中心讯)", "()" };

		for (String string : spechars) {
			content = content.replace(string, "");
		}
		return content;
	}

	@Override
	public String addTag(String content) {
		if (content == null) {
			return "";
		}
		Document document2 = Jsoup.parse(content);
		wrapPToTextNodes(document2.body());
		content = document2.body().toString();
		content = content.replace("\n", "");
		content = content.replace("\r", "");
		content = content.replace("<body>", "");
		content = content.replace("</body>", "");
		content = "<div> " + content + "</div>";
		return content;
	}

	// 获取内容最长的孩子节点
	public Element getMaxLengthChild(Element parentElement, String title) {
		Element temp = parentElement;
		while (true) {

			Elements childElements = temp.children();

			for (int i = 0; i < childElements.size() - 1; i++) {

				for (int j = i + 1; j < childElements.size(); j++) {
					// 如果包含标题
					int iLength = getElemnetTextLength(title, childElements
							.get(i).text());
					int jLength = getElemnetTextLength(title, childElements
							.get(j).text());
					if (iLength < jLength) {
						Element tempElemnt = childElements.get(i);
						childElements.set(i, childElements.get(j));
						childElements.set(j, tempElemnt);
					}
				}
			}
			// 找到结果最大的那个
			temp = childElements.first();
			if (temp == null) {
				return null;
			}
			// 如果该节点下面没有文字
			if (temp.text().trim().length() == 0) {
				temp = getIncludeTextNode(temp);
				break;
			}

			if (temp != null && temp.children().size() == 0) {
				break;
			}
		}
		if (temp == null) {
			return null;
		}
		return temp;

	}

	// 获取文本的长度
	private int getElemnetTextLength(String title, String itext) {
		int iLength = 0;
		char[] iarray = itext.toCharArray();
		for (char c : iarray) {
			if (c == ',' || c == '，') {
				iLength = iLength + 10;
			} else if (c == '。') {
				iLength = iLength + 30;
			} else if (title.contains(c + "")) {
				iLength = iLength + 10;
			} else {
				iLength = iLength + 1;
			}
		}
		return iLength;
	}

	// 获取有内容的的节点
	private Element getIncludeTextNode(Element temp) {
		while (true) {
			temp = temp.parent();
			if (temp == null) {
				break;
			}
			if (temp.text().trim().length() != 0) {
				break;
			}

		}
		return temp;
	}

	// 得到某元素下孩子节点长度最大的Element
	public Element getMaxTextElement(String url, String title,
			Element parentElement) {
		if (parentElement == null) {
			return null;
		}
		Element temp = getMaxLengthChild(parentElement, title);
		if (temp == null) {
			return null;
		}
		// logger.info(temp);
		if (temp.tagName().equals("td")) {
			// 如果标签是td ，则选择tr 上的tbody
			temp = getTdParentNode(temp);

		} else if (temp.tagName().equals("p") || temp.tagName().equals("div")) {
			// 如果是p 或者是div
			temp = getPnodeParent(temp);

		} else if (temp.tagName().contains("h")) {

			// 如果直接选择了标题列，这个在观察

		} else {
			// 其他标签的话，就先找到最可能的父标签
			temp = getPossibleParent(temp);
			// logger.info(temp);
			if (temp.tagName().equals("td")) {
				// 如果标签是td ，则选择tr 上的tbody
				temp = getTdParentNode(temp);

			} else if (temp.tagName().equals("p")
					|| temp.tagName().equals("div")) {
				// 如果是p 或者是div
				temp = getPnodeParent(temp);

			}

		}
		// logger.info(temp);
		return temp;

	}

	// 获取可能的节点
	private Element getPossibleParent(Element temp) {
		while (true) {
			if (temp.tagName().equals("p") || temp.tagName().equals("div")
					|| temp.tagName().equals("td")) {

				break;
			}
			temp = temp.parent();
		}
		return temp;
	}

	// 获取P 或div的父节点
	private Element getPnodeParent(Element temp) {
		if (temp.siblingElements().size() == 0) {
			// 如果他没有兄弟节点 就选它自己
		} else {
			// 如果有兄弟节点
			Elements elements = temp.siblingElements();

			for (Element element : elements) {

				if (element.tagName().equals(temp.tagName())) {

					if (!element.className().equals(temp.className())) {

						// 如果兄弟节点和自己节点的名称一致,但类名称不一致

						// 样式是居中 ，可能含有图片,保留

						removeNoImageElemnet(element);
						continue;
					}

					if (!element.id().equals(temp.id())) {
						// 如果类名一样,但ID 值不一样的话，去掉
						element.remove();
					}

				} else if (element.tagName().equals("p")
						|| element.tagName().equals("div")) {
					if (!element.className().isEmpty()) {
						element.remove();
					}

				} else if ((!element.tagName().equals("table"))
						&& (!element.tagName().equals("img"))
						&& (!element.tagName().equals("strong"))
						&& (!element.tagName().equals("b"))
						&& (!element.tagName().equals("br"))
						&& (!element.tagName().equals("center"))) {

					// 样式是居中 ，可能含有图片,保留
					removeNoImageElemnet(element);
				}

			}
			temp = temp.parent();
		}
		return temp;
	}

	private void removeNoImageElemnet(Element element) {
		boolean isRemove = true;
		if (element.attr("style").contains("text-align:  center")
				|| element.attr("style").contains("text-align: center")
				|| element.attr("align").contains("center")
				|| element.className().contains("center")) {
			Elements childs = element.getAllElements();
			for (Element element2 : childs) {
				if (element2.tagName().equals("img")) {
					isRemove = false;
					break;
				}
			}

		}
		if (isRemove) {
			element.remove();
		}
	}

	private Element getTdParentNode(Element temp) {
		while (true) {
			if (temp.tagName().equals("table")) {
				break;
			}
			temp = temp.parent();
		}
		// 直接获取的到table 标签

		Elements elements = temp.siblingElements();
		if (elements.size() == 0) {
			// 如果他没有兄弟节点
		} else {
			// 如果有的话

			for (Element element : elements) {
				if ((!element.tagName().equals(temp.tagName()))
						&& (!element.tagName().equals("p"))
						&& (!element.tagName().equals("div"))) {
					// 如果不是p 或者table 或者div 的话，直接去除就可以了
					element.remove();
				}
			}

			/***
			 * 这里处理的有点鲁莽， 后期改进
			 */
			temp = temp.parent();
		}

		return temp;
	}

	// 移除所有的属性
	private void removeAttr(Element element) {
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
							.contains("text-align:center"))) {
				element.removeAttr(attribute.getKey());
				element.attr("class", "p_center");
				continue;
			} else if (attribute.getKey().equals("style")
					&& (attribute.getValue().toLowerCase()
							.contains("text-align: center"))) {
				element.removeAttr(attribute.getKey());
				element.attr("class", "p_center");
				continue;
			}

			else if (attribute.getKey().equals("rowspan")
					|| attribute.getKey().equals("colspan")) {
				continue;
			} else {
				element.removeAttr(attribute.getKey());
			}
		}
	}

	// 去除不需要的板块
	public void removeWaste(Element element) {
		if (element != null && element.attr("style").contains("display:none;")) {
			element.remove();
		} else if (element.tagName().equals("script")
				|| element.tagName().equals("style")
				|| element.tagName().equals("textarea")
				|| element.tagName().equals("select")) {
			element.remove();
		} else if (element.tagName().equals("li")
				|| element.tagName().equals("dl")
				|| element.tagName().equals("dt")
				|| element.tagName().equals("dd")) {
			removeLinkNode(element);
		}

	}

	// 去除无关链接
	private void removeLinkNode(Element element) {
		boolean isRemove = true;
		Elements elements = element.children();
		for (Element element2 : elements) {
			if (element2.tagName().equals("a")
					|| element2.tagName().equals("br")
					|| element2.tagName().equals("img")
					|| element2.tagName().equals("span")) {

				// 如果是a br , img ,span 就删掉
				continue;
			} else {
				// 如果是p或者别的标签
				Elements elements2 = element2.siblingElements();

				if (elements2.size() == 0) {
					// 如果没有兄弟节点
					if (element2.children().size() == 1
							&& element2.children().first().tagName()
									.equals("a")) {
						// 如果他的孩子节点只有一个a标签的话，也去除

					} else {
						isRemove = false;
						break;
					}

				} else {
					// 兄弟节点很多， 保留
					isRemove = false;
					break;
				}
			}

		}
		if (isRemove) {
			element.remove();
		}
	}

	/**
	 * 将element中没有被P标签包裹的文字内容 用P标签包裹
	 * 
	 * @param element
	 */
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

	private void wrapTextNode(Element element) {
		List<TextNode> textNodes = element.textNodes();
		if (textNodes.size() != 0) {
			for (TextNode tn : textNodes) {
				tn.wrap("<p></p>");
			}
		}
	}

	public String wrapTagToProtecteChars(List<String> regList, String content) {
		if (content != null && regList.size() > 0) {
			for (String reg : regList) {
				Pattern p = Pattern.compile(reg);
				Matcher matcher = p.matcher(content);
				while (matcher.find()) {
					String str = matcher.group();
					content = content.replace(
							str,
							str.replace("(", "<protecteChars>").replace(")",
									"</protecteChars>"));
				}
			}
		}
		return content;
	}

	public String unwrapTagFromContent(String content) {
		if (content != null && regList.size() > 0) {
			content = content.replaceAll("<protecteChars>", "(");
			content = content.replaceAll("</protecteChars>", ")");
		}
		return content;
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

	public String getContent(String url, Document document, String title) {
		Element maxTextElement = removeNoNeedHtmlElement(url, document, title);
		if (maxTextElement == null) {
			return null;
		}
		removeAttr(maxTextElement);
		removeAdvertiseNode(maxTextElement);
		formatElements(maxTextElement);
		saveImage(maxTextElement, url);
		String content = maxTextElement.toString();
		content = StringUtil.angleConvertion(content);
		content = replaceStockCode(content);
		content = replaceSpechars(content);
		content = addTag(content);
		return content;
	}

	private void removeAdvertiseNode(Element maxTextElement) {
		removeAdvertiseText(maxTextElement);

	}

	@Override
	public String getContent(String url, String htmlString, String title) {
		if (htmlString == null && "".equals(htmlString.trim())) {
			return null;
		}
		Document doc = Jsoup.parse(htmlString);

		return getContent(url, doc, title);
	}

}
