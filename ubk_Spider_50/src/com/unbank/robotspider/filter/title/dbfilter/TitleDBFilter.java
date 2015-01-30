package com.unbank.robotspider.filter.title.dbfilter;

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
import com.unbank.robotspider.filter.title.TitleBaseFilter;
import com.unbank.robotspider.filter.title.TitleFilter;

public class TitleDBFilter {
	private static Log logger = LogFactory.getLog(TitleDBFilter.class);
	private static TitleDBFilter titleDBFilter=new TitleDBFilter();
	
	private TitleDBFilter(){}
	
	private static HashMap<String, TitleFilter> filtermap = new HashMap<String, TitleFilter>();
	
	
	public void loadFiltersFromDB(){
		//logger.info("========加载TitleDBFilter中========"+filtermap.size());		
		SqlSession sqlSession = MyBatisConnectionFactory.getInstanceSessionFactory().openSession();
		WebsiteParserMapper websiteParserMapper = sqlSession.getMapper(WebsiteParserMapper.class);

		List<WebsiteParser> parserList = websiteParserMapper.selectByExample(null);

		for (WebsiteParser parser : parserList) {
			filtermap.put(parser.getContenturlHost(), new InnerFilter(parser));
		}
	}
	
	public Map<String,TitleFilter> getFilters(){
		logger.info("========getTitleDBFilter======="+filtermap.size());
		if (filtermap.size() == 0) {
			loadFiltersFromDB();
		}
		
		return filtermap;
	}
	
	public static TitleDBFilter getInstance(){
		return titleDBFilter;
	}

	
	public class InnerFilter extends TitleBaseFilter{
		private WebsiteParser parser;
		
		public InnerFilter(WebsiteParser parser) {
			this.parser = parser;
			//filtermap.put(parser.getContenturlHost(), this);
		}

		@Override
		public String getTitle(Document document) {
			String titlePath=parser.getNewstitlePath();
			
			if(!StringUtils.isBlank(titlePath)){
				Elements titleElements= document.select(titlePath);
				if(titleElements!=null&&titleElements.size()>0){
					Element titleElement=titleElements.first();
					return titleElement.text();
				}
			}
			
			return super.getTitle(document);
		}
		
		@Override
		public String getTitle(Document document, String Alternativetitle) {
			String titlePath=parser.getNewstitlePath();
			
			if(!StringUtils.isBlank(titlePath)){
				Elements titleElements= document.select(titlePath);
				if(titleElements!=null&&titleElements.size()>0){
					Element titleElement=titleElements.first();
					return titleElement.text();
				}
			}
			
			return super.getTitle(document, Alternativetitle);
		}
		
		
	}
	
}
