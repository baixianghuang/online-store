package com.store.service.impl;

import com.store.dao.PersonInfoDao;
import com.store.dto.PersonInfoExecution;
import com.store.entity.PersonInfo;
import com.store.enums.OperationStateEnum;
import com.store.enums.PersonInfoStateEnum;
import com.store.exceptions.PersonInfoOperationException;
import com.store.service.PersonInfoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

@Service
public class PersonInfoServiceImpl implements PersonInfoService {

	@Autowired
	private PersonInfoDao personInfoDao;

	@Override
	public PersonInfo queryInfoByUserId(long userId) {
		return personInfoDao.queryPersonInfoById(userId);
	}

	@Override
	public PersonInfoExecution insertPersonInfo(PersonInfo user) {
		// 空值判断
		if (user == null || user.getLocalAuthId() == null || user.getName() == null) {
			return new PersonInfoExecution(PersonInfoStateEnum.NULL_PERSON_INFO);
		}
		// 设置默认信息
		user.setCreateTime(new Date());
		try {
			int effectedNum = personInfoDao.insertPersonInfo(user);
			if (effectedNum <= 0) {
				throw new PersonInfoOperationException("用户信息新增失败");
			} else {
				return new PersonInfoExecution(OperationStateEnum.SUCCESS, user);
			}
		} catch (Exception e) {
			throw new PersonInfoOperationException("insertPersonInfo error:" + e.getMessage());
		}
	}

	@Override
	public PersonInfoExecution updatePersonInfo(PersonInfo user) {
		// 空值判断
		if (user == null || user.getUserId() == null || user.getLocalAuthId() == null || user.getName() == null) {
			return new PersonInfoExecution(PersonInfoStateEnum.NULL_PERSON_INFO);
		}
		// 设置默认信息
		user.setLastEditTime(new Date());
		try {
			int effectedNum = personInfoDao.updatePersonInfo(user);
			if (effectedNum <= 0) {
				throw new PersonInfoOperationException("用户信息修改失败");
			} else {
				return new PersonInfoExecution(OperationStateEnum.SUCCESS, user);
			}
		} catch (Exception e) {
			throw new PersonInfoOperationException("insertPersonInfo error:" + e.getMessage());
		}
	}

}
