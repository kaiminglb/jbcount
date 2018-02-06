/** 

* @Title: HandleWordHelperTest.java

* @Package com.km.common


* @author hulikaimen@gmail.com

* @date 2017年10月30日 下午10:28:25

* @version V1.0 

*/ 
package com.km.common;

import static org.junit.Assert.*;

import java.util.concurrent.TimeUnit;

import javax.jws.HandlerChain;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import com.km.service.AnnInfoService;

/**
 * @author PipiLu
 * @version 创建时间：2017年10月30日 下午10:28:25
 * 类说明
 */
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = {"classpath:applicationContext.xml"})
public class HandleWordHelperTest {
	@Autowired
	private HandleWordHelper handleWordHelper;
	
	@Test
	public void testBatchWord() throws InterruptedException  {
//		handleWordHelper.batchWord(1, "d:/ann/word/20170930/test.docx");
//		handleWordHelper.batchWord(2, "d:/ann/word/20170930/test1.docx");
//		handleWordHelper.batchWord(3, "d:/ann/word/20170930/test2.docx");
//		TimeUnit.SECONDS.sleep(3);
//		Thread.currentThread().sleep(30000);
	}

}
