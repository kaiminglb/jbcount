/** 

* @Title: AnnCauseDao.java

* @Package com.km.dao


* @author hulikaimen@gmail.com

* @date 2017年11月20日 下午10:11:22

* @version V1.0 

*/ 
package com.km.dao;

import java.util.List;

import com.km.model.AnnCause;
import com.km.model.AnnInfo;

/**
 * @author PipiLu
 * @version 创建时间：2017年11月20日 下午10:11:22
 * 类说明
 */
public interface AnnCauseDao extends BaseDao<AnnCause> {
	/**
	 * 
	* @Description: 批量保存修改案由   
	* @param lists    案由list
	 */
	public void saveOrUpdateBatch(List<AnnCause> lists);
	
	
}
