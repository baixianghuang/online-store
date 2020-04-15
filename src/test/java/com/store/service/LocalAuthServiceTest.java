package com.store.service;

import com.store.BaseTest;
import com.store.dao.LocalAuthDao;
import com.store.dto.LocalAuthExecution;
import com.store.entity.LocalAuth;
import com.store.entity.PersonInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

public class LocalAuthServiceTest extends BaseTest {

	@Autowired
	private LocalAuthService localAuthService;
	private static final String username = "admin1";
	private static final String password = "666666";

	@Test
	@Ignore
	public void testInsertLocalAuth() throws Exception {
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(13L);
		localAuth.setPersonInfo(personInfo);
		localAuth.setUsername(username);
		localAuth.setPassword(password);
		localAuth.setCreateTime(new Date());
		LocalAuthExecution execution = localAuthService.saveLocalAuth(localAuth);
		System.out.println(execution.getStateInfo());
	}

	/**
	 * 根据用户名和账号查询用户
	 */
	@Test
	@Ignore
	public void testQueryLocalByUsername() {
//		LocalAuth localAuth = localAuthService.getLocalAuthByUsername(username);
		LocalAuth localAuth = localAuthService.getLocalAuthByUsernameAndPwd(username, password);
		System.out.println(localAuth.getPassword());

	}

	/**
	 * 根据用户Id查询本地账号信息
	 */
	@Test
	@Ignore
	public void testQueryLocalByLocalAuthId() {
		LocalAuth localAuth = localAuthService.queryLocalByLocalAuthId(10L);
		assertEquals("consumer1", localAuth.getPersonInfo().getName());
	}

	/**
	 * 更新账号信息
	 */
	@Test
//	@Ignore
	public void testUpdateLocalAuth() {
		Date now = new Date();
		LocalAuthExecution lae = localAuthService.modifyLocalAuth(username, password, "666666");
		System.out.println(lae.getStateInfo());
		//		assertEquals(1, effectNum);
	}

}
