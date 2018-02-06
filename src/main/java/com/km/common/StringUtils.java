/** 

* @Title: StringUtils.java

* @Package com.km.common

* @Description: TODO(用一句话描述该文件做什么)

* @author hulikaimen@gmail.com

* @date 2018年2月3日 下午8:30:25

* @version V1.0 

*/ 
package com.km.common;
/**
 * @author PipiLu
 * @version 创建时间：2018年2月3日 下午8:30:25
 * 类说明
 */
public class StringUtils {
	
	/**
	 * 
	* @Description: 判断字符串是否全为汉字。  
	* @param str
	* @return    全是 返回true,否则 false
	 */
	public static Boolean isAllChinese(String str){
		String reg = "[\\u4e00-\\u9fa5]+";
		return str.matches(reg);
	}
}
