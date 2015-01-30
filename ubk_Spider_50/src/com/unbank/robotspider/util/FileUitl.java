package com.unbank.robotspider.util;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.FileWriter;
import java.io.InputStreamReader;
import java.io.OutputStreamWriter;
import java.net.URISyntaxException;
import java.util.List;
import java.util.Map.Entry;

public class FileUitl {
	static String path = "";
	static {
		try {
			path = FileUitl.class.getClassLoader().getResource("").toURI()
					.getPath();
			path = path.substring(1, path.length()) + FinalWord.RESAULT;
		} catch (URISyntaxException e) {
			e.printStackTrace();
		}
	}

	public static void writeList(List<Entry<String, Integer>> allList,
			String path1, String fileName) {
		if (path1 == null || path1.isEmpty()) {
			path1 = path;
		}
		File file = new File(path1);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {

			FileWriter fw = new FileWriter(path + fileName, true);

			fw.append(allList.get(0).toString() + "\n\r");

			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public static void writeString(String words, String path1, String fileName) {
		if (path1 == null || path1.isEmpty()) {
			path1 = path;
		}
		File file = new File(path1);
		if (!file.exists()) {
			file.mkdirs();
		}
		try {

			FileWriter fw = new FileWriter(path + fileName, true);
			fw.append(words + "\n\r");
			fw.flush();
			fw.close();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

	public void writ() {
		InputStreamReader read = null;

		BufferedReader br = null;

		OutputStreamWriter writer = null;

		BufferedWriter bw = null;

		try {

			File inputPath = new File(path);
			read = new InputStreamReader(new FileInputStream(inputPath),
					"UTF-8");

			br = new BufferedReader(read);

			File outputPath = new File(path);

			writer = new OutputStreamWriter(new FileOutputStream(outputPath),
					"UTF-8");

			bw = new BufferedWriter(writer);

		} catch (Exception e) {

			e.printStackTrace();

		} finally {

			try {

				br.close();

				bw.close();

				read.close();

				writer.close();

			} catch (Exception e) {

				e.printStackTrace();

			}

		}
	}

	public static void writeString(String text, String string) {
		String path = "d://textModel/";
		File file = new File(path);
		if (!file.exists()) {
			file.mkdirs();
		}
		file = new File(path + string + ".html");
		try {

			OutputStreamWriter write = new OutputStreamWriter(
					new FileOutputStream(file), "gbk");
			BufferedWriter writer = new BufferedWriter(write);
			// PrintWriter writer = new PrintWriter(new BufferedWriter(new
			// FileWriter(filePathAndName)));
			// PrintWriter writer = new PrintWriter(new
			// FileWriter(filePathAndName));
			writer.write(text);
			writer.close();
			write.close();
		} catch (Exception e) {
			e.printStackTrace();

		}

	}

}
