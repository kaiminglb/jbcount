/** 

* @Title: AnnCause.java

* @Package com.km.model

* @Description: 案由实体

* @author hulikaimen@gmail.com

* @date 2017年11月16日 下午11:35:18

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
 * @version 创建时间：2017年11月16日 下午11:35:18
 * 类说明 案由
 */
@Data
@Entity
@Table(name="t_anncause")
public class AnnCause {
	@Id
	@GeneratedValue
	private Integer annCauseID;
	private String name;
	private String quanPin;
	private String jianPin;
}
