package com.unbank.robotspider.util;

import java.io.File;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;

public class XmlUtil {
	public static Map<String, String> jdbcMap = new HashMap<String, String>();

	public static Map<String, String> getJdbcMap() {
		readXML();
		return jdbcMap;
	}

	public static void readXML() {
		try {
			String path = XmlUtil.class.getClassLoader().getResource("")
					.toURI().getPath()
					+ FinalWord.JDBCCONFIGURL;
			File file = new File(path);
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(file);
			Element root = document.getRootElement();
			// Element jdbc = root.element(FinalWord.JDBC);
			// Iterator<Element> jdbcIterator = jdbc.elementIterator();
			Iterator<Element> jdbcIterator = root.elementIterator();
			while (jdbcIterator.hasNext()) {
				Element recordEless = (Element) jdbcIterator.next();
				jdbcMap.put(recordEless.getName(), recordEless.getTextTrim());
			}

		} catch (DocumentException e) {
			e.printStackTrace();

		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static List<String> getEmail() {
		List<String> emails = new ArrayList<String>();
		try {
			String path = XmlUtil.class.getClassLoader().getResource("")
					.toURI().getPath()
					+ FinalWord.EMAILURL;
			File file = new File(path);
			SAXReader saxReader = new SAXReader();
			Document document = saxReader.read(file);
			Element root = document.getRootElement();
			Iterator<Element> jdbcIterator = root.elementIterator();
			while (jdbcIterator.hasNext()) {
				Element recordEless = (Element) jdbcIterator.next();
				emails.add(recordEless.getTextTrim());
			}

		} catch (Exception e) {
			e.printStackTrace();
		}

		return emails;
	}
}
