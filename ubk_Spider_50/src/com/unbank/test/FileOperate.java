package com.unbank.test;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import org.apache.commons.io.FileUtils;

public class FileOperate
{

	public static List<String> readLines(String path)
	{
		File file = new File(path);
		List<String> list = null;
		if (file.exists())
		{
			try
			{
				list = FileUtils.readLines(file);
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return list;
	}

	public static String readToString(String path){
		String data=null;
		File file = new File(path);
		if (file.exists())
		{
			try
			{
				data = FileUtils.readFileToString(file,"utf-8");
			} catch (IOException e)
			{
				e.printStackTrace();
			}
		}
		return data;
	}
	
	
	public static boolean saveStringToFile(String str,String path){
		File file=new File(path);
		try
		{
			FileUtils.writeStringToFile(file, str);
		} catch (IOException e)
		{
			e.printStackTrace();
			return false;
		}
		return true;
	}
	
	
	public static String[] getFilePaths(String path){
		File file=new File(path);
		String[] paths=file.list();
		return paths;
	}
}
