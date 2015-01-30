package com.unbank.robotspider.filter.titlelist.dbfilter;

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
import com.unbank.robotspider.filter.titlelist.BaseTitleListFilter;
import com.unbank.robotspider.filter.titlelist.TitleListFilter;

public class TitleListDBFilter {
	private static Log logger = LogFactory.getLog(TitleListDBFilter.class);
	private static TitleListDBFilter titleListDBFilter=new TitleListDBFilter();
	private TitleListDBFilter(){}
	
	private static Map<String,TitleListFilter> filtermap=new HashMap<String,TitleListFilter>();
	
	
	public void loadFiltersFromDB(){
		//logger.info("========加载TitleListDBFilter中========"+filtermap.size());
		SqlSession sqlSession = MyBatisConnectionFactory.getInstanceSessionFactory().openSession();
		WebsiteParserMapper websiteParserMapper = sqlSession.getMapper(WebsiteParserMapper.class);
		
		List<WebsiteParser> parserList = websiteParserMapper.selectByExample(null);
		
		for (WebsiteParser parser : parserList) {
			filtermap.put(parser.getContenturlHost(), new InnerFilter(parser));
		}
	}
	
	public Map<String,TitleListFilter> getFilters(){
		logger.info("========getTitleListDBFilter========"+filtermap.size());
		if (filtermap.size() == 0) {
			loadFiltersFromDB();
		}
		
		return filtermap;
	}
	
	public static TitleListDBFilter getInstance(){
		
		return titleListDBFilter;
		
	}
	
	public class InnerFilter extends BaseTitleListFilter{
		
		private WebsiteParser parser;
		
		public InnerFilter(WebsiteParser parser) {
			this.parser = parser;
			//filtermap.put(parser.getContenturlHost(), this);
		}
		
		@Override
		public Elements getPossibleListElement(Document document) {
			
			String listPath=parser.getListPath();
			String listNeedlessPath=parser.getListNeedlesselementsPath();
			
			if(!StringUtils.isBlank(listNeedlessPath)){
				String[] paths = listNeedlessPath.split("\\|\\|");
				for (String path : paths) {
					removeNoNeedElement(document, path);
				}
			}
			
			if(!StringUtils.isBlank(listPath)){
			    Elements listElements=	document.select(listPath);
			    if(listElements!=null&&listElements.size()>0){
			    	return listElements;
			    }else{
			    	return super.getPossibleListElement(document);
			    }
			}else{
				return super.getPossibleListElement(document);
			}
			
		}
		
		
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
	}
	
}
