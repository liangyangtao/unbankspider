package com.unbank.robotspider.filter.content;

import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

public class ContentBaseFilter extends ContentFilter {
	public Logger logger = Logger.getLogger(ContentBaseFilter.class);

	public Element getContentNode(Document document) {
		return getMaxTextElement(document);
	}

	private Element getMaxTextElement(Document document) {
		if (document == null) {
			return null;
		}

		Element bodyElement = document.body();
		cleanBodyNode(bodyElement);
		Element temp = getMaxLengthChild(bodyElement);
		try {
			if (temp == null) {
				return null;
			}
			temp = findPossibleContentNode(temp);
		} catch (Exception e) {
			logger.info("获取内容节点失败", e);
		}
		return temp;
	}

	public Element findPossibleContentNode(Element temp) {
		temp = getPossibleParent(temp);
		if (temp.tagName().equals("td")) {
			temp = getTdParentNode(temp);
		} else if (temp.tagName().equals("p") || temp.tagName().equals("div")) {
			temp = getPnodeParent(temp);
		}
		return temp;
	}

	private Element getPossibleParent(Element temp) {
		while (true) {
			if (temp.tagName().equals("p") || temp.tagName().equals("div")
					|| temp.tagName().equals("td")) {
				break;
			}
			temp = temp.parent();
			if (temp == null) {
				break;
			}
		}
		return temp;
	}

	private Element getPnodeParent(Element temp) {
		if (temp == null) {
			return null;
		}
		if (temp.siblingElements().size() != 0) {
			Elements elements = temp.siblingElements();
			for (Element element : elements) {
				if (element.tagName().equals(temp.tagName())) {
					if (!element.className().equals(temp.className())) {
						removeNoImageElemnet(element);
						continue;
					}
					if (!element.id().equals(temp.id())) {
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
					removeNoImageElemnet(element);
				}

			}
			temp = temp.parent();
		}
		return temp;
	}

	private void removeNoImageElemnet(Element element) {
		boolean isRemove = true;
		if (element.attr("style").contains("text-align: center")
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

	public Element getMaxLengthChild(Element parentElement) {
		Element temp = parentElement;
		while (true) {
			if (temp == null) {
				return null;
			}
			Elements childElements = temp.children();
			compareTextLength(childElements);
			temp = childElements.first();
			if (temp == null) {
				return null;
			}
			if (temp.text().trim().length() == 0) {
				temp = getIncludeTextNode(temp);
				break;
			}

			if (temp != null && temp.children().size() == 0) {
				break;
			}
		}
		return temp;

	}

	private void compareTextLength(Elements childElements) {
		for (int i = 0; i < childElements.size() - 1; i++) {
			for (int j = i + 1; j < childElements.size(); j++) {
				int iLength = getElemnetTextLength(childElements.get(i).text());
				int jLength = getElemnetTextLength(childElements.get(j).text());
				if (iLength < jLength) {
					Element tempElemnt = childElements.get(i);
					childElements.set(i, childElements.get(j));
					childElements.set(j, tempElemnt);
				}
			}
		}
	}

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

	private int getElemnetTextLength(String itext) {
		int iLength = 0;
		char[] iarray = itext.toCharArray();
		for (char c : iarray) {
			if (c == ',' || c == '，') {
				iLength = iLength + 10;
			} else if (c == '。') {
				iLength = iLength + 30;
			} else {
				iLength = iLength + 1;
			}
		}
		return iLength;
	}

	public void cleanBodyNode(Element bodyElement) {
		removeNoNeedElement(bodyElement);
		removeAdvertiseLink(bodyElement);
	}

	public void removeNoNeedElement(Element element) {
		if (element != null) {
			Elements elements = element.children();
			for (Element element2 : elements) {
				removeWasteNode(element2);
				if (element2 == null) {
					continue;
				} else {
					removeNoNeedElement(element2);
				}
			}
		}
	}

	private Element getTdParentNode(Element temp) {
		while (true) {
			if (temp.tagName().equals("table")) {
				break;
			}
			temp = temp.parent();
		}
		temp = removeNoNeedBrotherNode(temp);
		return temp;
	}

	private Element removeNoNeedBrotherNode(Element temp) {
		Set<String> needElements = new HashSet<String>();
		needElements.add("div");
		needElements.add("p");
		needElements.add("table");
		needElements.add("center");
		needElements.add("strong");
		needElements.add("br");
		needElements.add("img");
		Elements elements = temp.siblingElements();
		if (elements.size() != 0) {
			for (Element element : elements) {
				if (!elements.contains(element.tagName())) {
					element.remove();
				}
			}
			temp = temp.parent();
		}
		return temp;
	}
}
