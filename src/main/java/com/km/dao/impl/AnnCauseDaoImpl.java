/** 

* @Title: AnnCauseDaoImpl.java

* @Package com.km.dao.impl

* @Description: 案由dao实现类

* @author hulikaimen@gmail.com

* @date 2017年11月20日 下午10:13:54

* @version V1.0 

*/ 
package com.km.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.km.dao.AnnCauseDao;
import com.km.model.AnnCause;
import com.km.model.AnnInfo;

/**
 * @author PipiLu
 * @version 创建时间：2017年11月20日 下午10:13:54
 * 类说明
 */
@Repository("annCauseDao")
public class AnnCauseDaoImpl extends BaseDaoImpl<AnnCause> implements AnnCauseDao {

	@Override
	public void delete(List<AnnCause> lists) {
		for(AnnCause a : lists){
			delete(a);
		}
	}

	public void delete(AnnCause t) {
		getSession().delete(load(t.getAnnCauseID()));
	}

	/**
	 * 批量保存修改案由   
	 */
	@Override
	public void saveOrUpdateBatch(List<AnnCause> lists) {
		Session session = getSession();
		for (int i = 0; i < lists.size(); i++) {
             session.saveOrUpdate(lists.get(i));
//             if (i % 50 == 0) { // 每一百条刷新并写入数据库
             if (i % 50 == 0) { // 每一百条刷新并写入数据库
                 session.flush();
                 session.clear();
             }
         }
	}
}
