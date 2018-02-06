/** 

* @Title: AnnInfoService.java

* @Package com.km.service

* @Description: 公告信息service接口

* @author hulikaimen@gmail.com

* @date 2017年10月8日 上午10:00:39

* @version V1.0 

*/ 
package com.km.service;

import java.util.List;

import com.km.model.AnnInfo;
import com.km.model.DataSourceRequest;
import com.km.model.DataSourceResult;

/**
 * @author PipiLu
 * @version 创建时间：2017年10月8日 上午10:00:39
 * 类说明
 */
public interface AnnInfoService {
	/**
	 * 
	* @Description: 返回不分页的列表 ,所有该类对应表的数据 
	* @return    设定文件
	 */
	public List<AnnInfo> getList();
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
    public void saveOrUpdate(List<AnnInfo> lists);
    
    /**
     * 
    * @Description: 增加或者更新对象   
    * @param t    设定文件
     */
    public void saveOrUpdate(AnnInfo annInfo);
    /**
     * 
    * @Description: 删除对象列表   
    * @param lists    设定文件
     */
    public void delete(List<AnnInfo> lists);
    /**
     * 
    * @Description: 删除对象 
    * @param t    设定文件
     */
    public void delete(AnnInfo annInfo);
    
    /**
     * 
    * @Description: 根据wordName查询   
    * @param wordFilePath 上传word存放路径
    * @return    AnnInfo
     */
    public AnnInfo  queryAnnInfoByWord(String wordFilePath);
    
    /**
	 * 
	* @Description: 根据 word存放路径,删除公告信息  
	* @param wordFilePath    wordFilePath word存放路径 比如 {yyyyMMdd}/xxxxx.docx
	 */
	public void deleteAnnInfoByWord(String wordFilePath);
	
	/**
	 * 
	* @Description: 根据id查询AnnInfo公告信息
	* @param id
	* @return    公告信息
	 */
	public AnnInfo queryAnnInfoById(Integer id);
}
