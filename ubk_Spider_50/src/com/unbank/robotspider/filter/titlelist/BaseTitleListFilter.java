package com.unbank.robotspider.filter.titlelist;

import org.apache.commons.lang3.StringUtils;
import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class BaseTitleListFilter extends TitleListFilter {

	public Logger logger = Logger.getLogger(BaseTitleListFilter.class);

	/***
	 * 比较文本长度来获得， 只是比较的第一层，
	 */
	public Elements getPossibleListElement(Document document) {
		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}
		// 获取特征节点
		Elements futureNodes = getFutureElements(bodyElement);
		if (futureNodes.size() == 0) {
			return bodyElement.select("a");
		}

		Elements possibleListElements = getPossibleListElements(bodyElement,
				futureNodes);
		return possibleListElements;

	}

	public Elements getPossibleListElement(Document document, String string) {
		Element bodyElement = document.body();
		if (bodyElement == null) {
			return null;
		}
		Elements parents = bodyElement.select(string);
		Elements possibleListElements = null;
		if (parents.size() != 0) {
			Element parent = parents.first();
			removeNoNeedElementsByCssQuery(parent);
			possibleListElements = parent.select("a");
		}
		return possibleListElements;
	}

	public void removeNoNeedElementsByCssQuery(Element contentElement) {
		// String cssQuerys[] = new String[] { "div.blank10", "div.f14",
		// "div.blank5" };
		// for (String cssQuery : cssQuerys) {
		// removeNoNeedElement(contentElement, cssQuery);
		// }
	}

	private Elements getPossibleListElements(Element bodyElement,
			Elements futureNodes) {
		Elements elements = bodyElement.getAllElements();
		Element endElement = futureNodes.get(futureNodes.size() - 1);
		Elements possibleListElements = new Elements();
		for (Element element : elements) {
			if (element.equals(endElement)) {
				break;
			} else {
				if (element.tagName().equals("a")) {
					possibleListElements.add(element);
				}
			}

		}
		return possibleListElements;
	}

	/***
	 * 获取特征节点
	 * 
	 * @param bodyElement
	 * @return
	 */
	private Elements getFutureElements(Element bodyElement) {
		Elements possibleNode = new Elements();
		Elements elements = bodyElement.select("a");
		for (Element element : elements) {
			String linkText = element.text().trim();
			linkText = linkText.replace(" ", "");
			linkText = linkText.replace("[", "");
			linkText = linkText.replace("]", "");
			boolean isFuture = checkLinkText(linkText);
			if (isFuture) {
				possibleNode.add(getDifferentTextParent(element));
			}
		}
		return possibleNode;
	}

	private Element getDifferentTextParent(Element element) {
		Element temp = element;
		while (true) {
			if (temp.text().equals(temp.parent().text())) {

			} else {
				break;
			}
			temp = temp.parent();
		}
		return temp.parent();
	}

	/***
	 * 判断是否为特征节点
	 * 
	 * @param linkText
	 * @return
	 */
	private boolean checkLinkText(String linkText) {

		String[] feature = { "下一页", "Next", "末页", "尾页" };
		for (int i = 0; i < feature.length; i++) {
			if (feature[i].equals(linkText)) {
				return true;
			} else if (StringUtils.isNumeric(linkText)) {
				return true;
			}
		}
		return false;
	}

}
