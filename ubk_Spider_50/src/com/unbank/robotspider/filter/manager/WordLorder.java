package com.unbank.robotspider.filter.manager;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.HashSet;
import java.util.Set;

import org.apache.log4j.Logger;

public class WordLorder {
	private final static Logger logger = Logger.getLogger(WordLorder.class);

	public static Set<String> loadWords(InputStream input) {
		String line;
		Set<String> words = new HashSet<String>();

		try {
			BufferedReader br = new BufferedReader(new InputStreamReader(input,
					"UTF-8"), 1024);
			while ((line = br.readLine()) != null) {
				line = line.trim().toLowerCase();
				words.add(line);
			}
			br.close();
		} catch (IOException e) {
			logger.info("WARNING: cannot open user words list!", e);
		}
		return words;
	}
}
