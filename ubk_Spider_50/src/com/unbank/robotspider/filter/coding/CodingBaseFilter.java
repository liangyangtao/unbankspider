package com.unbank.robotspider.filter.coding;

import info.monitorenter.cpdetector.io.ASCIIDetector;
import info.monitorenter.cpdetector.io.CodepageDetectorProxy;
import info.monitorenter.cpdetector.io.JChardetFacade;
import info.monitorenter.cpdetector.io.UnicodeDetector;

import java.io.InputStream;
import java.net.MalformedURLException;
import java.net.URL;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public class CodingBaseFilter implements CodingFilter {
	private static Log logger = LogFactory.getLog(CodingBaseFilter.class);

	public boolean checkMessyCode(String source) {
		if (source.contains("�") || source.contains("熶")
				|| source.contains("銆") || source.contains("為")) {
			return true;
		}
		return false;

	}

	@Override
	public String getCharset(String sourceUrl) {
		URL url = null;
		try {
			url = new URL(sourceUrl);
		} catch (MalformedURLException e) {
			e.printStackTrace();
		}
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		java.nio.charset.Charset charset = null;
		try {
			charset = detector.detectCodepage(url);
		} catch (Exception ex) {
			logger.info("", ex);
		}
		if (charset != null) {
			return charset.name();
		} else {
			return null;
		}
	}

	public String getCharset(InputStream inputStream) {
		CodepageDetectorProxy detector = CodepageDetectorProxy.getInstance();
		detector.add(JChardetFacade.getInstance());
		detector.add(ASCIIDetector.getInstance());
		detector.add(UnicodeDetector.getInstance());
		java.nio.charset.Charset charset = null;
		try {
			charset = detector.detectCodepage(inputStream,
					inputStream.available() / 2);
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		if (charset != null) {
			return charset.name();
		} else {
			return null;
		}
	}
}
