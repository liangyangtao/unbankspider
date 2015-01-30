package com.unbank.robotspider.util;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

/**
 * 
 * @author liangqingyu
 * 
 */
public class SmartTitle {
	private static float scoreFlag = 0.4f;


	public static String getTitle(Document document, String listTitle) {
		Document doc = document.clone();
		String title = "";
		float f = 0;

		// 如果含有h1 ...... h5, 现通过这个地方去找
		boolean isHx = true;
		for (int i = 1; i <= 5; i++) {
			Elements elements = doc.getElementsByTag("h" + i);
			if (elements.size() > 0) {
				// 找到h标签就不和网址的标题比较了。
				isHx = false;
				for (Element e : elements) {
					String s_title = e.text();
					System.out.println("dsfds" + s_title);
					float ff = Levenshtein.getSimilarityRatio(listTitle,
							s_title);
					System.out.println(ff + "--" + s_title);
					if (ff > scoreFlag && ff > f) {
						title = s_title;
						f = ff;
					}
				}
			}
		}

		if (isHx) {
			String doc_title = doc.title();
			float ff = Levenshtein.getSimilarityRatio(listTitle, doc_title);
			System.out.println(" 没有h   fdsf " + ff);
			if (ff > 0.8) {
				title = listTitle;
			}else{
				
			}

		} else {
			title = (title == null || title.trim().isEmpty()) ? listTitle
					: title;
		}

		// 如果不含有hX标签
		// 网址的标题

		return title;
	}
}
