package com.easychat.controller;

import com.easychat.annotation.GlobalInterceptor;
import com.easychat.entity.dto.TokenUserInfoDto;
import com.easychat.entity.enums.GroupStatusEnum;
import com.easychat.entity.enums.MessageTypeEnum;
import com.easychat.entity.enums.UserContactStatusEnum;
import com.easychat.entity.po.GroupInfo;
import com.easychat.entity.po.UserContact;
import com.easychat.entity.query.GroupInfoQuery;
import com.easychat.entity.query.UserContactQuery;
import com.easychat.entity.vo.GroupInfoVO;
import com.easychat.entity.vo.ResponseVO;
import com.easychat.exception.BusinessException;
import com.easychat.service.GroupInfoService;
import com.easychat.service.UserContactService;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

/**
 * @ClassName GroupInfoController
 * @Description
 * @Author Siyuan
 * @Date 2024/11/15/20:46
 * @Version 1.0
 */
public class GroupInfoController extends ABaseController {

    @Resource
    private GroupInfoService groupInfoService;

    @Resource
    private UserContactService userContactService;

    @RequestMapping("/saveGroup")
    public ResponseVO saveGroup(HttpServletRequest request,
                                String groupId,
                                @NotEmpty String groupName,
                                String groupNotice,
                                @NotNull Integer joinType,
                                MultipartFile avatarFile,
                                MultipartFile avatarCover) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        GroupInfo groupInfo = new GroupInfo();
        groupInfo.setGroupId(groupId);
        groupInfo.setGroupOwnerId(tokenUserInfoDto.getUserId());
        groupInfo.setGroupName(groupName);
        groupInfo.setGroupNotice(groupNotice);
        groupInfo.setJoinType(joinType);
        this.groupInfoService.saveGroup(groupInfo, avatarFile, avatarCover);
        return getSuccessResponseVO(null);
    }

    /**
     * 加载群组信息，实现效果是创建群组之后，前端更新群组列表
     * 聊天图片，群组封面，联系人头像等是从服务端下载，然后缓存在本地，缓解服务端压力
     * @param request
     * @return
     */
    @RequestMapping(value = "/loadMyGroup")
    @GlobalInterceptor
    public ResponseVO loadMyGroup(HttpServletRequest request) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        GroupInfoQuery infoQuery = new GroupInfoQuery();
        infoQuery.setGroupOwnerId(tokenUserInfoDto.getUserId());
        infoQuery.setOrderBy("create_time desc");
        List<GroupInfo> groupInfoList = this.groupInfoService.findListByParam(infoQuery);
        return getSuccessResponseVO(groupInfoList);
    }

    /**
     * 获取群信息
     *
     * @param request
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/getGroupInfo")
    @GlobalInterceptor
    public ResponseVO getGroupInfo(HttpServletRequest request,
                                   @NotEmpty String groupId) {
        GroupInfo groupInfo = getGroupDetailCommon(request, groupId);
        UserContactQuery userContactQuery = new UserContactQuery();
        userContactQuery.setContactId(groupId);
        Integer memberCount = this.userContactService.findCountByParam(userContactQuery);
        groupInfo.setMemberCount(memberCount);
        return getSuccessResponseVO(groupInfo);
    }

    private GroupInfo getGroupDetailCommon(HttpServletRequest request, String groupId) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        UserContact userContact = this.userContactService.getUserContactByUserIdAndContactId(tokenUserInfoDto.getUserId(), groupId);
        if (userContact == null || !UserContactStatusEnum.FRIEND.getStatus().equals(userContact.getStatus())) {
            throw new BusinessException("你不在群聊或者群聊不存在或已经解散");
        }
        GroupInfo groupInfo = this.groupInfoService.getGroupInfoByGroupId(groupId);
        if (groupInfo == null || !GroupStatusEnum.NORMAL.getStatus().equals(groupInfo.getStatus())) {
            throw new BusinessException("群聊不存在或已经解散");
        }
        return groupInfo;
    }

    @RequestMapping(value = "/getGroupInfo4Chat")
    @GlobalInterceptor
    public ResponseVO getGroupInfo4Chat(HttpServletRequest request, @NotEmpty String groupId) {
        GroupInfo groupInfo = getGroupDetailCommon(request, groupId);
        UserContactQuery userContactQuery = new UserContactQuery();
        userContactQuery.setContactId(groupId);
        userContactQuery.setQueryUserInfo(true);
        userContactQuery.setOrderBy("create_time asc");
        userContactQuery.setStatus(UserContactStatusEnum.FRIEND.getStatus());
        List<UserContact> userContactList = this.userContactService.findListByParam(userContactQuery);

        GroupInfoVO groupInfoVo = new GroupInfoVO();
        groupInfoVo.setGroupInfo(groupInfo);
        groupInfoVo.setUserContactList(userContactList);
        return getSuccessResponseVO(groupInfoVo);
    }


    /**
     * 退群
     * @Description 退群和解散群的关键在于把消息通知给群组里其他人
     * @param request
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/leaveGroup")
    @GlobalInterceptor
    public ResponseVO leaveGroup(HttpServletRequest request, @NotEmpty String groupId) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        groupInfoService.leaveGroup(tokenUserInfoDto.getUserId(), groupId, MessageTypeEnum.LEAVE_GROUP);
        return getSuccessResponseVO(null);
    }

    /**
     * 解散群
     *
     * @param request
     * @param groupId
     * @return
     */
    @RequestMapping(value = "/dissolutionGroup")
    @GlobalInterceptor
    public ResponseVO dissolutionGroup(HttpServletRequest request, @NotEmpty String groupId) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        groupInfoService.dissolutionGroup(tokenUserInfoDto.getUserId(), groupId);
        return getSuccessResponseVO(null);
    }


    /**
     * 添加或者移除人员
     *
     * @param request
     * @param groupId
     * @param selectContacts
     * @param opType
     * @return
     */
    @RequestMapping(value = "/addOrRemoveGroupUser")
    @GlobalInterceptor
    public ResponseVO addOrRemoveGroupUser(HttpServletRequest request, @NotEmpty String groupId, @NotEmpty String selectContacts,
                                           @NotNull Integer opType) {
        TokenUserInfoDto tokenUserInfoDto = getTokenUserInfo(request);
        groupInfoService.addOrRemoveGroupUser(tokenUserInfoDto, groupId, selectContacts, opType);
        return getSuccessResponseVO(null);
    }

}
