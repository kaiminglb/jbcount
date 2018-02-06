/** 

 * @Title: BaseDaoImpl.java

 * @Package com.km.dao.impl

 * @Description: BaseDao实现类，抽象类需子类实现2抽象方法

 * @author hulikaimen@gmail.com

 * @date 2017年10月23日 下午11:45:44

 * @version V1.0 

 */
package com.km.dao.impl;

import java.lang.reflect.ParameterizedType;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Set;

import org.hibernate.Query;
import org.hibernate.SQLQuery;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.transform.Transformers;
import org.springframework.beans.factory.annotation.Autowired;

import com.km.dao.BaseDao;
import com.km.model.DataSourceRequest;
import com.km.model.DataSourceResult;

/**
 * @author PipiLu
 * @version 创建时间：2017年10月23日 下午11:45:44 类说明 基础Dao实现类（没实现返回分页的对象列表）
 */
public abstract class BaseDaoImpl<T> implements BaseDao<T> {

	@Autowired
	private SessionFactory sessionFactory;
	/**
	 * 创建一个Class的对象来获取泛型的class
	 */
	private Class<?> clz;

	/**
	 * 
	 * @Description: 获取泛型的Class对象
	 * @return 设定文件
	 */
	public Class<?> getClz() {
		if (clz == null) {
			// 获取泛型的Class对象
			clz = ((Class<?>) (((ParameterizedType) (this.getClass()
					.getGenericSuperclass())).getActualTypeArguments()[0]));
		}
		return clz;
	}

	protected Session getSession() {
		return sessionFactory.getCurrentSession();
	}

	@Override
	public T add(T t) {
		getSession().save(t);
		return t;
	}

	@Override
	public void update(T t) {
		getSession().update(t);
	}

	@Override
	public void delete(int id) {
		getSession().delete(this.load(id));
	}

	@SuppressWarnings("unchecked")
	@Override
	public T load(int id) {
		return (T) getSession().load(getClz(), id);
	}

	@SuppressWarnings("unchecked")
	@Override
	public List<T> getList() {
		return getSession().createCriteria(getClz()).list();
	}

	@Override
	public DataSourceResult getList(DataSourceRequest request) {
		return request.toDataSourceResult(getSession(), getClz());
	}

	@Override
	public void saveOrUpdate(List<T> lists) {
		Session session = getSession();
		for (T t : lists) {
			session.saveOrUpdate(t);
		}

	}

	@Override
	public void saveOrUpdate(T t) {
		getSession().saveOrUpdate(t);
	}

	/*
	 * 具體實現類實現
	 */
	@Override
	public abstract  void delete(List<T> lists);

	/*
	 * 具體實現類實現
	 */
	@Override
	public abstract  void delete(T t);
	
	

	// --------直接调用hql相关方法-----------------------
	public List<T> list(String hql, Object[] args) {
		return this.list(hql, args, null);
	}

	public List<T> list(String hql, Object arg) {
		return this.list(hql, new Object[] { arg });
	}

	public List<T> list(String hql) {
		return this.list(hql, null);
	}

	@SuppressWarnings("rawtypes")
	private void setAliasParameter(Query query, Map<String, Object> alias) {
		if (alias != null) {
			Set<String> keys = alias.keySet();
			for (String key : keys) {
				Object val = alias.get(key);
				if (val instanceof Collection) {
					// 命名查询条件是集合
					query.setParameterList(key, (Collection) val);
				} else {
					// 命名查询条件是单值
					query.setParameter(key, val);
				}
			}
		}
	}

	private void setParameter(Query query, Object[] args) {
		if (args != null && args.length > 0) {
			int index = 0;
			for (Object arg : args) {
				query.setParameter(index++, arg);
			}
		}
	}

	@SuppressWarnings("unchecked")
	public List<T> list(String hql, Object[] args, Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.list();
	}

	public List<T> listByAlias(String hql, Map<String, Object> alias) {
		return this.list(hql, null, alias);
	}

	public Object queryObject(String hql, Object[] args) {
		return this.queryObject(hql, args, null);
	}

	public Object queryObject(String hql, Object arg) {
		return this.queryObject(hql, new Object[] { arg });
	}

	public Object queryObject(String hql) {
		return this.queryObject(hql, null);
	}

	public void updateByHql(String hql, Object[] args) {
		Query query = getSession().createQuery(hql);
		setParameter(query, args);
		query.executeUpdate();
	}

	public void updateByHql(String hql, Object arg) {
		this.updateByHql(hql, new Object[] { arg });
	}

	public void updateByHql(String hql) {
		this.updateByHql(hql, null);
	}

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
			Class<?> clz, boolean hasEntity) {
		return this.listBySql(sql, args, null, clz, hasEntity);
	}

	public <N extends Object> List<N> listBySql(String sql, Object arg,
			Class<?> clz, boolean hasEntity) {
		return this.listBySql(sql, new Object[] { arg }, clz, hasEntity);
	}

	public <N extends Object> List<N> listBySql(String sql, Class<?> clz,
			boolean hasEntity) {
		return this.listBySql(sql, null, clz, hasEntity);
	}

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
	@SuppressWarnings("unchecked")
	public <N extends Object> List<N> listBySql(String sql, Object[] args,
			Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
		SQLQuery sq = getSession().createSQLQuery(sql);
		setAliasParameter(sq, alias);
		setParameter(sq, args);
		if (hasEntity) {
			sq.addEntity(clz);
		} else
			sq.setResultTransformer(Transformers.aliasToBean(clz));
		return sq.list();
	}

	public <N extends Object> List<N> listByAliasSql(String sql,
			Map<String, Object> alias, Class<?> clz, boolean hasEntity) {
		return this.listBySql(sql, null, alias, clz, hasEntity);
	}

	public Object queryObject(String hql, Object[] args,
			Map<String, Object> alias) {
		Query query = getSession().createQuery(hql);
		setAliasParameter(query, alias);
		setParameter(query, args);
		return query.uniqueResult();
	}

	public Object queryObjectByAlias(String hql, Map<String, Object> alias) {
		return this.queryObject(hql, null, alias);
	}

}
