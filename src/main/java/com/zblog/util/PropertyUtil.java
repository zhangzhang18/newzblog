package com.zblog.util;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLDecoder;
import java.util.Properties;

public class PropertyUtil {
	
	private static Properties Prop;

	private static Properties proload() {
		Prop = new Properties();
		try {
			FileInputStream fis = new FileInputStream(getRealPath("/WEB-INF/classes/pdf2swf.properties"));
			Prop.load(fis);
			fis.close();
		} catch (FileNotFoundException ex) {
			System.out.println("没有找到配置文件");
			ex.printStackTrace();
		} catch (IOException ex) {
			System.out.println("IO错误");
			ex.printStackTrace();
		} 
		return Prop;
	}
	
	public static String getRealPath(String relativePosition){
		String str = PropertyUtil.class.getResource("/").getPath();
		try {
			/* 转码。比如路径中有“%20”会被替换成空格； */
			str = URLDecoder.decode(str, "UTF-8");
		} catch (UnsupportedEncodingException e) {
			e.printStackTrace();
		}
		String path = str.substring(0,str.lastIndexOf("WEB-INF/classes")-1) + relativePosition;
		return path;
	}

	public static String getProper(String key) {
		if (Prop == null) {
			proload();
		}
		return Prop.getProperty(key).trim();
	}
	
	public static void main(String[] args) {
		System.out.println(getProper("PDF2SWF_PATH"));
		System.out.println(getProper("PDF2SWF_LANGDIR"));
	}

}
