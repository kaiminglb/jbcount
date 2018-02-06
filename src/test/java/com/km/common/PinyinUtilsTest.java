/** 

* @Title: PinyinUtilsTest.java

* @Package com.km.common


* @author hulikaimen@gmail.com

* @date 2017年11月17日 上午1:35:19

* @version V1.0 

*/ 
package com.km.common;

import static org.junit.Assert.*;  
import static org.hamcrest.CoreMatchers.*;  

import org.junit.Test;

/**
 * @author PipiLu
 * @version 创建时间：2017年11月17日 上午1:35:19
 * 类说明
 */
public class PinyinUtilsTest {

	@Test
	public void testGetPingYin() {
		String pyqc = PinyinUtils.getPingYin("新年好！Hello，新年大家都过得开心吧？");
		assertThat(pyqc, is("xinnianhao！Hello，xinniandajiadouguodekaixinba？"));
	}
	
	@Test
	public void testConverterToFirstSpell() {
		String pyjc = PinyinUtils.converterToFirstSpell("新年好！");
		assertThat(pyjc,is("XNH！"));
	}

}
