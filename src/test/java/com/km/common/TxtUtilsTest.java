/** 

* @Title: TxtUtilsTest.java

* @Package com.km.common

* @author hulikaimen@gmail.com

* @date 2017年11月19日 下午6:27:18

* @version V1.0 

*/ 
package com.km.common;

import static org.junit.Assert.fail;

import org.junit.After;
import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
import org.junit.Test;


/**
 * @author PipiLu
 * @version 创建时间：2017年11月19日 下午6:27:18
 * 类说明
 */
public class TxtUtilsTest {
	public static String CONTENT1,CONTENT2,CONTENT3;
	private static String PATH1 = "D:/ann/txt/20170930/test.txt";
	private static String PATH2 = "D:/ann/txt/20170930/报告.txt";
   	@BeforeClass    
    public static void beforeClass() {     
//   		CONTENT1 = FileUtils.extractTxt(PATH1);
//   		CONTENT2 = FileUtils.extractTxt(PATH2);
    };     
      
    @AfterClass    
    public static void afterClass() {     
//        System.out.println("@AfterClass");     
    };  
	
	@Before
    public void setUp() throws Exception {
    }
    @After
    public void tearDown() throws Exception {
    }
	
	@Test
	public void testExtractParty() {
//		System.out.println("----------");
//		System.out.println(TxtUtils.extractParty(CONTENT1));
//		System.out.println("----------");
	}
	
	@Test
	public void testExtractCourtJudge() {
//		System.out.println("----------");
//		System.out.println(TxtUtils.extractCourtJudge(CONTENT1));
//		System.out.println("----------");
//		System.out.println(TxtUtils.extractCourtJudge(CONTENT2));
//		System.out.println("----------");
	}

}
