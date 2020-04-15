package com.store.service.impl;

import com.store.dao.LocalAuthDao;
import com.store.dto.LocalAuthExecution;
import com.store.entity.LocalAuth;
import com.store.enums.LocalAuthStateEnum;
import com.store.enums.OperationStateEnum;
import com.store.exceptions.LocalAuthOperationException;
import com.store.service.LocalAuthService;
import com.store.util.MD5;
import com.sun.xml.internal.bind.v2.TODO;
import org.apache.commons.lang.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

@Service
public class LocalAuthServiceImpl implements LocalAuthService {
	@Autowired
	private LocalAuthDao localAuthDao;

//	TODO: encrypt pwd
	@Override
	public LocalAuth getLocalAuthByUsernameAndPwd(String username, String password) {
		return localAuthDao.queryLocalByUsernameAndPwd(username, password);  // MD5.getMd5(password)
	}

	@Override
	public LocalAuth getLocalAuthByUsername(String username) {
		return localAuthDao.queryLocalByUsername(username);
	}

	@Override
	public LocalAuth queryLocalByLocalAuthId(long localAuthId) {
		return localAuthDao.queryLocalByLocalAuthId(localAuthId);
	}

	@Override
	@Transactional
	public LocalAuthExecution saveLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException {
		if (localAuth == null || localAuth.getUsername() == null || localAuth.getPassword() == null) {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
		// 保存数据
		localAuth.setCreateTime(new Date());
		localAuth.setPassword(localAuth.getPassword());  // MD5.getMd5(localAuth.getPassword()))
		try {
			int effectedNum = localAuthDao.insertLocalAuth(localAuth);
			if (effectedNum <= 0) {
				throw new LocalAuthOperationException("用户账号新增失败");
			} else {
				return new LocalAuthExecution(OperationStateEnum.SUCCESS, localAuth);
			}
		} catch (Exception e) {
			throw new LocalAuthOperationException("insertLocalAuth error:" + e.getMessage());
		}
	}

	@Override
	public LocalAuthExecution modifyLocalAuth(String username, String password, String newPassword)
			throws LocalAuthOperationException {
		if (StringUtils.isNotBlank(username) && StringUtils.isNotBlank(password)
				&& StringUtils.isNotBlank(newPassword)) {
			try {
				// MD5.getMd5(password), MD5.getMd5(newPassword)
				int effectedNum = localAuthDao.updateLocalAuth(username, password, newPassword,
						new Date());
				if (effectedNum <= 0) {
					return new LocalAuthExecution(LocalAuthStateEnum.ERROR_UPDATE);
				} else {
					return new LocalAuthExecution(LocalAuthStateEnum.SUCCESS);
				}
			} catch (Exception e) {
				throw new LocalAuthOperationException("updateLocalAuth error:" + e.getMessage());
			}
		} else {
			return new LocalAuthExecution(LocalAuthStateEnum.NULL_AUTH_INFO);
		}
	}
}
