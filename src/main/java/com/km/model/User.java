package com.km.model;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.Email;

@Entity
@Table(name="t_user")
public class User {
	private int userID;
	/**
	 * 用户登录名称
	 */
	private String username;
	/**
	 * 用户登录密码
	 */
	private String password;
	/**
	 * 用户的中文名称
	 */
	private String nickname;
	
	
	
	
	
	/**   
	* @param id
	* @param username
	* @param password 用Md5加密过,加盐
	* @param nickname   
	*/
	public User(int id, String username, String password, String nickname) {
		super();
		this.userID = id;
		this.username = username;
		this.password = password;
		this.nickname = nickname;
	}

	public User() {
	}
	
	@Id
	@GeneratedValue
	public int getUserID() {
		return userID;
	}

	public void setUserID(int userID) {
		this.userID = userID;
	}
	
	@NotNull(message="用户名不能为空")
	public String getUsername() {
		return username;
	}
	

	public void setUsername(String username) {
		this.username = username;
	}
	
	@NotNull(message="用户密码不能为空")
	public String getPassword() {
		return password;
	}
	public void setPassword(String password) {
		this.password = password;
	}
	public String getNickname() {
		return nickname;
	}
	public void setNickname(String nickname) {
		this.nickname = nickname;
	}
	
	
}
