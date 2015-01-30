package com.unbank.robotspider.action.filter;

import java.util.ArrayList;
import java.util.List;

import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;
import org.springframework.stereotype.Service;

import com.unbank.robotspider.action.model.normal.BaseFilter;
import com.unbank.robotspider.action.model.normal.FilterLocator;

@Service
public class ComZhicheng extends BaseFilter
{
	private String domain="www.zhicheng.com";
	
	public ComZhicheng(){
		FilterLocator.getInstance().register(domain, this);
	}
	
	@Override
	public Element removeNoNeedHtmlElement(String url, Document document, String title)
	{
		
		Element element = document.select("#artibody").first(); 
		return element;

	}

	@Override
	public void formatElements(Element maxTextElement)
	{
		super.formatElements(maxTextElement);
	}

	@Override
	public void saveImage(Element maxTextElement, String url)
	{
		super.saveImage(maxTextElement, url);
	}

	@Override
	public String replaceStockCode(String content)
	{
		return super.replaceStockCode(content);
	}

	@Override
	public String replaceSpechars(String content)
	{
		String str=super.replaceSpechars(content);
		List<String> list=new ArrayList<String>();
		list.add("(,)");
		
		for(int i=0;i<list.size();i++){
			String s=list.get(i);
			if(str.contains(s)){
				str=str.replace(s,"");
			}
		}
		
		
		return str;
	}

	@Override
	public String addTag(String content)
	{
		return super.addTag(content);
	}

}
