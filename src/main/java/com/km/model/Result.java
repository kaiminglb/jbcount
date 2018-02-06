/** 

 * @Title: Result.java

 * @Package com.km.model

 * @Description: TODO(用一句话描述该文件做什么)

 * @author hulikaimen@gmail.com

 * @date 2018年1月21日 上午9:54:27

 * @version V1.0 

 */
package com.km.model;

/**
 * @author PipiLu
 * @version 创建时间：2018年1月21日 上午9:54:27 类说明
 *  返回执行结果的对象信息
 */
public class Result<T> {
	/* 错误码，成功为0 */
	private Integer code;
	/* 提示信息 */
	private String msg;
	/* 具体的内容 ,返回java对象*/
	private T data;

	public Integer getCode() {
		return code;
	}

	public void setCode(Integer code) {
		this.code = code;
	}

	public String getMsg() {
		return msg;
	}

	public void setMsg(String msg) {
		this.msg = msg;
	}

	public T getData() {
		return data;
	}

	public void setData(T data) {
		this.data = data;
	}
}
