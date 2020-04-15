package com.store.dao;

import java.util.List;

import org.apache.ibatis.annotations.Param;

import com.store.entity.PersonInfo;

public interface PersonInfoDao {

	List<PersonInfo> queryPersonInfoList(@Param("personInfoCondition") PersonInfo personInfoCondition, @Param("rowIndex") int rowIndex, @Param("pageSize") int pageSize);

	int queryPersonInfoCount(@Param("personInfoCondition") PersonInfo personInfoCondition);

	PersonInfo queryPersonInfoById(long userId);

	int insertPersonInfo(PersonInfo personInfo);

	int updatePersonInfo(PersonInfo personInfo);
}
