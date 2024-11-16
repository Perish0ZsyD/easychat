package com.easychat.service.impl;


import java.util.List;
import com.easychat.entity.query.SimplePage;
import com.easychat.entity.enums.PageSize;
import com.easychat.mappers.UserContactMapper;
import com.easychat.service.UserContactService;
import com.easychat.entity.vo.PaginationResultVO;
import com.easychat.entity.po.UserContact;
import com.easychat.entity.query.UserContactQuery;
import org.springframework.stereotype.Service;
import javax.annotation.Resource;
/**
 * @Description: 联系人 业务接口实现
 * @Author: false
 * @Date: 2024/11/14 23:06:48
 */
@Service("UserContactMapper")
public class UserContactServiceImpl implements UserContactService{

	@Resource
	private UserContactMapper<UserContact, UserContactQuery> userContactMapper;

	/**
 	 * 根据条件查询列表
 	 */
	@Override
	public List<UserContact> findListByParam(UserContactQuery query) {
		return this.userContactMapper.selectList(query);	}

	/**
 	 * 根据条件查询数量
 	 */
	@Override
	public Integer findCountByParam(UserContactQuery query) {
		return this.userContactMapper.selectCount(query);	}

	/**
 	 * 分页查询
 	 */
	@Override
	public PaginationResultVO<UserContact> findListByPage(UserContactQuery query) {
		Integer count = this.findCountByParam(query);
		Integer pageSize = query.getPageSize() == null ? PageSize.SIZE15.getSize() : query.getPageSize();
		SimplePage page = new SimplePage(query.getPageNo(), count, pageSize);
		query.setSimplePage(page);
		List<UserContact> list = this.findListByParam(query);
		PaginationResultVO<UserContact> result = new PaginationResultVO(count, page.getPageSize(), page.getPageNo(), page.getPageTotal(), list);
		return result;
	}

	/**
 	 * 新增
 	 */
	@Override
	public Integer add(UserContact bean) {
		return this.userContactMapper.insert(bean);
	}

	/**
 	 * 批量新增
 	 */
	@Override
	public Integer addBatch(List<UserContact> listBean) {
		if ((listBean == null) || listBean.isEmpty()) {
			return 0;
		}
			return this.userContactMapper.insertBatch(listBean);
	}

	/**
 	 * 批量新增或修改
 	 */
	@Override
	public Integer addOrUpdateBatch(List<UserContact> listBean) {
		if ((listBean == null) || listBean.isEmpty()) {
			return 0;
		}
			return this.userContactMapper.insertOrUpdateBatch(listBean);
	}

	/**
 	 * 根据 UserIdAndContactId 查询
 	 */
	@Override
	public UserContact getUserContactByUserIdAndContactId(String userId, String contactId) {
		return this.userContactMapper.selectByUserIdAndContactId(userId, contactId);}

	/**
 	 * 根据 UserIdAndContactId 更新
 	 */
	@Override
	public Integer updateUserContactByUserIdAndContactId(UserContact bean, String userId, String contactId) {
		return this.userContactMapper.updateByUserIdAndContactId(bean, userId, contactId);}

	/**
 	 * 根据 UserIdAndContactId 删除
 	 */
	@Override
	public Integer deleteUserContactByUserIdAndContactId(String userId, String contactId) {
		return this.userContactMapper.deleteByUserIdAndContactId(userId, contactId);}
}