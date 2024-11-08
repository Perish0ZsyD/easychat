package com.easychat.entity.query;



/**
 * @Description: 靓号表
 * @Author: false
 * @Date: 2024/11/08 15:08:51
 */
public class UserInfoBeautyQuery extends BaseQuery {
	/**
 	 * 自增ID 查询对象
 	 */
	private String id;

	private String idFuzzy;

	/**
 	 *  查询对象
 	 */
	private String email;

	private String emailFuzzy;

	/**
 	 *  查询对象
 	 */
	private String userId;

	private String userIdFuzzy;

	/**
 	 * 0：未使用，1：已使用 查询对象
 	 */
	private Integer status;


	public void setId(String id) {
		this.id = id;
	}

	public String getId() {
		return id;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setIdFuzzy(String idFuzzy) {
		this.idFuzzy = idFuzzy;
	}

	public String getIdFuzzy() {
		return idFuzzy;
	}

	public void setEmailFuzzy(String emailFuzzy) {
		this.emailFuzzy = emailFuzzy;
	}

	public String getEmailFuzzy() {
		return emailFuzzy;
	}

	public void setUserIdFuzzy(String userIdFuzzy) {
		this.userIdFuzzy = userIdFuzzy;
	}

	public String getUserIdFuzzy() {
		return userIdFuzzy;
	}
}