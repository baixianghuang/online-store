package com.store.dao;

import com.store.BaseTest;
import com.store.entity.PersonInfo;
import com.store.enums.PersonInfoStateEnum;
import com.store.enums.PersonInfoTypeEnum;
import org.junit.Ignore;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Date;

import static org.junit.Assert.assertEquals;

/**
 * @Description: 用户信息数据层测试
 *
 * @author tyronchen
 * @date 2019年6月3日
 */
public class PersonInfoDaoTest extends BaseTest {

	@Autowired
	private PersonInfoDao personInfoDao;

	/**
	 * 测试插入用户
	 * 
	 * @throws Exception
	 */
	@Test
	@Ignore
	public void testInsertPersonInfo() throws Exception {
		// 新增一条用户
		PersonInfo personInfo = new PersonInfo();
		personInfo.setLocalAuthId(10L);
		// 注册一个有权限访问的顾客
		personInfo.setEnableStatus(PersonInfoStateEnum.ALLOW.getState());
		personInfo.setUserType(PersonInfoTypeEnum.ADMIN.getState());
		personInfo.setCreateTime(new Date());
		personInfo.setGender("male");
		personInfo.setEmail("a@a.com");
		personInfo.setName("admin1");
		int effectNum = personInfoDao.insertPersonInfo(personInfo);
		assertEquals(1, effectNum);
	}

	/**
	 * 根据用户Id查询本地账号信息
	 */
	@Test
//	@Ignore
	public void testQueryInfoByUserId() {
		PersonInfo personInfo = personInfoDao.queryPersonInfoById(12L);
		assertEquals("admin1", personInfo.getName());
	}

	/**
	 * 更新账号信息
	 */
	@Test
	@Ignore
	public void testUpdatePersonInfo() {
		PersonInfo personInfo = new PersonInfo();
		personInfo.setLastEditTime(new Date());
		personInfo.setUserId(2L);
		personInfo.setName("顾客aa");
		int effectNum = personInfoDao.updatePersonInfo(personInfo);
		assertEquals(1, effectNum);
	}

}
