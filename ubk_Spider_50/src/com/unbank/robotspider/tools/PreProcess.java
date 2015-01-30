package com.unbank.robotspider.tools;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import org.jsoup.nodes.Document;

public class PreProcess
{

	public static List<String> process(Document document){
		List<String> lines=new ArrayList<String>();
		
		document.getElementsByTag("li").remove();
		
		String html=document.html();
		
		html = html.replaceAll("(?is)<!DOCTYPE.*?>", "");
		html = html.replaceAll("(?is)<!--.*?-->", "");				// remove html comment
		html = html.replaceAll("(?is)<script.*?>.*?</script>", ""); // remove javascript
		html = html.replaceAll("(?is)<iframe.*?>.*?</iframe>", ""); // remove javascript
		html = html.replaceAll("(?is)<style.*?>.*?</style>", "");   // remove css
		html = html.replaceAll("&.{2,5};|&#.{2,5};", " ");			// remove special char
		html = html.replaceAll("(?is)<.*?>", "");
		
		lines = Arrays.asList(html.split("\n"));
		
		return lines;
	}
}
