package com.unbank.robotspider.tools;

public class AngleConvertion {
	static final char FIGURE_START = 65296;// 数字开始
	static final char FIGURE_END = 65305;// 数字END
	static final char CHAR_START = 65345;// 字母开始
	static final char CHAR_END = 65370;// 字母END
	static final int CONVERT_STEP = 65248; // 全角半角转换间隔
	static final char SBC_SPACE = 12288; // 全角空格 12288
	static final char DBC_SPACE = ' '; // 半角空格

	public String angleConvertion(String str) {

		if (str == null) {
			return str;
		}
		StringBuilder sbud = new StringBuilder(str.length());
		char[] convertionChar = str.toCharArray();
		for (int i = 0; i < str.length(); i++) {
			if (convertionChar[i] == 65282) {
				sbud.append((char) (8220));
				continue;
			}
			if (convertionChar[i] == 65288) {
				sbud.append((char) (40));
				continue;
			}
			if (convertionChar[i] == 65289) {
				sbud.append((char) (41));
				continue;
			}
			if (convertionChar[i] == 65285) {
				sbud.append((char) (37));
				continue;
			}
			if (convertionChar[i] == 65283) {
				sbud.append((char) (35));
				continue;
			}
			if (convertionChar[i] == 65312) {
				sbud.append((char) (64));
				continue;
			}
			if (convertionChar[i] == 65294) {
				sbud.append((char) (46));
				continue;
			}

			if (convertionChar[i] >= FIGURE_START
					&& convertionChar[i] <= FIGURE_END
					|| convertionChar[i] >= CHAR_START
					&& convertionChar[i] <= CHAR_END) { // 如果位于全角！到全角～区间内
				sbud.append((char) (convertionChar[i] - CONVERT_STEP));
			} else if (convertionChar[i] == SBC_SPACE) { // 如果是全角空格
				sbud.append(DBC_SPACE);
			} else { // 不处理全角空格，全角！到全角～区间外的字符
				sbud.append(convertionChar[i]);
			}
		}
		return sbud.toString();
	}

}
