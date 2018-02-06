/** 

 * @Title: AnnInfoDaoImpl.java

 * @Package com.km.dao.impl

 * @Description: 公告信息dao实现类

 * @author hulikaimen@gmail.com

 * @date 2017年9月27日 上午2:17:51

 * @version V1.0 

 */
package com.km.dao.impl;

import java.util.List;

import org.hibernate.Session;
import org.springframework.stereotype.Repository;

import com.km.dao.AnnInfoDao;
import com.km.model.AnnInfo;

/**
 * @author PipiLu
 * @version 创建时间：2017年9月27日 上午2:17:51 类说明
 */
@Repository("annInfoDao")
public class AnnInfoDaoImpl extends BaseDaoImpl<AnnInfo> implements AnnInfoDao {
	
	@Override
	public void delete(List<AnnInfo> lists) {
		Session session = getSession();
		for(AnnInfo annInfo : lists){
			session.delete(load(annInfo.getAnnInfoID()));
		}
	}

	@Override
	public void delete(AnnInfo annInfo) {
		getSession().delete(load(annInfo.getAnnInfoID()));
	}

	
	@Override
	/**
	 * 根据 word存放路径,查询公告信息
	 */
	public AnnInfo queryAnnInfoByWord(String wordFilePath) {
		String hql = "FROM AnnInfo a WHERE a.wordFilePath = ?";
		return (AnnInfo)this.queryObject(hql, wordFilePath);
	}

	

}
