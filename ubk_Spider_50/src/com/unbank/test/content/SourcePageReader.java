package com.unbank.test.content;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;

import com.unbank.test.FileOperate;

public class SourcePageReader
{
	private static final String SOURCEPATH="D:/TestParser/sourcePages/";
	private static final String URLPATH="D:/TestParser/urls.txt";
	
	public Map<Integer ,Map<String,String>> getSource(){
		List<Website> sourceWebsiteList=new ArrayList<Website>();
		Map<Integer ,Map<String,String> > sourceMap=new HashMap<Integer,Map<String,String>>();
		sourceWebsiteList=	getWebsite();
		for(Website sw:sourceWebsiteList){
			sourceMap.put(sw.getId(), getSourcesById(sw.getId()));
		}
		return sourceMap;
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
	
	
	/**
	 * 获取网页源码
	 * @param website_id
	 * @return
	 */
	private Map<String,String> getSourcesById(int website_id){
		Map<String,String> map=new HashMap<String,String>();
		System.out.println("website_id:"+website_id);
		String sourcePath=SOURCEPATH+website_id+"/";
		System.out.println("path:"+sourcePath);
		
		String[] names= FileOperate.getFilePaths(sourcePath);
		for(String name:names){
			String path=sourcePath+name;
			String content= FileOperate.readToString(path);
			String key= name.split("\\.")[0];
			map.put(key, content);
		}
		return map;
	}
	
	
}
