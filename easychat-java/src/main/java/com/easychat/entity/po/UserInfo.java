package com.easychat.entity.po;

import java.io.Serializable;

import com.easychat.enums.DateTimePatternEnum;
import com.easychat.utils.DateUtils;
import java.util.Date;
import com.fasterxml.jackson.annotation.JsonFormat;
import org.springframework.format.annotation.DateTimeFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;


/**
 * @Description: UserInfo
 * @Author: false
 * @Date: 2024/11/08 15:08:51
 */
public class UserInfo implements Serializable {
	/**
 	 * 用户ID，有靓号存在，不要自增长
 	 */
	private String userId;

	/**
 	 * 邮箱
 	 */
	private String email;

	/**
 	 * 昵称
 	 */
	private String nickName;

	/**
 	 * 0：直接加入，1：同意后加好友
 	 */
	private Integer joinType;

	/**
 	 * 0：女，1：男
 	 */
	private Integer sex;

	/**
 	 * 密码
 	 */
	private String password;

	/**
 	 * 个性签名
 	 */
	private String personalSignature;

	/**
 	 * 状态
 	 */
	@JsonIgnore
	private Integer status;

	/**
 	 * 账户创建时间
 	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date createTime;

	/**
 	 * 最后登录时间
 	 */
	@JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone = "GMT+8")
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
	private Date lastLoginTime;

	/**
 	 * 地区
 	 */
	private String areaName;

	/**
 	 * 地区编号
 	 */
	private String areaCode;

	/**
 	 * 最后在线时间(记录到毫秒级别）
 	 */
	private Long lastOffTime;


	public void setUserId(String userId) {
		this.userId = userId;
	}

	public String getUserId() {
		return userId;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getEmail() {
		return email;
	}

	public void setNickName(String nickName) {
		this.nickName = nickName;
	}

	public String getNickName() {
		return nickName;
	}

	public void setJoinType(Integer joinType) {
		this.joinType = joinType;
	}

	public Integer getJoinType() {
		return joinType;
	}

	public void setSex(Integer sex) {
		this.sex = sex;
	}

	public Integer getSex() {
		return sex;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getPassword() {
		return password;
	}

	public void setPersonalSignature(String personalSignature) {
		this.personalSignature = personalSignature;
	}

	public String getPersonalSignature() {
		return personalSignature;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public Integer getStatus() {
		return status;
	}

	public void setCreateTime(Date createTime) {
		this.createTime = createTime;
	}

	public Date getCreateTime() {
		return createTime;
	}

	public void setLastLoginTime(Date lastLoginTime) {
		this.lastLoginTime = lastLoginTime;
	}

	public Date getLastLoginTime() {
		return lastLoginTime;
	}

	public void setAreaName(String areaName) {
		this.areaName = areaName;
	}

	public String getAreaName() {
		return areaName;
	}

	public void setAreaCode(String areaCode) {
		this.areaCode = areaCode;
	}

	public String getAreaCode() {
		return areaCode;
	}

	public void setLastOffTime(Long lastOffTime) {
		this.lastOffTime = lastOffTime;
	}

	public Long getLastOffTime() {
		return lastOffTime;
	}
	@Override
	public String toString() {
		return "用户ID，有靓号存在，不要自增长:" + (userId == null ? "空" : userId) + "," + 
				"邮箱:" + (email == null ? "空" : email) + "," + 
				"昵称:" + (nickName == null ? "空" : nickName) + "," + 
				"0：直接加入，1：同意后加好友:" + (joinType == null ? "空" : joinType) + "," + 
				"0：女，1：男:" + (sex == null ? "空" : sex) + "," + 
				"密码:" + (password == null ? "空" : password) + "," + 
				"个性签名:" + (personalSignature == null ? "空" : personalSignature) + "," + 
				"状态:" + (status == null ? "空" : status) + "," + 
				"账户创建时间:" + (createTime == null ? "空" : DateUtils.format(createTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + "," + 
				"最后登录时间:" + (lastLoginTime == null ? "空" : DateUtils.format(lastLoginTime, DateTimePatternEnum.YYYY_MM_DD_HH_MM_SS.getPattern())) + "," + 
				"地区:" + (areaName == null ? "空" : areaName) + "," + 
				"地区编号:" + (areaCode == null ? "空" : areaCode) + "," + 
				"最后在线时间(记录到毫秒级别）:" + (lastOffTime == null ? "空" : lastOffTime);
		}
}