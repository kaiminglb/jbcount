/** 

 * @Title: AnnCauseServiceImplTest.java

 * @Package com.km.service.impl

 * @Description: TODO(用一句话描述该文件做什么)

 * @author hulikaimen@gmail.com

 * @date 2018年2月4日 上午12:00:09

 * @version V1.0 

 */
package com.km.service.impl;

import static org.hamcrest.CoreMatchers.is;
import static org.junit.Assert.assertThat;

import org.hibernate.NonUniqueResultException;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.annotation.Rollback;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import com.km.model.AnnCause;
import com.km.service.AnnCauseService;

/**
 * @author PipiLu
 * @version 创建时间：2018年2月4日 上午12:00:09 类说明
 */
@RunWith(SpringJUnit4ClassRunner.class)
// 使用junit4进行测试
@ContextConfiguration({ "classpath*:applicationContext.xml" })
// 加载配置文件
public class AnnCauseServiceImplTest {
	@Autowired
	AnnCauseService annCauseService;
	
	/*@Test(expected= NonUniqueResultException.class)//期待异常
//	@Transactional// 标明此方法需使用事务
//	@Rollback(true)// 标明使用完此方法后事务不回滚,true时为回滚
	public void testCheckExistedByAnnCauseName1() {
		*//*AnnCause annCause = new AnnCause();
		annCause.setName("信用卡纠纷测试");
		annCauseService.saveOrUpdate(annCause);*//*
		Boolean result1 = annCauseService.checkExistedByAnnCauseName("海域使用权纠纷");
	}*/
	
	@Test
	public void testCheckExistedByAnnCauseName2() {
		Boolean result2 = annCauseService.checkExistedByAnnCauseName("信用卡纠纷二");
		assertThat(result2, is(Boolean.FALSE));
	}

}
