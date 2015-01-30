package com.test;

import java.io.IOException;
import java.net.URLDecoder;
import java.net.URLEncoder;

import org.jsoup.nodes.Document;

import com.unbank.robotspider.fetch.JsoupNetFetcher;

public class ContentTest {

	public static final String a = "dsdf";

	public static void main(String[] args) throws IOException {
		// // new ClassPathXmlApplicationContext(
		// // new String[] { "applicationContext.xml" });
		// // String url =
		// // "http://news.takungpao.com/paper/q/2014/1112/2821888.html";
		// //
	     String url ="http://114.80.154.46/briefingdeploy/%7Bcea6b3de-e94b-4176-b806-1034ecf811e8%7D.xml";
		  Document document = new JsoupNetFetcher().fetchText(url);
		  System.out.println(document);
		// // // System.out.println(document);
		// // String title = document.title();
		// // title = new TitlePaser().getTitle(document, title);
		// // System.out.println(title);
		// // String content = new ContentPaser().extractNewsContent(url,
		// document,
		// // title);
		// // // // FileUtils.writeStringToFile(new
		// File("d:/test/t112312.html"),
		// // // content,"gbk");
		// // System.out.println(content);
		//
		// String a =
		// "<div><p> dsfsdfsfdsd</p><img src =\"asdfds\" alt=\"dsfdsf\" title=\"fsdfsdf\" border=\"0\"><p> dsfsfafds</p><img src =\"asdfds\" alt=\"dsfdsf\" title=\"fsdfsdf\" border=\"0\"></div>";
		// Document document = Jsoup.parse(a);
		// Elements elements = document.select("img");
		// for (Element element : elements) {
		// Attributes attributes = element.attributes();
		// for (Attribute attribute : attributes) {
		// if (attribute.getKey().isEmpty()) {
		// continue;
		// } else if (attribute.getKey().equals("src")) {
		// continue;
		// } else {
		// element.removeAttr(attribute.getKey());
		// }
		// }
		// }
		// // System.out.println(document.toString());
		// // System.out.println(document.toString().replaceAll(">\\s{0,10}",
		// // ">"));
		// String yyy = StringUtils.substringBetween(document.toString()
		// .replaceAll(">\\s{0,10}", ">"), "<html><head></head><body>",
		// "</body></html>");
		// System.out.println(yyy);
		//17d4b20a-f7cb-48e6-9e05-482aa88769ca
		//e9b3bf36-e148-4cad-a1de-a88a46600a1f
		
//		String a = URLEncoder.encode("{cea6b3de-e94b-4176-b806-1034ecf811e8}",
//				"utf-8");
//		String b = URLDecoder.decode("%7B716f4031-c9e3-4824-8279-60018cd5a85a%7D.xml",
//				"utf-8");

		// encodeURIComponent
	}

}
