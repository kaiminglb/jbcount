/** 

* @Title: c123.java

* @Package com.km.common.enums

* @Description: TODO(用一句话描述该文件做什么)

* @author hulikaimen@gmail.com

* @date 2018年1月22日 上午1:01:42

* @version V1.0 

*/ 
package com.km.common.enums;
/**
 * @author PipiLu
 * @version 创建时间：2018年1月22日 上午1:01:42
 * 类说明  返回结果枚举类
 */
public enum ResultEnum {
	UKNOWN_ERROR(-1,"未知系统异常"),SUCCESS(0,"成功"),ANNCAUSEEXISTED(110,"案由已存在")
	,ANNCAUSECHINESE(111,"案由名称必须中文")
	;
	
	private String msg;
	private Integer code;
	
	ResultEnum(Integer code, String msg) {
		this.code = code;
		this.msg = msg;
	}

	public Integer getCode() {
		return code;
	}

	public String getMsg() {
		return msg;
	}
	
}
