package com.unbank.robotspider.fetch;

import org.jsoup.nodes.Document;

/**
 * 
 * @author 梁擎宇
 *
 */
public interface Fetcher
{
	/**
	 * 获取文本源码
	 * @param url
	 * @return
	 */
	public Document fetchText(String url); 
	/**
	 * 下载图片
	 * @param imageSrc
	 * @return
	 */
	public String fetchImage(String imageSrc,ImageFilter imageFilter);
}
