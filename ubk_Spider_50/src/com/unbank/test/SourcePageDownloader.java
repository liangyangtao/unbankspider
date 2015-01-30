package com.unbank.test;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.commons.lang.StringUtils;
import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

import com.unbank.test.content.SourceWebsite;
import com.unbank.test.content.Verifier;


public class SourcePageDownloader
{
	private static final String SOURCEPATH="D:/TestParser/sourcePages/";
	private static final String URLPATH="D:/TestParser/urls.txt";
	private static final String SOURCECATALOGPATH="D:/TestParser/sourcePages/source.txt";
	
	
	/**
	 * 读取需要测试的网址列表
	 * @return
	 */
	private static List<SourceWebsite> getSourceWebsites(){
		List<String> urlList = new ArrayList<String>();
		List<SourceWebsite> sourceWebsiteList=new ArrayList<SourceWebsite>();
		urlList = FileOperate.readLines(SOURCECATALOGPATH);
		for(String s:urlList){
			if(!StringUtils.isBlank(s)){
				String[] ss= s.split(" ");
				if(ss.length==2){
					SourceWebsite sw=new SourceWebsite();
					sw.setUrl(ss[1]);
					sw.setId(ss[0]);
					sourceWebsiteList.add(sw);
				}
			}
		}
		return sourceWebsiteList;
	}
	
	public static String downLoad(String url){
		String sourceText=downLoad_get(url);
		if(StringUtils.isBlank(sourceText)){
			sourceText=downLoad_post(url);
		}
		return sourceText;
	}
	
	
	private static String downLoad_get(String url)
	{
		Connection conn = Jsoup.connect(url);
		Document doc = null;
		try
		{
			doc = conn.get();
		} catch (IOException e)
		{
			e.printStackTrace();
			FileOperate.saveStringToFile(url+"|", SOURCEPATH+System.currentTimeMillis()+"_errors.txt");
			System.out.println("GET抓取失败。url："+url);
			return null;
		}
		if (doc == null)
		{
			return null;
		}
		return doc.toString();
	}

	private static String downLoad_post(String url)
	{
		Connection conn = Jsoup.connect(url);
		Document doc = null;
		try
		{
			doc = conn.get();
		} catch (IOException e)
		{
			e.printStackTrace();
			FileOperate.saveStringToFile(url+"|", SOURCEPATH+System.currentTimeMillis()+"_errors.txt");
			System.out.println("POST抓取失败。url："+url);
			return null;
		}
		if (doc == null)
		{
			return null;
		}
		return doc.toString();
	}
	
	
	public static void main(String[] args)
	{
		List<SourceWebsite> sourceList=getSourceWebsites();
		
		for(SourceWebsite website:sourceList){
			String id= website.getId();
			String url=website.getUrl();
			String sourceText= downLoad(url);
			if(sourceText==null){
				sourceText= downLoad(url);
			}
			if(sourceText==null){
				FileOperate.saveStringToFile(id+"|", SOURCEPATH+id+"_errors.txt");
				continue;
			}
			System.out.println("下载完成："+url);
			String p_id= id.split("_")[0];
			
			String path=SOURCEPATH+p_id+"/"+id+".txt";
			FileOperate.saveStringToFile(sourceText, path);
			
		}
		
	}
	
}
