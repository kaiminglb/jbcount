/** 

* @Title: TxtUtils.java

* @Package com.km.common

* @Description: 纯文本处理工具类

* @author hulikaimen@gmail.com

* @date 2017年11月19日 下午6:19:14

* @version V1.0 

*/ 
package com.km.common;
/**
 * @author PipiLu
 * @version 创建时间：2017年11月19日 下午6:19:14
 * 类说明  普通文本提取信息工具类
 */
public class TxtUtils {
	private static String NORESULT = "没找到";
	/**
	 * 
	* @Description: 提取 当事人  
	* @return    设定文件
	 */
	public static String  extractParty(String text){
		int startPos,endPos;
		endPos = text.indexOf("：");
		startPos = text.lastIndexOf("\n", endPos);
		// 去掉换行符
		return text.substring(startPos + 1, endPos);
	}
	
	/**
	 * 承办法官姓名 courtJudge，与author有对应关系
	 * 		1、承办人：江朝丽
	 * 		2 金融庭联系人：蒋思斯（，或者空格）
	 * 		  本院联系人：贺文静
	 * 		3 没有
	 */
	public static String  extractCourtJudge(String text){
		int startPos,endPos;
		//1、承办人：江朝丽
		startPos = text.indexOf("承办人：");
		//2 联系人：蒋思斯
		if(startPos == -1) startPos = text.indexOf("联系人：");
		//没找到
		if(startPos == -1) return NORESULT;
		
		
		endPos = getEndPos(startPos,text);
		//没找到
		if(startPos == endPos) return NORESULT;
			
		return text.substring(startPos + 4, endPos);
	}
	
	/**
	 * 
	* @Description: 获得承办法官的结束位置   
	* @param start
	* @param text 文本
	* @return    设定文件
	 */
	private static int getEndPos(int start , String text){
		int spaceEndPos = text.indexOf(" ", start);
		int commaEndPos = text.indexOf("，",start);
		if(spaceEndPos == -1 && commaEndPos == -1)
			return start;
		if(spaceEndPos * commaEndPos <= 0)
			return Math.max(spaceEndPos,commaEndPos);
		return Math.min(spaceEndPos,commaEndPos);
	}
	
	/**
	 * 
	* @Description: 承办法官所在部门   
	* @param text
	* @return    设定文件
	 */
	public static String  extractAuthor(String text){
		int startPos,endPos;
		endPos = text.indexOf("联系人：");
		startPos = text.lastIndexOf("\n", endPos);
		if(endPos > 0 && startPos > 0)
			return text.substring(startPos + 1 , endPos);
		return NORESULT;
	}
}
