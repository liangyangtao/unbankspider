package com.unbank.duplicate.util;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

public class Shingling {
	/*
	 * 判断hashCodeA与hashCodeB的相似度是否超过给定阈值threshold
	 */
	public static boolean isSimilar(Set<String> strA,Set<String> strB, int N, double threshold){
		
		double jaccardIndex=jaccardIndex(strA, strB);
		if(jaccardIndex>threshold){
			return true;
		}else{
			return false;
		}
	}
	
	/*
	 * 获取输入字符串inputString的N-Gram字符串列表
	 */
	public static List<String> getNGramList(String inputString, int N){
		List<String> nGramList=new ArrayList<String>();
		for(int i=0;i<inputString.length()-N+1;i++){
			nGramList.add(inputString.substring(i, i+N));
			//转为hash
//			nGramList.add(HashFunctionLib.PJWHash(inputString.substring(i, i+N))+"");
		}
		return nGramList;
	}
	
	/*
	 * 计算sentence字符串中N-gram的词项数及各词项的权重
	 */
	public static Map<String, Integer> getTokenAndWeight(String sentence, int N){
		List<String> nGramList=getNGramList(sentence, N);
		Map<String, Integer> resultMap=new HashMap<String, Integer>();
		for(int i=0;i<nGramList.size();i++){
			String key=nGramList.get(i);
			if(!resultMap.containsKey(key)){
				resultMap.put(key, 1);
			}
			else{
				resultMap.put(key, resultMap.get(key)+1);
			}
		}
		return resultMap;
	}
	/*
	 * A、B:待计算jaccard系数的两个Integer集合
	 * K:用于计算jaccard系数的哈希值最小的K个元素
	 * 如果size(A)<K或size(B)<K，则用min(size(A), size(B))作为K的实际值
	 */
	public static double jaccardIndex(Set<String> minHashA, Set<String> minHashB){
		/*
		 * 计算Jaccard系数
		 */
		Set<String> mergedSet=new HashSet<String>();
		mergedSet.addAll(minHashA);
		mergedSet.addAll(minHashB);
		double jaccardIndex=(double)(minHashA.size()+minHashB.size()-mergedSet.size())/(double)mergedSet.size();
		return jaccardIndex;
	}
}
