package com.unbank.test.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.unbank.test.FileOperate;

public class TestCaseReader
{
	private static final String URLPATH="D:/TestParser/urls.txt";
	private static final String CONTENTPATH="D:/TestParser/contents/";
	private static final String TITLEPATH="D:/TestParser/titles/";
	private static final String TIMEPATH="D:/TestParser/times/";
	
	public Map<Integer,Map<String,Page>> getPages(){
		List<Website> sourceWebsiteList=new ArrayList<Website>();
		Map<Integer,Map<String,Page>> pageMap=new HashMap<Integer,Map<String,Page>>();
		sourceWebsiteList=	getWebsite();
		for(Website sw:sourceWebsiteList){
			Map<String,Page> map=new HashMap<String,Page>();
			map= getPagesById(sw.getId());
			pageMap.put(sw.getId(), map);
		}
		return pageMap;
	}
	
	
	/**
	 * 读取需要测试的网址列表
	 * @return
	 */
	private List<Website> getWebsite(){
		List<String> urlList = new ArrayList<String>();
		List<Website> sourceWebsiteList=new ArrayList<Website>();
		urlList = FileOperate.readLines(URLPATH);
		for(String s:urlList){
			if(!StringUtils.isBlank(s)){
				String[] ss= s.split(" ");
				if(ss.length==2){
					Website sw=new Website();
					sw.setUrl(ss[0]);
					sw.setId(Integer.valueOf(ss[1]));
					sourceWebsiteList.add(sw);
				}
			}
		}
		return sourceWebsiteList;
	}
	
	private Map<String,Page> getPagesById(int website_id){
		Map<String,Page> map=new HashMap<String,Page>();
		String contentsPath=CONTENTPATH+website_id+"/";
		String titlesPath=TITLEPATH+website_id+"/";
		String timesPath=TIMEPATH+website_id+"/";
		
		String[] names= FileOperate.getFilePaths(contentsPath);
		for(String name:names){
			String contentPath=contentsPath+name;
			String titlePath=titlesPath+name;
			String timePath=timesPath+name;
			String content= FileOperate.readToString(contentPath);
			String title= FileOperate.readToString(titlePath);
			String time= FileOperate.readToString(timePath);
			Page p=new Page();
			p.setContent(content);
			p.setTime(title);
			p.setTime(time);
			String key= name.split("\\.")[0];
			map.put(key, p);
		}
		return map;
	}
	
}
