package com.easychat.service;


import java.util.List;
import java.util.Map;

import com.easychat.entity.vo.PaginationResultVO;
import com.easychat.entity.po.UserInfo;
import com.easychat.entity.query.UserInfoQuery;
/**
 * @Description: 用户信息表 Service
 * @Author: false
 * @Date: 2024/11/08 15:08:51
 */
public interface UserInfoService{

	/**
 	 * 根据条件查询列表
 	 */
	List<UserInfo> findListByParam(UserInfoQuery query);

	/**
 	 * 根据条件查询数量
 	 */
	Integer findCountByParam(UserInfoQuery query);

	/**
 	 * 分页查询
 	 */
	PaginationResultVO<UserInfo> findListByPage(UserInfoQuery query);

	/**
 	 * 新增
 	 */
	Integer add(UserInfo bean);

	/**
 	 * 批量新增
 	 */
	Integer addBatch(List<UserInfo> listBean);

	/**
 	 * 批量新增或修改
 	 */
	Integer addOrUpdateBatch(List<UserInfo> listBean);

	/**
 	 * 根据 UserId 查询
 	 */
	UserInfo getUserInfoByUserId(String userId);

	/**
 	 * 根据 UserId 更新
 	 */
	Integer updateUserInfoByUserId(UserInfo bean, String userId); 

	/**
 	 * 根据 UserId 删除
 	 */
	Integer deleteUserInfoByUserId(String userId);

	/**
 	 * 根据 Email 查询
 	 */
	UserInfo getUserInfoByEmail(String email);

	/**
 	 * 根据 Email 更新
 	 */
	Integer updateUserInfoByEmail(UserInfo bean, String email); 

	/**
 	 * 根据 Email 删除
 	 */
	Integer deleteUserInfoByEmail(String email);

	/**
 	 * 注册
 	 */
	void register(String email, String nickName, String password);

	/**
 	 * 登录
 	 */
	Map<String, Object> login(String email,  String password);
}