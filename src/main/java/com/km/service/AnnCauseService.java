/** 

* @Title: AnnCauseService.java

* @Package com.km.service

* @Description: 案由服务接口

* @author hulikaimen@gmail.com

* @date 2017年11月20日 下午10:22:26

* @version V1.0 

*/ 
package com.km.service;

import java.util.List;

import com.km.model.AnnCause;
import com.km.model.DataSourceRequest;
import com.km.model.DataSourceResult;

/**
 * @author PipiLu
 * @version 创建时间：2017年11月20日 下午10:22:26
 * 类说明
 */
public interface AnnCauseService {
	/**
	 * 
	* @Description: 返回不分页的列表 ,所有该类对应表的数据 
	* @return    设定文件
	 */
	public List<AnnCause> getList();
    /**
     * 
    * @Description: 返回请求条件【DataSourceRequest，grid封装】的分页数据  
    * @param request
    * @return    【DataSourceResult】封装查询结果类，grid所需数据
     */
    public DataSourceResult getList(DataSourceRequest request);
    
    /**
     * 
    * @Description: 增加或者更新对象列表,是否含有组件。（自赋值的主键除外）  
    * @param lists    设定文件
     */
    public void saveOrUpdate(List<AnnCause> lists);
    
    /**
     * 
    * @Description: 增加或者更新对象   
    * @param t    设定文件
     */
    public void saveOrUpdate(AnnCause annCause);
    /**
     * 
    * @Description: 删除对象列表   
    * @param lists    设定文件
     */
    public void delete(List<AnnCause> lists);
    /**
     * 
    * @Description: 删除对象 
    * @param t    设定文件
     */
    public void delete(AnnCause annCause);
    
    /**
     * 
    * @Description: 批量增、改案由   
    * @param list    设定文件
     */
    public void  saveOrUpdateBatch(List<AnnCause> list);
    
    /**
     * 
    * @Description: 检查案由名称是否存在  
    * @param annCauseName 案由名称
    * @return    案由是否已存在。存在返回true,否则返回false
     */
    public Boolean checkExistedByAnnCauseName(String annCauseName );
    
    /**
     * 
    * @Description: 通过拼音缩写查找案由
    * @param key	拼音缩写
    * @return    设定文件
     */
    public List<AnnCause> findAnnCauseByJianPin(String key);
}
