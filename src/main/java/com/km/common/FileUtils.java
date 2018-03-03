/** 

 * @Title: FileNameUtils.java

 * @Package com.km.common

 * @Description: File工具类

 * @author hulikaimen@gmail.com

 * @date 2017年10月7日 下午9:25:15

 * @version V1.0 

 */
package com.km.common;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author PipiLu
 * @version 创建时间：2017年10月7日 下午9:25:15 类说明 文件工具类
 */
public class FileUtils {
	/**
	 * 
	 * @Description: 返回当前时间 年月日字符串
	 * @return
	 */
	public static String getDateStr() {
		SimpleDateFormat formatter = new SimpleDateFormat("yyyyMMdd");
		return formatter.format(new Date());
	}

	/**
	 * 
	 * @Description: 判断文件后缀名是否为doc,docx
	 * @param fileFullName
	 *            文件全名
	 * @return 设定文件
	 */
	public static boolean isDoc(String fileFullName) {
		String extension = getExtensionName(fileFullName);
		return extension.equalsIgnoreCase("doc")
				|| extension.equalsIgnoreCase("docx");
	}

	/**
	 *
	 * @Description: 判断文件后缀名是否为Xlsx，不支持office2003的xls
	 * @param fileFullName
	 *            文件全名
	 * @return 设定文件
	 */
	public static boolean isXlsx(String fileFullName) {
		String extension = getExtensionName(fileFullName);
		return extension.equalsIgnoreCase("xlsx");
	}

	/**
	 * 
	 * @Description: 取文件的后缀名
	 * @param filename
	 *            文件全名
	 * @return
	 */
	public static String getExtensionName(String filename) {
		if ((filename != null) && (filename.length() > 0)) {
			int dot = filename.lastIndexOf('.');
			if ((dot > -1) && (dot < (filename.length() - 1))) {
				return filename.substring(dot + 1);
			}
		}
		// 无扩展名，直接返回
		return filename;
	}

	/**
	 * @param str
	 *            大的字符串
	 * @param petty
	 *            被包含的小字符串
	 * @param num
	 *            petty在str中倒着数第几次出现 num 必须>=1
	 * @return petty在str中倒着数第num次出现的位置（包含petty子串）;若没找到返回-1;
	 * */
	public static int getStringNum(String str, String petty, int num) {
		int pos = -1;
		if (num < 1)
			return pos;
		if (num == 1)
			return str.lastIndexOf(petty);

		int weizhi = 0;
		for (int i = 1; i < num; i++) {
			// 刚开始
			if (weizhi == 0) {
				weizhi = str.lastIndexOf(petty);
			}
			// 没找到
			if (weizhi == -1)
				return weizhi;
			// System.out.println(sB+" 在"+sA+" 中第"+i+"次出现的位置"+(weizhi+1));
			// 查找下个位置
			weizhi = str.lastIndexOf(petty, weizhi - 1);
		}
		return weizhi;
	}

	/**
	 * @Description:
	 * @Param: [docPath] 全路径
	 * @return: java.lang.String  路径片段，如 /XXxx/yyyy
	 * @Author: PiPiLu
	 * @Date: 2018/3/3
	 */
	private static String getPathCut(String docPath) {
		int begin = getStringNum(docPath, "/", 2);
		int end = docPath.lastIndexOf('.');
		if (begin == -1 || end == -1) {
			return null;
		} else {
			return docPath.substring(begin, end);
		}
	}

	/**
	 * 
	 * @Description: 将"d:/ann/word/20170930/xxx.docx"转为"/pdf/20170930/xxx.pdf"
	 * @param docPath
	 * @return 设定文件
	 */
	public static String wordToPdfFilePath(String docPath) {
		String pathCut = getPathCut(docPath);
		return pathCut == null ? null : pathCut + ".pdf";
	}

	public static String wordToTxtFilePath(String docPath) {
		String pathCut = getPathCut(docPath);
		return pathCut == null ? null : pathCut + ".txt";
	}

	public static String wordToHtmlFilePath(String docPath) {
		String pathCut = getPathCut(docPath);
		return pathCut == null ? null : pathCut + ".html";
	}
	
	/**
	 * 
	* @Description: 从fileName提取文本;
	* 				 把英文标点"," ":" 替换为中文的
	* @param fileName 目标文件
	* @return    设定文件
	 */
	public static String extractTxt(String fileName) {
		if (!"txt".equalsIgnoreCase(getExtensionName(fileName)))
			throw new RuntimeException(fileName + "不是文本文件");
		if (!(new File(fileName).exists()))
			throw new RuntimeException(fileName + "不存在");
		StringBuilder result = new StringBuilder();
		try {
			//构造一个BufferedReader类来读取ANSI编码的txt文件
			BufferedReader br = new BufferedReader(new InputStreamReader(
					new FileInputStream(fileName), "GBK"));
			String s = null;
			int lineNum = 1;
			while ((s = br.readLine()) != null) {// 使用readLine方法，一次读一行
				// 第一行不换行
				if (lineNum == 1) {
					result.append(s);
				} else {
					result.append(System.lineSeparator() + s);
				}
				lineNum++;
			}
			br.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return result.toString().replaceAll(":", "：").replaceAll(",", "，");
	}
}
