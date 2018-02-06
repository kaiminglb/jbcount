/** 

 * @Title: StringUtilsTest.java

 * @Package com.km.common

 * @Description: TODO(用一句话描述该文件做什么)

 * @author hulikaimen@gmail.com

 * @date 2018年2月3日 下午8:35:57

 * @version V1.0 

 */
package com.km.common;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.*;

import org.junit.Test;

/**
 * @author PipiLu
 * @version 创建时间：2018年2月3日 下午8:35:57 类说明
 */
public class StringUtilsTest {

	@Test
	public void testIsAllChinese() {
		String str1 = "java判断是否为汉字";
		String str2 = "全为汉字";

		Boolean result1 = StringUtils.isAllChinese(str1);// false
		Boolean result2 = StringUtils.isAllChinese(str2);// true

		assertThat(result1,is(Boolean.FALSE));
		assertThat(result2,is(Boolean.TRUE));
	}

}
