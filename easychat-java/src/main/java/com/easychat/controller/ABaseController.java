package com.easychat.controller;

import com.easychat.entity.constants.Constants;
import com.easychat.entity.dto.TokenUserInfoDto;
import com.easychat.entity.vo.ResponseVO;;

import com.easychat.entity.enums.ResponseCodeEnum;
import com.easychat.redis.RedisUtils;;import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
 * @Description: 信息返回状态
 * @Author: Siyuan
 */
public class ABaseController {

	protected static final String STATUS_SUCCESS = "success";

	protected static final String STATUS_ERROR = "error";

	@Resource
	private RedisUtils redisUtils;

	protected <T> ResponseVO getSuccessResponseVO(T t) {
		ResponseVO<T> responseVO = new ResponseVO<>();
		responseVO.setStatus(STATUS_SUCCESS);
		responseVO.setCode(ResponseCodeEnum.CODE_200.getCode());
		responseVO.setInfo(ResponseCodeEnum.CODE_200.getMsg());
		responseVO.setData(t);
		return responseVO;
	}

	protected TokenUserInfoDto getTokenUserInfo(HttpServletRequest request) {
		String token = request.getHeader("token");
		TokenUserInfoDto tokenUserInfoDto = (TokenUserInfoDto) redisUtils.get(Constants.REDIS_KEY_WS_TOKEN + token);
		return tokenUserInfoDto;
	}
}
