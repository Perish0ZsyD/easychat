package com.easychat.service;


import java.util.List;
import com.easychat.entity.vo.PaginationResultVO;
import com.easychat.entity.po.UserContact;
import com.easychat.entity.query.UserContactQuery;
/**
 * @Description: 联系人 Service
 * @Author: false
 * @Date: 2024/11/14 23:06:48
 */
public interface UserContactService{

	/**
 	 * 根据条件查询列表
 	 */
	List<UserContact> findListByParam(UserContactQuery query);

	/**
 	 * 根据条件查询数量
 	 */
	Integer findCountByParam(UserContactQuery query);

	/**
 	 * 分页查询
 	 */
	PaginationResultVO<UserContact> findListByPage(UserContactQuery query);

	/**
 	 * 新增
 	 */
	Integer add(UserContact bean);

	/**
 	 * 批量新增
 	 */
	Integer addBatch(List<UserContact> listBean);

	/**
 	 * 批量新增或修改
 	 */
	Integer addOrUpdateBatch(List<UserContact> listBean);

	/**
 	 * 根据 UserIdAndContactId 查询
 	 */
	UserContact getUserContactByUserIdAndContactId(String userId, String contactId);

	/**
 	 * 根据 UserIdAndContactId 更新
 	 */
	Integer updateUserContactByUserIdAndContactId(UserContact bean, String userId, String contactId); 

	/**
 	 * 根据 UserIdAndContactId 删除
 	 */
	Integer deleteUserContactByUserIdAndContactId(String userId, String contactId);
}