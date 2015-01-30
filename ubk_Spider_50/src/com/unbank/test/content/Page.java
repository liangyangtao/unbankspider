package com.unbank.test.content;


import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class Page
{
	/**
	 * 图片数量
	 */
	private int imgCount;
	/**
	 * 完整内容
	 */
	private String content;
	/**
	 * 不包含图片的内容
	 */
	private String contentWithoutImg;
	/**
	 * 新闻时间
	 */
	private String time;
	/**
	 * 新闻标题
	 */
	private String title;
	
	
	private Website sourceWebsite;
	
	public Website getSourceWebsite()
	{
		return sourceWebsite;
	}
	public void setSourceWebsite(Website sourceWebsite)
	{
		this.sourceWebsite = sourceWebsite;
	}
	
	public String getTime()
	{
		return time;
	}
	public void setTime(String time)
	{
		this.time = time;
	}
	public String getTitle()
	{
		return title;
	}
	public void setTitle(String title)
	{
		this.title = title;
	}
	public int getImgCount()
	{
		return imgCount;
	}
	public void setImgCount(int imgCount)
	{
		this.imgCount = imgCount;
	}
	public String getContent()
	{
		return content;
	}
	public void setContent(String content)
	{
		this.content = content;
		Document doc= Jsoup.parse(content);
		imgCount= doc.getElementsByTag("img").size();
		doc.getElementsByTag("img").remove();
		contentWithoutImg=doc.body().children().toString();
	}
	public String getContentWithoutImg()
	{
		return contentWithoutImg;
	}
	public void setContentWithoutImg(String contentWithoutImg)
	{
		this.contentWithoutImg = contentWithoutImg;
	}
	@Override
	public String toString()
	{
		return "Page [imgCount=" + imgCount + ", content=" + content + ", contentWithoutImg=" + contentWithoutImg + ", time=" + time + ", title=" + title
				;
	}
	@Override
	public boolean equals(Object obj)
	{
		if(!(obj instanceof Page)){
			return false;
		}
		Page p=(Page)obj;
		if(this.contentWithoutImg.equals(p.getContentWithoutImg())
				&&this.imgCount==p.imgCount
				){
			return true;
		}
		return false;
	}
	
	
}
