/** 

 * @Title: ResultUtils.java

 * @Package com.km.common

 * @Description: TODO(用一句话描述该文件做什么)

 * @author hulikaimen@gmail.com

 * @date 2018年1月21日 上午9:56:35

 * @version V1.0 

 */
package com.km.common;

import com.km.model.Result;

/**
 * @author PipiLu
 * @version 创建时间：2018年1月21日 上午9:56:35 类说明
 * 返回带消息和数据的结果对象
 */
public class ResultUtils {
	
	/**
	 * 
	* @Description: 成功消息对象 ，默认 状态码0文本成功
	* @param  包含数据的对象
	* @return    设定文件
	 */
	@SuppressWarnings({ "rawtypes", "unchecked" })
	public static Result success(Object object) {
		Result result = new Result();
		result.setCode(0);
		result.setMsg("成功");
		result.setData(object);
		return result;
	}

	@SuppressWarnings("rawtypes")
	public static Result success() {
		return success(null);
	}
	
	/**
	 * 
	* @Description: 定义错误消息对象  
	* @param code	错误码
	* @param msg	错误消息文本
	* @return    设定文件
	 */
	@SuppressWarnings("rawtypes")
	public static Result error(Integer code, String msg) {
		Result result = new Result();
		result.setCode(code);
		result.setMsg(msg);
//		result.setData(object);返回对象为空
		return result;
	}

}
