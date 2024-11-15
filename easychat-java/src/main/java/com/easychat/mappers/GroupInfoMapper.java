package com.easychat.mappers;

import org.apache.ibatis.annotations.Param;

/**
 * @Description:  Mapper
 * @Author: false
 * @Date: 2024/11/14 23:06:48
 */
public interface GroupInfoMapper<T, P> extends BaseMapper {
	/**
	 * 根据GroupId更新
	 */
	Integer updateByGroupId(@Param("bean") T t,@Param("groupId") String groupId);


	/**
	 * 根据GroupId删除
	 */
	Integer deleteByGroupId(@Param("groupId") String groupId);


	/**
	 * 根据GroupId获取对象
	 */
	T selectByGroupId(@Param("groupId") String groupId);

}