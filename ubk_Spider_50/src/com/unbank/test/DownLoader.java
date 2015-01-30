package com.unbank.test;

import java.io.IOException;

import org.jsoup.Connection;
import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;

public class DownLoader
{

	public static String downLoad(String url)
	{
		Connection conn = Jsoup.connect(url);
		Document doc = null;
		try
		{
			doc = conn.post();
		} catch (IOException e)
		{
			e.printStackTrace();
		}
		if (doc == null)
		{
			return null;
		}
		return doc.toString();
	}
	
	
	public static void main(String[] args)
	{
		String url="http://www.caam.org.cn/qiyexinwen/20140721/0905125104.html";
		String path="D:/TestParser/sourcePages/"+"376/376_1"+".txt";
		String doc= downLoad(url);
		System.out.println(doc);
		FileOperate.saveStringToFile(doc, path);
	}
}
