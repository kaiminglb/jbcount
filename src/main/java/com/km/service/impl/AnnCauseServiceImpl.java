/** 

 * @Title: AnnCauseServiceImpl.java

 * @Package com.km.service.impl

 * @Description: 案由service实现类

 * @author hulikaimen@gmail.com

 * @date 2017年11月20日 下午10:29:32

 * @version V1.0 

 */
package com.km.service.impl;

import java.util.List;

import org.hibernate.NonUniqueResultException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.km.common.StringUtils;
import com.km.common.enums.ResultEnum;
import com.km.common.exception.CustomException;
import com.km.dao.AnnCauseDao;
import com.km.model.AnnCause;
import com.km.model.DataSourceRequest;
import com.km.model.DataSourceResult;
import com.km.service.AnnCauseService;

/**
 * @author PipiLu
 * @version 创建时间：2017年11月20日 下午10:29:32 类说明
 */
@Transactional
@Service("annCauseService")
public class AnnCauseServiceImpl implements AnnCauseService {
	@Autowired
	AnnCauseDao annCauseDao;

	@Override
	public List<AnnCause> getList() {
		return annCauseDao.getList();
	}

	@Override
	public DataSourceResult getList(DataSourceRequest request) {
		return annCauseDao.getList(request);
	}

	@Override
	public void saveOrUpdate(List<AnnCause> lists) {
		for (AnnCause annCause : lists) {
			annCauseDao.saveOrUpdate(annCause);
		}
	}

	@Override
	public void saveOrUpdate(AnnCause annCause) throws CustomException{
		// 案由名称纯中文，否则抛异常；若案由已存在抛异常
		if (!StringUtils.isAllChinese(annCause.getName())) {
			throw new CustomException(ResultEnum.ANNCAUSECHINESE);
		}
		if (checkExistedByAnnCauseName(annCause.getName())) {
			throw new CustomException(ResultEnum.ANNCAUSEEXISTED);
		}
		annCauseDao.saveOrUpdate(annCause);
	}

	@Override
	public void delete(List<AnnCause> lists) {
		annCauseDao.delete(lists);
	}

	@Override
	public void delete(AnnCause annCause) {
		annCauseDao.delete(annCause);
	}

	@Override
	public void saveOrUpdateBatch(List<AnnCause> lists) {
		annCauseDao.saveOrUpdateBatch(lists);
	}

	@Override
	public Boolean checkExistedByAnnCauseName(String annCauseName) {
		String hql = "FROM AnnCause where name = ?";
		// List<AnnCause> anns = annCauseDao.list(hql, annCauseName);
		AnnCause annCause;
		try {
			annCause = (AnnCause) annCauseDao.queryObject(hql,
					annCauseName);
		} catch (NonUniqueResultException e) {
			e.printStackTrace();
			return Boolean.TRUE;
		}

		if (null == annCause)
			return Boolean.FALSE;
		return Boolean.TRUE;
	}

	@Override
	public List<AnnCause> findAnnCauseByJianPin(String key) {
		String hql = "FROM AnnCause where jianPin like ?";
		
		return annCauseDao.list(hql, "%" + key +"%");
	}

}
