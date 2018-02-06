/** 

* @Title: FileUtilsTest.java

* @Package com.km.common

* @Description: FileUtils测试类

* @author hulikaimen@gmail.com

* @date 2017年10月7日 下午11:30:58

* @version V1.0 

*/ 
package com.km.common;

import static org.junit.Assert.*;

import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;


/**
 * @author PipiLu
 * @version 创建时间：2017年10月7日 下午11:30:58
 * 类说明
 */
public class FileUtilsTest {
	private final static Logger logger = LoggerFactory.getLogger(FileUtilsTest.class);
	
	@Test
	public void testGetDateStr() {
		System.out.println(FileUtils.getDateStr());
	}

	@Test
	public void testIsDoc() {
		assertTrue(FileUtils.isDoc("1.doc") && FileUtils.isDoc("2.docx") && FileUtils.isDoc("1.Doc") && FileUtils.isDoc("1.dOcX"));
		assertFalse(FileUtils.isDoc("1doc"));
	}
	
	@Test
	public void testGetPathCut(){
		//D:/ann/word/报告.docx
//		String docPath = "d:/ann/word/20170930/xxx.docx";
//		String docPath1 = "20170930/xxx.docx";
//		String docPath2 = "d:/ann/word/20170930/xxx";
//		
//		assertTrue(("/20170930/xxx").equals(FileUtils.getPathCut(docPath)));
//		assertFalse(("/20170930/xxx.").equals(FileUtils.getPathCut(docPath)));
//		
//		assertNull(FileUtils.getPathCut(docPath1));
//		assertNull(FileUtils.getPathCut(docPath2));
	}
	
	@Test
	public void testExtractTxt(){
//		String s = FileUtils.extractTxt("D:/ann/txt/20170930/报告.txt");
//		System.out.println(s);
	}

}
