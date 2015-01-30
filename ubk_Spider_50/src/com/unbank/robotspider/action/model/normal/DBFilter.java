package com.unbank.robotspider.action.model.normal;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSession;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import com.unbank.robotspider.dao.MyBatisConnectionFactory;
import com.unbank.robotspider.dao.WebsiteParserMapper;
import com.unbank.robotspider.entity.WebsiteParser;

public class DBFilter {
	private static Log logger = LogFactory.getLog(DBFilter.class);
	private static  Map<String, Filter> filtermap = new HashMap<String, Filter>();

	private static DBFilter dBFilter = new DBFilter();
	
	
	public void loadFiltersFromDB(){
		//logger.info("========加载DBFilter中========"+filtermap.size());
		SqlSession sqlSession = MyBatisConnectionFactory.getInstanceSessionFactory().openSession();
		WebsiteParserMapper websiteParserMapper = sqlSession.getMapper(WebsiteParserMapper.class);
		List<WebsiteParser> parserList = websiteParserMapper.selectByExample(null);
		for (WebsiteParser parser : parserList) {
			filtermap.put(parser.getContenturlHost(), new InnerFilter(parser));
		}
	}

	public Map<String, Filter> getFilters() {
		logger.info("========getDBFilter========"+filtermap.size());
		if (filtermap.size() == 0) {
			loadFiltersFromDB();
		}
		return filtermap;
	}

	public static DBFilter getInstance() {
		return dBFilter;
	}

	public class InnerFilter extends BaseFilter {
		private WebsiteParser parser;

		public InnerFilter(WebsiteParser parser) {
			this.parser = parser;
			//filtermap.put(parser.getContenturlHost(), this);
		}

		@Override
		public Element removeNoNeedHtmlElement(String url, Document document, String title) {
			System.out.println("从数据库中读取的解析规则，解析页面：" + url);
			remove(document.body());
			Element bodyElement = document.body();
			if (bodyElement == null) {
				return null;
			}

			String needlessElementPaths = parser.getNeedlesselementsPath();
			if (!StringUtils.isBlank(needlessElementPaths)) {

				String[] paths = needlessElementPaths.split("\\|\\|");
				for (String path : paths) {
					removeNoNeedElement(bodyElement, path);
				}
			}
			Elements aimElements = bodyElement.select(parser.getNewscontentPath());
			if (aimElements != null && aimElements.size() > 0) {
				return aimElements.first();
			}

			return super.removeNoNeedHtmlElement(url, document, title);
		}

		@Override
		public void removeNoNeedElement(Element element, String string) {
			if (element == null) {
				return;
			}
			Elements elements = element.select(string);
			if (elements == null || elements.size() == 0) {
				return;
			} else {
				for (Element element2 : elements) {
					element2.remove();
				}
			}
			return;
		}

		@Override
		public String removeStockCode(String content) {
			// TODO Auto-generated method stub
			return super.removeStockCode(content);
		}

		@Override
		public String replaceSpechars(String content) {
			String needlessStr = parser.getNeedlesscharsPath();

			if (!StringUtils.isBlank(needlessStr)) {
				String[] needlessChars = needlessStr.split("\\|\\|");

				for (String needlessChar : needlessChars) {
					content = content.replace(needlessChar, "");
				}
			}
			String needlessTailStr = parser.getNeedlesstailsPath();
			
			if (!StringUtils.isBlank(needlessTailStr)) {

				String[] needlessTailChars = needlessTailStr.split("\\|\\|");

				for (String tail : needlessTailChars) {
					if (content.contains(tail)) {
						int index = content.indexOf(tail);
						content = content.substring(0, index);
					}
				}
			}
			
			return super.replaceSpechars(content);
		}

		@Override
		public String getContent(String url, Document document, String title) {
			return super.getContent(url, document, title);
		}

		@Override
		public String getContent(String url, String htmlString, String title) {
			return super.getContent(url, htmlString, title);
		}

	}

	/*
	 * public class InnerFilter{
	 * 
	 * private String appendStr; private String key;
	 * 
	 * public InnerFilter(String key ,String appendStr){ this.key=key;
	 * this.appendStr=appendStr; DBFilter.filterMap.put(key, this); }
	 * 
	 * public String doFilter(String str){ return str+appendStr; } }
	 */

}
