/** 

* @Title: AnnInfoServiceImpl.java

* @Package com.km.service.impl

* @Description: 公告信息service实现类

* @author hulikaimen@gmail.com

* @date 2017年10月24日 上午1:09:29

* @version V1.0 

*/ 
package com.km.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.km.dao.AnnInfoDao;
import com.km.model.AnnInfo;
import com.km.model.DataSourceRequest;
import com.km.model.DataSourceResult;
import com.km.service.AnnInfoService;

/**
 * @author PipiLu
 * @version 创建时间：2017年10月24日 上午1:09:29
 * 类说明  公告信息Service實現類
 */
@Transactional
@Service("annInfoService")
public class AnnInfoServiceImpl implements AnnInfoService {
	@Autowired
	AnnInfoDao annInfoDao;
	
	@Override
	public List<AnnInfo> getList() {
		return annInfoDao.getList();
	}

	public DataSourceResult getList(DataSourceRequest request) {
		return annInfoDao.getList(request);
	}

	@Override
	public void saveOrUpdate(List<AnnInfo> lists) {
		annInfoDao.saveOrUpdate(lists);
	}

	@Override
	public void saveOrUpdate(AnnInfo annInfo) {
		annInfoDao.saveOrUpdate(annInfo);
	}

	@Override
	public void delete(List<AnnInfo> lists) {
		annInfoDao.delete(lists);
	}

	@Override
	public void delete(AnnInfo annInfo) {
		annInfoDao.delete(annInfo);
	}

	//--------------------------
	@Override
	public AnnInfo queryAnnInfoByWord(String wordFilePath) {
		return annInfoDao.queryAnnInfoByWord(wordFilePath);
	}

	@Override
	public void deleteAnnInfoByWord(String wordFilePath) {
		AnnInfo annInfo = annInfoDao.queryAnnInfoByWord(wordFilePath);
		if(annInfo != null) annInfoDao.delete(annInfo.getAnnInfoID());
	}

	@Override
	public AnnInfo queryAnnInfoById(Integer id) {
		return annInfoDao.load(id);
	}

}
