package com.store.service;

import com.store.dto.LocalAuthExecution;
import com.store.entity.LocalAuth;
import com.store.exceptions.LocalAuthOperationException;

public interface LocalAuthService {

	LocalAuth getLocalAuthByUsernameAndPwd(String username, String password);

	LocalAuth getLocalAuthByUsername(String username);

	LocalAuth queryLocalByLocalAuthId(long localAuthId);

	LocalAuthExecution saveLocalAuth(LocalAuth localAuth) throws LocalAuthOperationException;

	LocalAuthExecution modifyLocalAuth(String username, String password, String newPassword)
			throws LocalAuthOperationException;
}
