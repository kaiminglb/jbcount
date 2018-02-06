/** 

 * @Title: PinyinUtils.java

 * @Package com.km.common

 * @Description: 汉字、拼音转换处理工具类

 * @author hulikaimen@gmail.com

 * @date 2017年11月17日 上午1:32:27

 * @version V1.0 

 */
package com.km.common;

import net.sourceforge.pinyin4j.PinyinHelper;
import net.sourceforge.pinyin4j.format.HanyuPinyinCaseType;
import net.sourceforge.pinyin4j.format.HanyuPinyinOutputFormat;
import net.sourceforge.pinyin4j.format.HanyuPinyinToneType;
import net.sourceforge.pinyin4j.format.HanyuPinyinVCharType;
import net.sourceforge.pinyin4j.format.exception.BadHanyuPinyinOutputFormatCombination;

/**
 * @author PipiLu
 * @version 创建时间：2017年11月17日 上午1:32:27 类说明 汉字转拼音工具类
 */
public class PinyinUtils {
	/**
	 * 获得汉语拼音首字母
	 *
	 * @param chines
	 *            汉字
	 * @return
	 
	public static String getAlpha(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			if (nameChar[i] > 128) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}
	*/
	
	
	/**
	 * 将字符串中的中文转化为全部拼音,英文字符不变
	 *
	 * @param inputString
	 *            汉字
	 * @return
	 */
	public static String getPingYin(String inputString) {
		HanyuPinyinOutputFormat format = new HanyuPinyinOutputFormat();
		format.setCaseType(HanyuPinyinCaseType.LOWERCASE);
		format.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		format.setVCharType(HanyuPinyinVCharType.WITH_V);
		String output = "";
		if (inputString != null && inputString.length() > 0
				&& !"null".equals(inputString)) {
			char[] input = inputString.trim().toCharArray();
			try {
				for (int i = 0; i < input.length; i++) {
					if (java.lang.Character.toString(input[i]).matches(
							"[\\u4E00-\\u9FA5]+")) {
						String[] temp = PinyinHelper.toHanyuPinyinStringArray(
								input[i], format);
						output += temp[0];
					} else
						output += java.lang.Character.toString(input[i]);
				}
			} catch (BadHanyuPinyinOutputFormatCombination e) {
				e.printStackTrace();
			}
		} else {
			return "*";
		}
		return output;
	}

	/**
	 * 汉字转换位汉语拼音首字母，中文标点、英文字符不变
	 *
	 * @param chines
	 *            汉字字符串
	 * @return 拼音
	 */
	public static String converterToFirstSpell(String chines) {
		String pinyinName = "";
		char[] nameChar = chines.toCharArray();
		HanyuPinyinOutputFormat defaultFormat = new HanyuPinyinOutputFormat();
		defaultFormat.setCaseType(HanyuPinyinCaseType.UPPERCASE);
		defaultFormat.setToneType(HanyuPinyinToneType.WITHOUT_TONE);
		for (int i = 0; i < nameChar.length; i++) {
			// if (nameChar[i] > 128) {
			//至少匹配一个汉字
			if (java.lang.Character.toString(nameChar[i]).matches(
					"[\\u4E00-\\u9FA5]+")) {
				try {
					pinyinName += PinyinHelper.toHanyuPinyinStringArray(
							nameChar[i], defaultFormat)[0].charAt(0);
				} catch (BadHanyuPinyinOutputFormatCombination e) {
					e.printStackTrace();
				}
			} else {
				pinyinName += nameChar[i];
			}
		}
		return pinyinName;
	}
}
