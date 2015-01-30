package com.unbank.robotspider.tools;

import java.util.Map;


public class DoSecore
{

	public static int secore(String str){
		Map<String,Integer> map=GetCharsNum.getNum(str);
		int secore=0;
		
		int chCharacter=map.get("chCharacter");//中文字符个数
		int chPunctuationCharacter=map.get("chPunctuationCharacter");//中文标点个数
		int otherCharacter=map.get("otherCharacter");//其他字符个数
		
		secore=chCharacter*PropertyValules.CNCHARSCORE
				+chPunctuationCharacter*PropertyValules.CNPNCHARSCORE
				+otherCharacter/5;
		return secore;
	}
}
