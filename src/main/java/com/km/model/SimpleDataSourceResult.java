/** 

* @Title: SimpleDataSourceResult.java

* @Package com.km.model

* @Description: TODO(用一句话描述该文件做什么)

* @author hulikaimen@gmail.com

* @date 2018年2月6日 上午12:43:45

* @version V1.0 

*/ 
package com.km.model;

import java.util.ArrayList;
import java.util.List;

/**
 * @author PipiLu
 * @version 创建时间：2018年2月6日 上午12:43:45
 * 类说明  List为结果的包装对象
 */
public class SimpleDataSourceResult {
	//List<?>一般只能用于读取，不能add，除非是null
	private List<?> data;

	public List<?> getData() {
		return data;
	}

	public void setData(List<?> data) {
		this.data = data;
	}

	public SimpleDataSourceResult(Object o) {
		super();
		if(o instanceof List){
			this.data = (List<?>)o;
		}else{
			List<Object> list = new ArrayList<Object>(1);
			list.add(o);
			this.data = list;
		}
		
	}
	
	
	
	
}
