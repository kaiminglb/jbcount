/** 

* @Title: AnnInfoDao.java

* @Package com.km.dao.impl

* @Description: 公告信息dao

* @author hulikaimen@gmail.com

* @date 2017年9月27日 上午2:13:21

* @version V1.0 

*/ 
package com.km.dao;

import java.util.List;

import com.km.model.AnnInfo;

/**
 * @author PipiLu
 * @version 创建时间：2017年9月27日 上午2:13:21
 * 类说明
 */
public interface AnnInfoDao extends BaseDao<AnnInfo>{
	/**
	 * 
	* @Description: 根据 word存放路径,查询公告信息   
	* @param wordFilePath word存放路径 比如 {yyyyMMdd}/xxxxx.docx
	* @return    AnnInfo公告信息
	 */
	public AnnInfo queryAnnInfoByWord(String wordFilePath);
	
}
