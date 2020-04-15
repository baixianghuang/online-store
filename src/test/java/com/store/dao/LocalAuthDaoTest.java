package com.store.dao;

import com.store.BaseTest;
import com.store.entity.LocalAuth;
import com.store.entity.PersonInfo;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @Description: 本地用户信息数据层测试
 *
 * @author tyronchen
 * @date 2019年5月20日
 */
public class LocalAuthDaoTest extends BaseTest {

	@Autowired
	private LocalAuthDao localAuthDao;
	private static final String username = "admin1";
	private static final String password = "666666";

	@Test
	@Ignore
	public void testInsertLocalAuth() throws Exception {
		// 新增一条本地用户
		LocalAuth localAuth = new LocalAuth();
		PersonInfo personInfo = new PersonInfo();
		personInfo.setUserId(9L);
		localAuth.setPersonInfo(personInfo);
		// 设置用户名和密码
		localAuth.setUsername(username);
		localAuth.setPassword(password);
		localAuth.setCreateTime(new Date());
		int effectNum = localAuthDao.insertLocalAuth(localAuth);
		assertEquals(1, effectNum);
	}

	/**
	 * 根据用户名和账号查询用户
	 */
	@Test
//	@Ignore
	public void testQueryLocalByUsernameAndPwd() {
		LocalAuth localAuth = localAuthDao.queryLocalByUsernameAndPwd(username, password);
		assertEquals("admin1", localAuth.getPersonInfo().getName());
	}

	/**
	 * 根据用户Id查询本地账号信息
	 */
	@Test
	@Ignore
	public void testQueryLocalByLocalAuthId() {
		LocalAuth localAuth = localAuthDao.queryLocalByLocalAuthId(10L);
		assertEquals("consumer1", localAuth.getPersonInfo().getName());
	}

	/**
	 * 更新账号信息
	 */
	@Test
	@Ignore
	public void testUpdateLocalAuth() {
		Date now = new Date();
		int effectNum = localAuthDao.updateLocalAuth(username, password, "654321", now);
		assertEquals(1, effectNum);
	}

}
