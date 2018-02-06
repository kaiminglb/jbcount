/** 

* @Title: Clerk.java

* @Package com.km.model


* @author hulikaimen@gmail.com

* @date 2017年11月17日 上午12:21:00

* @version V1.0 

*/ 
package com.km.model;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.Data;

/**
 * @author PipiLu
 * @version 创建时间：2017年11月17日 上午12:21:00
 * 类说明 书记员（承办法官）及所属法庭信息;书记员和法庭是对应的
 */
@Data
@Entity
@Table(name="t_clerk")
public class Clerk {
	@Id
	@GeneratedValue
	private Integer clerkID;
	private String name;
	private String quanPin;
	private String jianPin;
	/** 法庭 */
	private String courtName;
}
