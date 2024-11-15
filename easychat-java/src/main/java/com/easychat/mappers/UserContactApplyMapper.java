package com.easychat.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Description: 申请人信息 Mapper
 * @Author: false
 * @Date: 2024/11/14 23:06:48
 */
public interface UserContactApplyMapper<T, P> extends BaseMapper {

	/**
	 * 根据ApplyId更新
	 */
	Integer updateByApplyId(@Param("bean") T t,@Param("applyId") Integer applyId);


	/**
	 * 根据ApplyId删除
	 */
	Integer deleteByApplyId(@Param("applyId") Integer applyId);


	/**
	 * 根据ApplyId获取对象
	 */
	T selectByApplyId(@Param("applyId") Integer applyId);


	/**
	 * 根据ApplyUserIdAndReceiveUserIdAndContactId更新
	 */
	Integer updateByApplyUserIdAndReceiveUserIdAndContactId(@Param("bean") T t,@Param("applyUserId") String applyUserId,@Param("receiveUserId") String receiveUserId,@Param("contactId") String contactId);


	/**
	 * 根据ApplyUserIdAndReceiveUserIdAndContactId删除
	 */
	Integer deleteByApplyUserIdAndReceiveUserIdAndContactId(@Param("applyUserId") String applyUserId,@Param("receiveUserId") String receiveUserId,@Param("contactId") String contactId);


	/**
	 * 根据ApplyUserIdAndReceiveUserIdAndContactId获取对象
	 */
	T selectByApplyUserIdAndReceiveUserIdAndContactId(@Param("applyUserId") String applyUserId,@Param("receiveUserId") String receiveUserId,@Param("contactId") String contactId);

}