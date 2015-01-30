package com.unbank.robotspider.action.model.normal;


import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

public interface Filter
{
	/**
	 * 移除不需要的标签，并且返回内容标签
	 * @param url
	 * @param document
	 * @param title
	 * @return
	 */
	public Element removeNoNeedHtmlElement(String url, Document document,
			String title);
	
	/**
	 * 将内容标签格式化
	 * @param maxTextElement
	 */
	public void formatElements(Element maxTextElement);
	
	/**
	 * 保存内容中的图片
	 * @param maxTextElement
	 * @param url
	 */
	public void saveImage(Element maxTextElement,String url);
	
	/**
	 * 移除内容中的股票代码
	 * @param content
	 * @return
	 */
	public String replaceStockCode(String content);
	
	/**
	 * 移除内容中的特殊字符
	 * @param content
	 * @return
	 */
	public String  replaceSpechars(String content);
	
	/**
	 * 为内容添加标签，最后的处理
	 * @param content
	 * @return
	 */
	public String addTag(String content);
	
	/**
	 * 解析内容
	 * @param url
	 * @param document
	 * @param title
	 * @return
	 */
	public String getContent(String url,Document document,String title);
	
	/**
	 * 解析内容
	 * @param url
	 * @param htmlString
	 * @param title
	 * @return
	 */
	public String getContent(String url,String htmlString,String title);
	

}
