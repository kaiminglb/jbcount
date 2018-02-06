/** 

* @Title: BaseDao.java

* @Package com.km.dao

* @Description: 基本baseDao接口

* @author hulikaimen@gmail.com

* @date 2017年10月23日 下午11:44:18

* @version V1.0 

*/ 
package com.km.dao;

import java.util.List;
import java.util.Map;



import com.km.model.DataSourceRequest;
import com.km.model.DataSourceResult;

/**
 * @author PipiLu
 * @version 创建时间：2017年10月23日 下午11:44:18
 * 类说明 dao抽象类
 */
public interface BaseDao<T> {
	
	public static Integer num = 0 ;
	/**
	 * 添加对象
	 * @param t
	 * @return
	 */
	public T add(T t);
	/**
	 * 更新对象
	 * @param t
	 */
	public void update(T t);
	/**
	 * 根据id删除对象
	 * @param id
	 */
	public void delete(int id);
	/**
	 * 根据id加载对象
	 * @param id
	 * @return
	 */
	public T load(int id);
	/**
	 * 
	* @Description: 返回不分页的列表 ,所有该类对应表的数据 
	* @return    设定文件
	 */
	public List<T> getList();
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
    public void saveOrUpdate(List<T> lists);
    
    /**
     * 
    * @Description: 增加或者更新对象   
    * @param t    设定文件
     */
    public void saveOrUpdate(T t);
    /**
     * 
    * @Description: 删除对象列表   
    * @param lists    设定文件
     */
    public void delete(List<T> lists);
    /**
     * 
    * @Description: 删除对象 
    * @param t    设定文件
     */
    public void delete(T t);
    
    
 // --------直接调用hql相关方法-----------------------
 	public List<T> list(String hql, Object[] args);

 	public List<T> list(String hql, Object arg);

 	public List<T> list(String hql);

 	
 	public List<T> list(String hql, Object[] args, Map<String, Object> alias);

 	public List<T> listByAlias(String hql, Map<String, Object> alias) ;

 	public Object queryObject(String hql, Object[] args);

 	public Object queryObject(String hql, Object arg) ;

 	public Object queryObject(String hql) ;

 	public void updateByHql(String hql, Object[] args) ;

 	public void updateByHql(String hql, Object arg);

 	public void updateByHql(String hql);

 	/**
 	 * 
 	 * @Description: 原生sql查询
 	 * @param sql
 	 * @param args
 	 * @param clz
 	 * @param hasEntity
 	 *            是否为hibernate托管实体类
 	 * @return 设定文件
 	 */
 	public <N extends Object> List<N> listBySql(String sql, Object[] args,
 			Class<?> clz, boolean hasEntity) ;

 	public <N extends Object> List<N> listBySql(String sql, Object arg,
 			Class<?> clz, boolean hasEntity) ;

 	public <N extends Object> List<N> listBySql(String sql, Class<?> clz,
 			boolean hasEntity);

 	/**
 	 * 
 	 * @Description: 原生sql查询
 	 * @param sql
 	 * @param args
 	 * @param alias
 	 * @param clz
 	 *            结果转为的实体类
 	 * @param hasEntity
 	 *            是否为hibernate托管实体类
 	 * @return 设定文件
 	 */
 	public <N extends Object> List<N> listBySql(String sql, Object[] args,
 			Map<String, Object> alias, Class<?> clz, boolean hasEntity) ;

 	public <N extends Object> List<N> listByAliasSql(String sql,
 			Map<String, Object> alias, Class<?> clz, boolean hasEntity);

 	public Object queryObject(String hql, Object[] args,
 			Map<String, Object> alias);

 	public Object queryObjectByAlias(String hql, Map<String, Object> alias);
    
}
