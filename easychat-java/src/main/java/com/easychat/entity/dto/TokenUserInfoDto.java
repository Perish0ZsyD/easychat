package com.easychat.entity.dto;

/**
 * @ClassName TokenUserInfoDto
 * @Description 传入Redis中的用户信息
 * @Author Siyuan
 * @Date 2024/11/08/21:53
 * @Version 1.0
 */
public class TokenUserInfoDto {
    private static final long serialVersionUID = -6910208948981307451L;
    private String token;
    private String userId;
    private String nickName;
    private Boolean admin; // 是否为超级管理员

    public String getNickName() {
        return nickName;
    }

    public void setNickName(String nickName) {
        this.nickName = nickName;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public Boolean getAdmin() {
        return admin;
    }

    public void setAdmin(Boolean admin) {
        this.admin = admin;
    }
}
