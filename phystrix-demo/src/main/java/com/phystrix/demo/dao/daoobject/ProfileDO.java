package com.phystrix.demo.dao.daoobject;

import java.io.Serializable;


public class ProfileDO implements Serializable{

	/**  */
	private static final long serialVersionUID = 5769408669596795306L;

	/** 自增主键 */
	private Long id;

	/** 用户唯一标识 */
	private String userId;

	/** 年龄 */
	private int age;

	/** 邮件 */
	private String email;
	
	/** 城市 */
	private String city;
	
	/** 手机 */
	private String mobile;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getUserId() {
		return userId;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}


	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getCity() {
		return city;
	}

	public void setCity(String city) {
		this.city = city;
	}

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public ProfileDO(Long id, String userId, int age, String email, String city, String mobile) {
		this.id = id;
		this.userId = userId;
		this.age = age;
		this.email = email;
		this.city = city;
		this.mobile = mobile;
	}

	@Override
	public String toString() {
		return "ProfileDO [id=" + id + ", userId=" + userId + ", age=" + age + ", email=" + email + ", city=" + city
				+ ", mobile=" + mobile + "]";
	}

}
